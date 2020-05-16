layui.use(['form', 'table', 'crud', 'element'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        crud = layui.crud;

    // 初始化表格数据
    crud.initPage('permission', bmsUrl + '/permission/page', buildColumn());

    crud.initSelect('appId');
    crud.initSelect('method');
    /**
     * 构建table列
     */
    function buildColumn() {
        return [
            [
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', title: '权限编号', sort: true},
                {field: 'name', title: '权限名称'},
                {field: 'appId', title: '服务id'},
                {field: 'uri', title: '权限地址'},
                {field: 'method', title: '请求方式'},
                {field: 'name', title: '权限名称'},
                {field: 'gmtModified', title: '修改时间', templet: "<div>{{layui.crud.formatDate(d.gmtModified)}}</div>"},
                {fixed: 'right', title: '操作', toolbar: '#currentTableBar', width: 250}
            ]
        ];
    }

    // 监听搜索操作
    form.on('submit(formSearch)', function (data) {
        return crud.search('permission', data);
    });

    // 监听头工具栏事件
    table.on('toolbar(permission)', function (obj) {
        if (obj.event === 'add') {
            crud.open('/page/system/permission/permissionAdd.html', '新增权限');
        }else if (obj.event === 'import'){
            crud.confirm('确认导入所有权限?', bmsUrl + '/permission/importAll');
        }
    });

    //监听行工具事件
    table.on('tool(permission)', function (obj) {
        var data = obj.data;
        // 定义全局变量传输到子页面
        id = data.id;
        switch (obj.event) {
            case 'detail':
                crud.open('/page/system/permission/permissionDetail.html', '权限详情');
                break;
            case 'update':
                crud.open('/page/system/permission/permissionUpdate.html', '修改权限');
                break;
            case 'delete':
                crud.delete("确定删除该权限?", bmsUrl + '/permission/' + data.id);
                break;
        }
    });

});
