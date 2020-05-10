layui.use(['form', 'crud'], function () {
    var crud = layui.crud;
    crud.fillForm(bmsUrl + '/role/' + id, 'roleDetailForm');
});
