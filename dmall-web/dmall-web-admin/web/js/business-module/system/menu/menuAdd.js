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


    form.on('select(type)', function (data) {
        if (data.value === '1') {
            $("#openItem").attr('class', "layui-form-item");
            $("#urlItem").attr('class', "layui-form-item layui-hide");
        } else {
            $("#openItem").attr('class', "layui-form-item layui-hide");
            $("#urlItem").attr('class', "layui-form-item");
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
