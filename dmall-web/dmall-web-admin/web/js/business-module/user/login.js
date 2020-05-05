layui.use(['form', 'crud'], function () {
    var form = layui.form,
        $ = layui.$,
        crud = layui.crud;

    // 登录过期的时候，跳出iframe框架
    if (top.location !== self.location) top.location = self.location;

    // 粒子线条背景
    $(function () {
        $('.layui-container').particleground({
            dotColor: '#7ec7fd',
            lineColor: '#7ec7fd'
        });
    })

    // 进行登录操作
    form.on('submit(login)', function (data) {
        form = data.field;
        var loginObj = {
            'userName': form.userName,
            'password': form.password,
            'rememberMe': form.rememberMe
        };
        crud.post(ssoUrl +'/admin/login', loginObj, loginSuccess);
        return false;
    });

    /**
     * 登录成功执行的方法
     */
    function loginSuccess(response) {
        if (response.code === '0') {
            // 设置token
            var obj = {
                key: 'value',
                value: response.data.token
            }
            if (form.rememberMe === 'true') {
                layui.data('token', obj);
            } else {
                // 页面关闭后失效
                layui.sessionData('token', obj);
            }
            crud.msgBack('登录成功', function () {
                window.location.href = '/index.html';
            });
        } else {
           crud.error(response.msg);
        }
    }

});

