layui.use(['form', 'table', 'crud', 'element'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        crud = layui.crud;

    crud.initPage('menu', bmsUrl + '/menu/page', buildColumn());

    function buildColumn() {
        return [
            [
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', title: 'ID', sort: true},
                {field: 'name', title: '名称'},
                {field: 'type', title: '类型'},
                {field: 'url', title: '地址'},
                {field: 'icon', title: '图标'},
                {fixed: 'right', title: '操作', toolbar: '#currentTableBar', width: 150}
            ]
        ];
    }

    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        var result = JSON.stringify(data.field);
        layer.alert(result, {
            title: '最终的搜索信息'
        });

        //执行搜索重载
        table.reload('currentTableId', {
            page: {
                curr: 1
            }
            , where: {
                searchParams: result
            }
        }, 'data');

        return false;
    });


});
