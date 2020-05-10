layui.use(['form', 'crud'], function () {
    var crud = layui.crud;
    crud.fillForm(bmsUrl + '/user/' + id, 'userDetailForm');
});
