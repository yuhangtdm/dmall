layui.use(['form', 'crud'], function () {
    var $ = layui.jquery,
        form = layui.form,
        crud = layui.crud;

    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;

    if (parentId) {
        $("input[name='parentName']").val(parentName);
    }

    crud.fillForm(bmsUrl + '/menu/' + id, 'menuUpdateForm');

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        crud.put(bmsUrl + '/menu', data.field, function () {
            layer.close(parentIndex);
        });
        return false;
    });
});
