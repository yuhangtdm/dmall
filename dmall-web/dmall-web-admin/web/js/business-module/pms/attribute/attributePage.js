layui.use(['form', 'table', 'crud', 'dtree',], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        dtree = layui.dtree,
        crud = layui.crud;

    // 初始化表格数据
    crud.initPage('attribute', pmsUrl + '/attribute/page', buildColumn());

    crud.selectTree('selTree', pmsUrl + '/category/tree/0/2', 'only');

    crud.initFormSelect('attributeSearchForm');

    /**
     * 构建table列
     */
    function buildColumn() {
        return [
            [
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', title: '属性编号', sort: true},
                {field: 'showName', title: '展示名称'},
                {field: 'type', title: '类型', templet: "<div>{{layui.crud.getDesc(d.type,'TypeEnum')}}</div>"},
                {
                    field: 'inputType',
                    title: '属性录入方式',
                    templet: "<div>{{layui.crud.getDesc(d.inputType,'InputTypeEnum')}}</div>"
                },
                {
                    field: 'handAddStatus',
                    title: '是否支持手动新增',
                    templet: "<div>{{layui.crud.getDesc(d.handAddStatus,'HandAddStatusEnum')}}</div>"
                },
                {field: 'gmtModified', title: '修改时间', templet: "<div>{{layui.crud.formatDate(d.gmtModified)}}</div>"},
                {fixed: 'right', title: '操作', toolbar: '#currentTableBar', width: 320}
            ]
        ];
    }

    // 监听搜索操作
    form.on('submit(formSearch)', function (data) {
        var checked = dtree.getCheckbarJsonArrParam("selTree");
        if (checked.nodeId.length !== 0) {
            data.field.categoryId = checked.nodeId[0];
        } else {
            data.field.categoryId = null;
        }
        return crud.search('attribute', data);
    });

    // 监听头工具栏事件
    table.on('toolbar(attribute)', function (obj) {
        if (obj.event === 'add') {
            crud.open('/page/pms/attribute/attributeAdd.html', '新增属性');
        }
    });

    //监听行工具事件
    table.on('tool(attribute)', function (obj) {
        var data = obj.data;
        // 定义全局变量传输到子页面
        id = data.id;
        switch (obj.event) {
            case 'detail':
                crud.open('/page/pms/attribute/attributeDetail.html', '属性详情');
                break;
            case 'update':
                crud.open('/page/pms/attribute/attributeUpdate.html', '修改属性');
                break;
            case 'delete':
                crud.delete("确定删除该属性?", pmsUrl + '/attribute/' + data.id);
                break;
            // case 'setCategory':
            //     type = data.type;
            //     categoryId = data.categoryId;
            //     crud.open('/page/pms/attribute/setCategory.html', '设置商品分类');
            //     break;
        }
    });
})
;
