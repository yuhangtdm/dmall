layui.use(['form', 'crud'], function () {
    var $ = layui.jquery,
        form = layui.form,
        crud = layui.crud;

    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;

    if (parentId) {
        $("input[name='parentId']").val(parentId);
        $("input[name='parentName']").val(parentName);
    }

    crud.initFormSelect('menuAddForm');

    // 图标选择器的使用
    crud.initIcon('icon');

    form.on('select(type)', function (data) {
        if (data.value === '1') {
            $("#openItem").removeClass('layui-hide');
            $("#urlItem").addClass('layui-hide');
            $("#targetItem").addClass('layui-hide');
        } else {
            $("#openItem").addClass('layui-hide');
            $("#urlItem").removeClass('layui-hide');
            $("#targetItem").removeClass('layui-hide');
        }
    });

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        crud.post(bmsUrl + '/menu', data.field, function () {
            layer.close(parentIndex);
        });
        return false;
    });


});
