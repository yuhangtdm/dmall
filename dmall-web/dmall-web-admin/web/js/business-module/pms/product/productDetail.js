layui.use(['form', 'crud', 'table', 'tableMerge'], function () {
    var crud = layui.crud;
    var table = layui.table;
    var tableMerge = layui.tableMerge;

    // 请求数据
    crud.get(pmsUrl + '/product/' + id, function (response) {
        var data = response.data;
        crud.dataForm(data.basicProduct, 'basicProductForm');
        var ext = data.ext;
        crud.dataForm(ext, 'basicProductForm');
        initSpecifications(ext.specifications);
        initSalePoint(ext.salePoints);
        initParam(ext.params);
        initSku(data.skuList);
    })

    /**
     * 初始化规格数据
     */
    function initSpecifications(specifications) {
        var tableData = [];
        for (var i = 0; i < specifications.length; i++) {
            var specification = specifications[i];
            for (var j = 0; j < specification.attributeValues.length; j++) {
                var row = {
                    'attributeName': specification.attributeName,
                    'attributeValue': specification.attributeValues[j].attributeValue,
                    'pic': specification.attributeValues[j].pic
                }
                tableData.push(row);
            }
        }
        table.render({
            elem: '#specificationsTable',
            cols: [[
                {field: 'attributeName', merge: true, title: '规格名称', width: 150},
                {field: 'attributeValue', title: '规格值列表'},
                {field: 'pic', title: '规格值配图', templet: "<div>{{layui.crud.showImg(d.pic)}}</div>"}
            ]],
            data: tableData,
            done: function () {
                tableMerge.render(this)
            }
        });
    }

    /**
     * 初始化卖点数据
     */
    function initSalePoint(salePoints) {
        var tableData = [];
        for (var i = 0; i < salePoints.length; i++) {
            var salePoint = salePoints[i];
            for (var j = 0; j < salePoint.attributeValues.length; j++) {
                var row = {
                    'attributeName': salePoint.attributeName,
                    'attributeValue': salePoint.attributeValues[j].attributeValue
                }
                tableData.push(row);
            }
        }
        table.render({
            elem: '#salePointTable',
            cols: [[
                {field: 'attributeName', merge: true, title: '卖点名称', width: 150},
                {field: 'attributeValue', title: '卖点值列表'}
            ]],
            data: tableData,
            done: function () {
                tableMerge.render(this)
            }
        });
    }

    /**
     * 初始化参数
     */
    function initParam(params) {
        var tableData = [];
        for (var i = 0; i < params.length; i++) {
            var param = params[i];
            for (var j = 0; j < param.params.length; j++) {
                var paramValue = param.params[j];
                for (var k = 0; k < paramValue.attributeValues.length; k++) {
                    var row = {
                        'attributeTypeName': param.attributeTypeName,
                        'attributeName': paramValue.attributeName,
                        'attributeValue': paramValue.attributeValues[k].attributeValue
                    }
                    tableData.push(row);
                }
            }
        }
        table.render({
            elem: '#paramTable',
            cols: [[
                {field: 'attributeTypeName', merge: true, title: '参数类型名称', width: 150},
                {field: 'attributeName', merge: true, title: '参数名称', width: 150},
                {field: 'attributeValue', title: '参数值列表'}
            ]],
            data: tableData,
            done: function () {
                tableMerge.render(this)
            }
        });
    }

    /**
     * 初始化sku列表
     */
    function initSku(skuList) {
        table.render({
            elem: '#skuTable',
            cols: [[
                {field: 'skuId', title: 'sku编号'},
                {field: 'skuName', title: 'sku名称'},
                {field: 'price', title: 'sku价格'},
                {field: 'stock', title: 'sku库存'},
                {field: 'specifications', title: 'sku规格'},
            ]],
            data: skuList
        });
    }

});
