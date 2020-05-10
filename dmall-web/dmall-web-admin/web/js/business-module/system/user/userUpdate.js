layui.use(['form', 'crud'], function () {
    var form = layui.form,
        crud = layui.crud;


    crud.fillForm(bmsUrl + '/user', 'updateUserForm');

    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        crud.put(bmsUrl + '/user', data.field, function () {
            window.location.href = '/';
        });
        return false;
    });
});
