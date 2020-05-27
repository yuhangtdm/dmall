layui.use(['form', 'crud', 'dtree'], function () {
    var form = layui.form,
        dtree = layui.dtree,
        crud = layui.crud,
        $ = layui.$;

    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;

    if (type === 1) {
        // 单选
        crud.selectTree('category', pmsUrl + '/category/tree/' + categoryId + '/4', 'only',
            pmsUrl + '/attribute/getCategoryIds/' + id);
    } else {
        // 多选
        crud.selectTree('category', pmsUrl + '/category/tree/' + categoryId + '/4', 'all',
            pmsUrl + '/attribute/getCategoryIds/' + id);
    }

    crud.initSelect('canScreen');

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        crud.post(pmsUrl + '/attribute', data.field, function () {
            layer.close(parentIndex);
        });
        return false;
    });
});
