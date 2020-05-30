layui.use(['form', 'table', 'crud', 'element', 'treeTable'], function () {
    var treeTable = layui.treeTable,
        crud = layui.crud;

    var options = {
        elem: '#menu',
        url: bmsUrl + '/menu/tree',
        headers: {
            source: 'admin',
            token: crud.getToken()
        },
        toolbar: '#toolbarDemo',
        tree: {
            iconIndex: 2,           // 折叠图标显示在第几列
            isPidData: true,        // 是否是id、pid形式数据
            idName: 'id',  // id字段名称
            pidName: 'parentId',
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
        id = data.id;
        // 定义全局变量传输到子页面
        switch (obj.event) {
            case 'detail':
                parentId = data.id;
                parentName = data.name;
                crud.open('/page/system/menu/menuDetail.html', '菜单/目录详情', reloadTable);
                break;
            case 'add':
                parentId = data.id;
                parentName = data.name;
                crud.open('/page/system/menu/menuAdd.html', '新增子菜单/目录', reloadTable);
                break;
            case 'update':
                if (data.basicData.type === 1) {
                    crud.open('/page/system/menu/catalogUpdate.html', '修改目录', reloadTable);
                } else {
                    crud.open('/page/system/menu/menuUpdate.html', '修改菜单', reloadTable);
                }
                break;
            case 'delete':
                if (data.type === 1) {
                    crud.delete("确定删除该目录以及子菜单?", bmsUrl + '/menu/' + data.id, reloadTable);
                } else {
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
                {field: 'id', title: '菜单编号'},
                {field: 'title', title: '名称'},
                {field: 'icon', title: '图标', templet: '<div><i class="layui-icon {{d.basicData.icon}}"></i></div>'},
                {field: 'basicData.type', title: '类型', templet: "<div>{{layui.crud.getDesc(d.basicData.type,'MenuTypeEnum')}}</div>"},
                {field: 'basicData.url', title: '地址',  templet: "<div>{{d.basicData.url === null ? '' : d.basicData.url}}</div>"},
                {field: 'basicData.target', title: '菜单打开方式',  templet: "<div>{{d.basicData.target === null ? '' : d.basicData.target}}</div>"},
                {fixed: 'basicData.right', title: '操作', toolbar: '#currentTableBar', width: 200}
            ]
        ];
    }

});
