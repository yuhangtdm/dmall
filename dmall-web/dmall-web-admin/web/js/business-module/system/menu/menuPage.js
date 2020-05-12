layui.use(['form', 'table', 'crud', 'element', 'treeTable'], function () {
    var $ = layui.jquery,
        form = layui.form,
        treeTable = layui.treeTable,
        crud = layui.crud;

    var options = {
        elem: '#menu',
        url: bmsUrl + '/menu/list',
        headers: {
            source: 'admin',
            token: crud.getToken()
        },
        toolbar: '#toolbarDemo',
        tree: {
            iconIndex: 2,           // 折叠图标显示在第几列
            isPidData: true,        // 是否是id、pid形式数据
            idName: 'id',  // id字段名称
            pidName: 'parentId'     // pid字段名称
        },
        cols: buildColumn()
    };
    // 初始化菜单树
    var menuTreeTable = treeTable.render(options);

    parentId = '';
    parentName = '';
    // 监听头工具栏事件
    treeTable.on('toolbar(menu)', function (obj) {
        if (obj.event === 'add') {
            parentId = '';
            parentName = '';
            crud.open('/page/system/menu/menuAdd.html', '新增菜单/目录', reloadTable);
        }
    });


    //监听行工具事件
    treeTable.on('tool(menu)', function (obj) {
        var data = obj.data;
        // 定义全局变量传输到子页面
        switch (obj.event) {
            case 'add':
                parentId = data.id;
                parentName = data.name;
                crud.open('/page/system/menu/menuAdd.html', '新增子菜单/目录', reloadTable);
                break;
            case 'update':
                id = data.id;
                crud.open('/page/system/menu/menuUpdate.html', '修改子菜单/目录', reloadTable);
                break;
            case 'delete':
                var type = data.type;
                if (type === '1'){
                    crud.delete("确定删除该目录以及子菜单?", bmsUrl + '/menu/' + data.id, reloadTable);
                }else {
                    crud.delete("确定删除该菜单?", bmsUrl + '/menu/' + data.id, reloadTable);
                }
                break;
        }
    });

    function reloadTable() {
        menuTreeTable.reload(options);
    }
    function buildColumn() {
        return [
            [
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', title: 'ID'},
                {field: 'name', title: '名称'},
                {field: 'type', title: '类型'},
                {field: 'url', title: '地址'},
                {field: 'icon', title: '图标'},
                {fixed: 'right', title: '操作', toolbar: '#currentTableBar', width: 200}
            ]
        ];
    }

});
