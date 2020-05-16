layui.use(['form', 'table', 'crud', 'element'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        crud = layui.crud;

    // 初始化表格数据
    crud.initPage('role', bmsUrl + '/role/page', buildColumn());

    /**
     * 构建table列
     */
    function buildColumn() {
        return [
            [
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', title: 'ID', sort: true},
                {field: 'name', title: '角色名称'},
                {field: 'remark', title: '备注'},
                {field: 'gmtModified', title: '修改时间', templet: "<div>{{layui.crud.formatDate(d.gmtModified)}}</div>"},
                {fixed: 'right', title: '操作', toolbar: '#currentTableBar', width: 320}
            ]
        ];
    }

    // 监听搜索操作
    form.on('submit(formSearch)', function (data) {
        return crud.search('role', data);
    });

    // 监听头工具栏事件
    table.on('toolbar(role)', function (obj) {
        if (obj.event === 'add') {
            crud.open('/page/system/role/roleAdd.html', '新增用户');
        }
    });

    //监听行工具事件
    table.on('tool(role)', function (obj) {
        var data = obj.data;
        // 定义全局变量传输到子页面
        id = data.id;
        switch (obj.event) {
            case 'detail':
                crud.open('/page/system/role/roleDetail.html', '角色详情');
                break;
            case 'update':
                crud.open('/page/system/role/roleUpdate.html', '修改角色');
                break;
            case 'delete':
                crud.delete("确定删除该角色?", bmsUrl + '/role/' + data.id);
                break;
            case 'setMenu':
                crud.openTree('选择菜单',
                    bmsUrl + '/menu/tree',
                    bmsUrl + '/role/getMenus/' + data.id,
                    bmsUrl + '/role/setMenu');
                break;
            case 'setPermission':
                crud.openT('/page/system/role/setPermission.html', '设置权限');
                break;
        }
    });

})
;
