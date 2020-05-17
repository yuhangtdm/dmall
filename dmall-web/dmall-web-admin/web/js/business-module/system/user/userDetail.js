layui.use(['form', 'crud'], function () {
    var crud = layui.crud;
    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;
    crud.fillForm(bmsUrl + '/user/' + id, 'userDetailForm', parentIndex);
});
