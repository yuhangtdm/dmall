layui.use(['form', 'table', 'crud', 'element', 'treeTable'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        treeTable = layui.treeTable,
        crud = layui.crud;


    treeTable.render({
        elem: '#menu',
        url: bmsUrl  + '/menu/list',
        headers: {
            source: 'admin',
            token: crud.getToken()
        },
        tree: {
            iconIndex: 2,           // 折叠图标显示在第几列
            isPidData: true,        // 是否是id、pid形式数据
            idName: 'id',  // id字段名称
            pidName: 'parentId'     // pid字段名称
        },
        cols: buildColumn()
    });

    function buildColumn() {
        return [
            [
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', title: 'ID'},
                {field: 'name', title: '名称'},
                {field: 'type', title: '类型'},
                {field: 'url', title: '地址'},
                {field: 'icon', title: '图标'}
                /*{fixed: 'right', title: '操作', toolbar: '#currentTableBar', width: 150}*/
            ]
        ];
    }

});
