layui.use(['form', 'crud'], function () {
    var crud = layui.crud;

    var parentIndex = layer.index;
    crud.fillForm(bmsUrl + '/role/' + id, 'roleDetailForm', parentIndex);
});
