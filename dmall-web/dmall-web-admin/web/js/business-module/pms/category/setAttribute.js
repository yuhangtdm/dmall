layui.use(['table', 'tableEdit', 'crud'], function () {
    var table = layui.table,
        tableEdit = layui.tableEdit,
        crud = layui.crud,
        $ = layui.$;

    var data;
    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;

    var cols = table.render({
        elem: '#tableId',
        id: id,
        url: pmsUrl + '/attribute/list',
        method: 'post',
        contentType: 'application/json',
        headers: {
            source: 'admin',
            token: crud.getToken()
        },
        cellMinWidth: 100,
        toolbar: false,
        text: {
            none: '暂无相关数据'
        },
        cols: buildColumn(),
        page: false,
        loading: true,
        request: {
            pageName: 'current',
            limitName: 'size'
        },
        where: buildQueryObj(),
        done: function (res, curr, count) {
            if (res.code === '408') {
                // 用户未登陆 跳转登录页面
                crud.errorBack('登录已失效', function () {
                    window.location.href = 'page/login.html';
                })
            }
        },
        parseData: function (res) { //res 即为原始返回的数据
            if (res.data != null) {
                data = res.data;
                crud.get(pmsUrl + '/category/getAttributeIds/' + categoryId, function (response) {
                    for (var i = 0; i < data.length; i++) {
                        for (var j = 0; j < response.data.length; j++) {
                            var canScreen = response.data[j];
                            if (data[i].id === canScreen.id) {
                                data[i].LAY_CHECKED = true;
                                data[i].canScreen = {
                                    'name': canScreen.name,
                                    'value': canScreen.value
                                }
                            }
                        }
                    }
                });
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": data//解析数据列表
                }

            }
        }
    }).config.cols;

    function buildColumn() {
        var params = [
            {name: 1, value: "不可筛选"},
            {name: 2, value: "单选"},
            {name: 3, value: "多选"},
        ];
        return [[
            {type: 'checkbox', fixed: 'left'},
            {field: "id", title: "属性编号"},
            {field: "showName", title: "展示名称"},
            {
                field: "canScreen",
                title: "是否可筛选",
                event: "danxuan",
                config: {"type": "select", "data": params, "cascadeSelectField": "name"},
                templet: function (d) {
                    if (d.canScreen) {
                        if (d.canScreen.value) {
                            return d.canScreen.value;
                        }
                    }
                    return ''
                }
            }
        ]];
    }

    function buildQueryObj() {
        return {
            "categoryId": oneLevelId,
            "threeCategoryId": categoryId
        }
    }

    var aopTable = tableEdit.aopObj(cols); //获取一个aop对象

    aopTable.on('tool(tableEvent)', function (obj) {
        var field = obj.field; //单元格字段
        var value = obj.value; //修改后的值
        var data = obj.data; //当前行旧数据
        var event = obj.event; //当前单元格事件属性值
        var update = {};
        update[field] = value;
        //把value更新到行中
        obj.update(update);
    });

    // 提交
    $("#submit").bind('click', function () {
        var submitObj = {
            'categoryId': categoryId
        };
        var attributes = [];
        for (var i = 0; i < data.length; i++) {
            var obj = data[i];
            if (obj.LAY_CHECKED === true) {
                var attribute = {
                    'attributeId': obj.id
                }
                if (obj.canScreen && obj.canScreen.name) {
                    attribute.canScreen = obj.canScreen.name;
                }
                attributes.push(attribute);
            }
        }
        submitObj.attributes = attributes;
        crud.post(pmsUrl + '/category/setAttribute', submitObj, function () {
            layer.close(parentIndex);
        });
    })

});

