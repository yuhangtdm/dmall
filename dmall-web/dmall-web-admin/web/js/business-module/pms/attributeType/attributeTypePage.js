layui.use(['form', 'table', 'crud', 'dtree',], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        dtree = layui.dtree,
        crud = layui.crud;

    // 初始化表格数据
    crud.initPage('attributeType', pmsUrl + '/attributeType/page', buildColumn());

    // 单选
    crud.selectTree('selTree', pmsUrl + '/category/tree/0/4', 'only');

    /**
     * 构建table列
     */
    function buildColumn() {
        return [
            [
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', title: '属性类别编号', sort: true},
                {field: 'showName', title: '展示名称'},
                {field: 'sort', title: '排序'},
                {field: 'gmtModified', title: '修改时间', templet: "<div>{{layui.crud.formatDate(d.gmtModified)}}</div>"},
                {fixed: 'right', title: '操作', toolbar: '#currentTableBar', width: 200}
            ]
        ];
    }

    // 监听搜索操作
    form.on('submit(formSearch)', function (data) {
        var checked = dtree.getCheckbarJsonArrParam("selTree"); // 获取当前选中节点
        if (checked.nodeId.length !== 0) {
            data.field.categoryId = checked.nodeId[0];
        }
        return crud.search('attributeType', data);
    });

    // 监听头工具栏事件
    table.on('toolbar(attributeType)', function (obj) {
        if (obj.event === 'add') {
            crud.open('/page/pms/attributeType/attributeTypeAdd.html', '新增属性类别');
        }
    });

    //监听行工具事件
    table.on('tool(attributeType)', function (obj) {
        var data = obj.data;
        // 定义全局变量传输到子页面
        id = data.id;
        switch (obj.event) {
            case 'detail':
                crud.open('/page/pms/attributeType/attributeTypeDetail.html', '属性类别详情');
                break;
            case 'update':
                crud.open('/page/pms/attributeType/attributeTypeUpdate.html', '修改属性类别');
                break;
            case 'delete':
                crud.delete("确定删除该属性类别?", pmsUrl + '/attributeType/' + data.id);
                break;
        }
    });
})
;
