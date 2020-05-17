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
        response: {statusCode: '0', title: "name"},
        toolbarShow: [], // 取消自带的操作
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
                    var basicData = node.basicData;
                    if (basicData.description !== null) {
                        $("#description").val(basicData.description);
                    }
                }
            },
            {
                toolbarId: "updateC", icon: "dtree-icon-bianji", title: "修改", handler: function (node, $div) {
                    console.log(node);
                    var basicData = node.basicData;
                    if (basicData.description != null) {
                        $("#description").text(basicData.description);
                    }
                    $("#name").val(node.context);
                    if (node.parentId !== '-1') {
                        var param = dtree.getParentParam(tree, node.nodeId);
                        $("#parentId").val(node.nodeId);
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
                    // var url;
                    // if (node.level == 1) {
                    //     return false
                    // }
                    // else if (node.level == 2) {
                    //     url = "/monitor/system-info/";
                    // } else if (node.level == 3) {
                    //     url = "/monitor/service-resources-info/"
                    // }
                    // del(node, url);//调用删除函数
                    // DTree5.partialRefreshDel($div); // 这样即可删除节点
                }
            }]
    });

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        crud.post(pmsUrl + '/category', data.field, function () {
            // 刷新树
            tree.refreshTree();
            refreshForm();
        });
        return false;
    });

    function refreshForm() {
        console.log('xx');
        form.val("categoryEditForm", {
            "name": '',
            "parentId": '0',
            "level": '1',
            "parentName": '',
            "description": ''
        });
        form.render(); //刷新表单
    }
})
;
