layui.use(['form', 'crud'], function () {
    var crud = layui.crud;

    var parentIndex = layer.index;
    crud.fillForm(pmsUrl + '/attribute/' + id, 'attributeDetailForm', parentIndex);
});
