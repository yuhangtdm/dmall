layui.use(['form', 'crud'], function () {
    var form = layui.form,
        crud = layui.crud;

    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;
    crud.fillForm(pmsUrl + '/brand/' + id, 'brandUpdateForm', parentIndex);

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        crud.put(pmsUrl + '/brand', data.field, function () {
            layer.close(parentIndex);
        });
        return false;
    });
});
