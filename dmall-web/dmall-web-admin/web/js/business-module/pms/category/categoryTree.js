layui.use(['form', 'crud', 'dtree'], function () {
    var $ = layui.jquery,
        form = layui.form,
        dtree = layui.dtree,
        crud = layui.crud;


    var tree = dtree.render({
        elem: "#iframeTree1",
        url: pmsUrl + "/category/tree/0",
        method: 'get',
        headers: {
            "source": "admin",
            "token": crud.getToken()
        },
        type: 'all',
        dataStyle: "layuiStyle",
        skin: "zdy",
        toolbar: true,
        menubar: true,
        menubarTips:{
            group: ["moveDown", "moveUp", "refresh"], //按钮组
        },
        response: {statusCode: '0', title: "name"},
        toolbarShow: [], // 取消自带的操作
        success: function (response) {
            if (response.code === '0') {
                update(response.data);
            }
        },
        toolbarFun: {
            loadToolbarBefore: function (buttons, param, $div) {
                if (param.level === '3') {
                    buttons.addC = "";  // 取消新增功能
                }
                return buttons; // 将按钮对象返回
            }
        },
        toolbarExt: [
            {
                toolbarId: "addC", icon: "dtree-icon-add-circle", title: "新增子分类", handler: function (node, $div) {
                    $("#parentName").val(node.context);
                    $("#parentId").val(node.nodeId);
                    $("#level").val(parseInt(node.level) + 1);
                }
            },
            {
                toolbarId: "updateC", icon: "dtree-icon-bianji", title: "修改", handler: function (node, $div) {
                    var basicData = node.basicData;
                    if (basicData.description) {
                        $("#description").val(basicData.description);
                    }
                    if (basicData.sort) {
                        $("#sort").val(basicData.sort);
                    }
                    $("#id").val(node.nodeId);
                    $("#name").val(node.context);
                    if (node.parentId !== '-1') {
                        var param = dtree.getParentParam(tree, node.nodeId);
                        $("#parentId").val(param.nodeId);
                        $("#parentName").val(param.context);
                        $("#level").val(node.level);
                    } else {
                        $("#parentId").val('0');
                        $("#parentName").val('');
                        $("#level").val('1');
                    }
                }
            },
            {
                toolbarId: "deleteC",
                icon: "dtree-icon-roundclose",
                title: "删除",
                handler: function (node, $div) {
                    crud.delete('确定删除该分类?', pmsUrl + '/category/' + node.nodeId, function () {
                        // 刷新树
                        refreshForm();
                        tree.refreshTree();
                    });
                }
            }]
    });

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        var field = data.field;
        if (field.id) {
            crud.put(pmsUrl + '/category', data.field, function () {
                // 刷新树
                refreshForm();
                tree.refreshTree();
            });
        } else {
            crud.post(pmsUrl + '/category', data.field, function () {
                // 刷新树
                refreshForm();
                tree.refreshTree();
            });
        }
        return false;
    });

    function refreshForm() {
        $("#id").val('');
        $("#name").val('');
        $("#sort").val('');
        $("#parentId").val('0');
        $("#parentName").val('');
        $("#level").val('1');
        $("#description").val('');
    }

    function update(arr) {
        for (var i = 0; i < arr.length; i++) {
            arr[i].disabled = false;
            if (arr[i].children) {
                update(arr[i].children);
            }
        }
    }
})
;
