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
                {field: 'id', title: 'ID', sort: true},
                {field: 'userName', title: '用户名'},
                {field: 'nickName', title: '昵称'},
                {field: 'phone', title: '手机号'},
                {field: 'email', title: '邮箱'},
                {field: 'realName', title: '真实姓名'},
                {field: 'icon', title: '头像'},
                {field: 'warehouseId', title: '仓库id'},
                {field: 'gmtModified', title: '修改时间', templet : "<div>{{layui.crud.formatDate(d.gmtModified)}}</div>"},
                {fixed: 'right', title: '操作', toolbar: '#currentTableBar', width: 150}
            ]
        ];
    }

    // 监听搜索操作
    form.on('submit(formSearch)', function (data) {
        table.reload('user', {
            where: data.field
        });
        //执行搜索重载
       /* table.reload('currentTableId', {

            page: {
                curr: 1
            }
            , where: {
                searchParams: result
            }
        }, 'data');*/

        return false;
    });

    table.on('toolbar(user)', function (obj) {
        if (obj.event === 'add') {
            crud.open('/page/system/user/userAdd.html','新增用户');
        }
    });

});
