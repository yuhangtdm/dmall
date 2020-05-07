layui.use(['jquery', 'miniAdmin', 'crud'], function () {
    var $ = layui.jquery,
        crud = layui.crud,
        miniAdmin = layui.miniAdmin;

    var options = {
        iniUrl: webAdminUrl + "/index",    // 初始化接口
        clearUrl: "api/clear.json", // 缓存清理接口
        renderPageVersion: true,    // 初始化页面是否加版本号
        bgColorDefault: false,      // 主题默认配置
        multiModule: true,          // 是否开启多模块
        menuChildOpen: false,       // 是否默认展开菜单
        loadingTime: 0,             // 初始化加载时间
        pageAnim: true,             // 切换菜单动画
    };
    miniAdmin.render(options);

    $('.login-out').on("click", logout);

    /**
     * 退出登录
     */
    function logout() {
        var token = crud.getToken();
        crud.get(ssoUrl + '/admin/logout?token=' + token, logoutSuccess);
    }

    function logoutSuccess(response) {
        if (response.result) {
            crud.msgBack('退出登录成功', function () {
                layui.data('token', null);
                window.location.href = 'page/login.html';
            })
        } else {
            crud.error(response.msg);
        }
    }
});
