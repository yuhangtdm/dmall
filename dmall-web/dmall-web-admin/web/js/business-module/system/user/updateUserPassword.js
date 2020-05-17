layui.use(['form', 'miniPage', 'crud'], function () {
    var form = layui.form,
        crud = layui.crud,
        miniPage = layui.miniPage;


    //监听提交
    form.on('submit(saveBtn)', function (data) {
        var field = data.field;
        if (field.newPassword !== field.confirmNewPassword){
            crud.error('请确认新密码是否一致');
            return false;
        }
        // 提交请求
        crud.post(bmsUrl + '/user/updatePassword', field, function () {
            window.location.href = 'page/login.html';
        })
        return false;
    });

});