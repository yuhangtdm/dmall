layui.use(['form', 'table', 'crud'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        crud = layui.crud;

    // 初始化表格数据
    crud.initPage('brand', pmsUrl + '/brand/page', buildColumn());

    /**
     * 构建table列
     */
    function buildColumn() {
        return [
            [
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', title: '品牌编号', sort: true},
                {field: 'logo', title: '品牌logo', templet: "<div>{{layui.crud.showImg(d.logo)}}</div>"},
                {field: 'name', title: '品牌名称'},
                {field: 'englishName', title: '英文名称'},
                {field: 'firstLetter', title: '首字母'},
                {field: 'gmtModified', title: '修改时间', templet: "<div>{{layui.crud.formatDate(d.gmtModified)}}</div>"},
                {fixed: 'right', title: '操作', toolbar: '#currentTableBar', width: 320}
            ]
        ];
    }

    // 监听搜索操作
    form.on('submit(formSearch)', function (data) {
        return crud.search('brand', data);
    });

    // 监听头工具栏事件
    table.on('toolbar(brand)', function (obj) {
        if (obj.event === 'add') {
            crud.open('/page/pms/brand/brandAdd.html', '新增品牌');
        }
    });

    //监听行工具事件
    table.on('tool(brand)', function (obj) {
        var data = obj.data;
        // 定义全局变量传输到子页面
        id = data.id;
        switch (obj.event) {
            case 'detail':
                crud.open('/page/pms/brand/brandDetail.html', '品牌详情');
                break;
            case 'update':
                crud.open('/page/pms/brand/brandUpdate.html', '修改品牌');
                break;
            case 'delete':
                crud.delete("确定删除该品牌?", pmsUrl + '/brand/' + data.id);
                break;
            case 'setCategory':
                crud.openTree('设置商品分类', pmsUrl + '/category/tree/0',
                    pmsUrl + '/brand/getCategory/' + data.id,
                    pmsUrl + '/brand/setCategory');
                break;
        }
    });
})
;
