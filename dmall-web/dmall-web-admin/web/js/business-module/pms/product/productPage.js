layui.use(['form', 'table', 'crud'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        crud = layui.crud;

    // 初始化表格数据
    crud.initPage('product', pmsUrl + '/product/page', buildColumn());

    /**
     * 构建table列
     */
    function buildColumn() {
        return [
            [
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', title: '商品编号', sort: true},
                {field: 'pic', title: '商品图片', templet: "<div>{{layui.crud.showImg(d.pic)}}</div>"},
                {field: 'name', title: '商品名称'},
                {field: 'brandName', title: '商品名称'},
                {field: 'unit', title: '单位'},
                {field: 'weight', title: '重量'},
                {field: 'onMarketTime', title: '上市时间', templet: "<div>{{layui.crud.formatDate(d.onMarketTime,'yyyy-MM-dd')}}</div>"},
                {field: 'gmtModified', title: '修改时间', templet: "<div>{{layui.crud.formatDate(d.gmtModified)}}</div>"},
                {fixed: 'right', title: '操作', toolbar: '#currentTableBar', width: 350}
            ]
        ];
    }

    // 监听搜索操作
    form.on('submit(formSearch)', function (data) {
        return crud.search('product', data);
    });

    // 监听头工具栏事件
    table.on('toolbar(product)', function (obj) {
        if (obj.event === 'add') {
            crud.openT('/page/pms/product/productAdd.html', '新增商品');
        }
    });

    //监听行工具事件
    table.on('tool(product)', function (obj) {
        var data = obj.data;
        // 定义全局变量传输到子页面
        id = data.id;
        switch (obj.event) {
            case 'detail':
                crud.open('/page/pms/product/productDetail.html', '商品详情');
                break;
            case 'update':
                crud.open('/page/pms/product/productUpdate.html', '修改商品');
                break;
            case 'delete':
                crud.delete("确定删除该商品?", pmsUrl + '/product/' + data.id);
                break;
        }
    });
})
;
