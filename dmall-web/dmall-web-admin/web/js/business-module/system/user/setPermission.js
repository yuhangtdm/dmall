layui.use(['form', 'crud'], function () {
    var $ = layui.$,
        form = layui.form,
        crud = layui.crud;

    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;

    crud.get(bmsUrl + '/user/tab/' + id, function (response) {
        for (var i = 0; i < response.data.length; i++) {
            // 生成tab
            if (i === 0) {
                $(".layui-tab-title").append("<li class='layui-this'> " + response.data[i].appName + "</li>")
            } else {
                $(".layui-tab-title").append("<li>" + response.data[i].appName + "</li>")
            }
            // 生成tab里的内容
            var str = "";
            for (var j = 0; j < response.data[i].businessList.length; j++) {
                var business = response.data[i].businessList[j];
                str += business.name + "&nbsp;&nbsp;&nbsp;&nbsp;";
                for (var k = 0; k < business.permissions.length; k++) {
                    if (k !== 0 && k % 10 === 0) {
                        str += "<br/><br/>";
                    }
                    var permission = business.permissions[k];
                    if (permission.checked) {
                        str += "<input type='checkbox' checked lay-skin='primary' value='" + permission.id + "' title='" + permission.name + "' >&nbsp;&nbsp;";
                    } else {
                        str += "<input type='checkbox' lay-skin='primary' value='" + permission.id + "' title='" + permission.name + "' >&nbsp;&nbsp;";
                    }
                }
                str += "<br/><br/>";
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
    }, parentIndex)

    $("#submit").bind('click', function () {
        var relateIds = [];
        $('input[type="checkbox"]:checked').each(function (index, value) {
            relateIds.push($(value).val());
        });
        var checkObj = {
            id: id,
            relateIds: relateIds
        }
        crud.post(bmsUrl + '/user/setPermission', checkObj, function () {
            layer.close(parentIndex);
        })
    })

});
