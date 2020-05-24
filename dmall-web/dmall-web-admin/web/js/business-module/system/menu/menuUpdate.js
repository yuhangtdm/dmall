layui.use(['form', 'crud', 'iconPicker'], function () {
    var $ = layui.jquery,
        form = layui.form,
        crud = layui.crud,
        iconPicker = layui.iconPicker;

    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;

    if (parentId) {
        $("input[name='parentName']").val(parentName);
    }

    crud.initIcon('icon');
    crud.get(bmsUrl + '/menu/' + id, function (response) {
        crud.dataForm(response.data, 'menuUpdateForm');
        iconPicker.checkIcon('icon', response.data.icon);
    }, parentIndex)

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        crud.put(bmsUrl + '/menu', data.field, function () {
            layer.close(parentIndex);
        });
        return false;
    });
});
