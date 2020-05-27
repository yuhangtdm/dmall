layui.use(['form', 'crud'], function () {
    var form = layui.form,
        crud = layui.crud,
        $ = layui.$;

    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;

    crud.fillForm(pmsUrl + '/attribute/' + id, 'attributeUpdateForm', parentIndex, function (response) {
        if (response.data.inputType === 2) {
            $("#inputListItem").removeClass('layui-hide');
            $("#handAddStatusItem").removeClass('layui-hide');
        } else {
            $("#inputListItem").addClass('layui-hide');
            $("#handAddStatusItem").addClass('layui-hide');
        }
    });


    // 控制可选值列表的展示
    form.on('select(inputType)', function (data) {
        if (data.value === '2') {
            $("#inputListItem").removeClass('layui-hide');
            $("#handAddStatusItem").removeClass('layui-hide');
        } else {
            $("#inputListItem").addClass('layui-hide');
            $("#handAddStatusItem").addClass('layui-hide');
        }
    });

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        var field = data.field;
        if (field.inputType === '2') {
            if (field.handAddStatus === '') {
                layer.msg('是否支持手动新增不能为空', {
                    icon: 5
                });
                return false;
            }
            if (field.inputList.trim() === '') {
                layer.msg('可选值列表不能为空', {
                    icon: 5
                });
                return false;
            }
        }
        crud.put(pmsUrl + '/attribute', data.field, function () {
            layer.close(parentIndex);
        });
        return false;
    });
});
