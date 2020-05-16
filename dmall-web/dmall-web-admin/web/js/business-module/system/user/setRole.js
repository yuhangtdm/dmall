layui.use(['form', 'crud'], function () {
    var $ = layui.$,
        form = layui.form,
        crud = layui.crud;

    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;

    crud.get(bmsUrl + '/user/roleList/' + id, function (response) {
        var str = "";
        for (var i = 0; i < response.data.length; i++) {
            var role = response.data[i];
            if (role.checked) {
                str += "<input type='checkbox' checked lay-skin='primary' value='" + role.roleId + "' title='" + role.roleName + "' ><br/><br/>";
            } else {
                str += "<input type='checkbox' lay-skin='primary' value='" + role.roleId + "' title='" + role.roleName + "' ><br/><br/>";
            }
        }
        $("#role").append(str);
        form.render();
    });

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        var relateIds = [];
        $('input[type="checkbox"]:checked').each(function (index, value) {
            relateIds.push($(value).val());
        });
        var roleObj = {
            id: id,
            relateIds: relateIds
        }
        console.log(roleObj);
        crud.post(bmsUrl + '/user/setRole', roleObj, function () {
            layer.close(parentIndex);
        });
        return false;
    });

});
