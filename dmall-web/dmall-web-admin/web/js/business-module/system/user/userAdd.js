layui.use(['form', 'crud'], function () {
    var form = layui.form,
        crud = layui.crud;

    /**
     * 初始化表单，要加上，不然刷新部分组件可能会不加载
     */
    form.render();

    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        crud.post(bmsUrl + '/user', data.field, function () {
            layer.close(parentIndex);
        });
        return false;
    });
});
