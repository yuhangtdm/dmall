layui.use(['form', 'crud'], function () {
    var crud = layui.crud;

    var parentIndex = layer.index;
    crud.fillForm(pmsUrl + '/brand/' + id, 'brandDetailForm', parentIndex);
});
