layui.use(['form', 'crud', 'dtree', 'inputTags'], function () {
    var form = layui.form,
        dtree = layui.dtree,
        crud = layui.crud,
        $ = layui.$,
        inputTags = layui.inputTags;
    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;

    crud.singleTree('selTree', pmsUrl + '/category/tree/0');


    //监听提交
    form.on('submit(saveBtn)', function (data) {
        var field = data.field;
        var showName = field.showName;
        var checked = dtree.getCheckbarJsonArrParam("selTree"); // 获取当前选中节点
        if (checked.nodeId.length === 0) {
            layer.msg('商品分类不能为空', {
                icon: 5
            });
            return false;
        }
        var obj = {
            categoryId: checked.nodeId[0],
            showNames: showName.split(',').reverse()
        };
        crud.post(pmsUrl + '/attributeType/batchSave', obj, function () {
            layer.close(parentIndex);
        });
        return false;
    });
});
