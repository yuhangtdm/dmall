layui.use(['form', 'crud'], function () {
    var $ = layui.$,
        form = layui.form,
        crud = layui.crud;

    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;

    crud.get(bmsUrl + '/permission/tab/' + id, function (response) {
        for (var i = 0; i < response.data.length; i++) {
            if (i === 0) {
                $(".layui-tab-title").append("<li class='layui-this'> " + response.data[i].appName + "</li>")
            } else {
                $(".layui-tab-title").append("<li>" + response.data[i].appName + "</li>")
            }
            var str = '';
            for (var j = 0; j < response.data[i].permissions.length; j++) {
                var permission = response.data[i].permissions[j];
                if (j === 10) {
                    str += "<br/><br/>";
                }
                if (permission.checked) {
                    str += "<input type='checkbox' checked lay-skin='primary' value='" + permission.id + "' title='" + permission.name + "' >&nbsp;&nbsp;";
                } else {
                    str += "<input type='checkbox' lay-skin='primary' value='" + permission.id + "' title='" + permission.name + "' >&nbsp;&nbsp;";
                }
            }
            if (i === 0) {
                $(".layui-tab-content").append(
                    "<div class='layui-tab-item layui-form layui-show'>" + str + "</div>"
                );
            } else {
                $(".layui-tab-content").append(
                    "<div class='layui-tab-item layui-form'>" + str + "</div>"
                );
            }
            form.render();
        }
    })

    $("#submit").bind('click', function () {
        var relateIds = [];
        $('input[type="checkbox"]:checked').each(function (index, value) {
            relateIds.push($(value).val());
        });
        var checkObj = {
            id: id,
            relateIds: relateIds
        }
        crud.post(bmsUrl + '/role/setPermission', checkObj, function () {
            layer.close(parentIndex);
        })
    })

});
