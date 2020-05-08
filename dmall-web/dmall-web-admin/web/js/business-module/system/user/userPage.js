layui.use(['form', 'table', 'crud', 'element'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        crud = layui.crud;

    // 初始化表格数据
    crud.initPage('user', bmsUrl + '/user/page', buildColumn());

    /**
     * 构建table列
     */
    function buildColumn() {
        return [
            [
                {type: 'checkbox', fixed: 'left'},
                {
                    field: 'id', title: 'ID', templet: function (d) {
                        return d.id.toString();
                    }, sort: true
                },
                {field: 'userName', title: '用户名'},
                {field: 'nickName', title: '昵称'},
                {field: 'phone', title: '手机号'},
                {field: 'email', title: '邮箱'},
                {field: 'realName', title: '真实姓名'},
                {field: 'icon', title: '头像'},
                {field: 'warehouseId', title: '仓库id'},
                {field: 'gmtModified', title: '修改时间', templet: "<div>{{layui.crud.formatDate(d.gmtModified)}}</div>"},
                {fixed: 'right', title: '操作', toolbar: '#currentTableBar', width: 150}
            ]
        ];
    }

    // 监听搜索操作
    form.on('submit(formSearch)', function (data) {
        return crud.search('user', data);
    });

    // 监听头工具栏事件
    table.on('toolbar(user)', function (obj) {
        if (obj.event === 'add') {
            crud.open('/page/system/user/userAdd.html', '新增用户');
        }
    });

    //监听行工具事件
    table.on('tool(user)', function (obj) {
        var data = obj.data;
        switch (obj.event) {
            case 'disable':
                crud.confirm("确定禁用该用户?", bmsUrl + '/user/disable/' + data.id);
                break;
            case 'enable':
                crud.confirm("确定启用该用户?", bmsUrl + '/user/enable/' + data.id);
                break;
            case 'resetPassword':
                crud.confirm("确定重置密码?", bmsUrl + '/user/resetPassword/' + data.id);
                break;
        }
    });

});
