layui.use(['form', 'crud', 'dtree'], function () {
    var form = layui.form,
        dtree = layui.dtree,
        crud = layui.crud,
        $ = layui.$;

    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;

    crud.selectTree('category', pmsUrl + '/category/tree/0/6', 'only');

    crud.initFormSelect('attributeAddForm');

    // 控制可选值列表的展示
    form.on('select(inputType)', function (data) {
        if (data.value === '2') {
            $("#inputListItem").removeClass('layui-hide');
            $("#handAddStatusItem").removeClass('layui-hide');
        } else {
            $("#inputListItem").addClass('layui-hide');
            $("#handAddStatusItem").addClass('layui-hide');
        }
    });

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        var checked = dtree.getCheckbarJsonArrParam("category"); // 获取当前选中节点
        if (checked.nodeId.length === 0) {
            layer.msg('商品分类不能为空', {
                icon: 5
            });
            return false;
        }
        var field = data.field;
        if (field.inputType === '2') {
            if (field.handAddStatus === ''){
                layer.msg('是否支持手动新增不能为空', {
                    icon: 5
                });
                return false;
            }
            if (field.inputList.trim() === '') {
                layer.msg('可选值列表不能为空', {
                    icon: 5
                });
                return false;
            }
        }
        data.field.categoryId = checked.nodeId[0];
        crud.post(pmsUrl + '/attribute', data.field, function () {
            layer.close(parentIndex);
        });
        return false;
    });
});
