layui.use(['form', 'crud'], function () {
    var form = layui.form,
        crud = layui.crud;


    crud.fillForm(bmsUrl + '/role/' + id, 'roleUpdateForm');

    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        crud.put(bmsUrl + '/role', data.field, function () {
            layer.close(parentIndex);
        });
        return false;
    });
});
