layui.use(['form', 'crud', 'table'], function () {
    var crud = layui.crud;
    var table = layui.table;

    var parentIndex = layer.index;

    var data;
    crud.get(pmsUrl + '/product/' + id, function (response) {
        data = response.data;
    })

    // 基本信息
    if (data) {
        crud.dataForm(data.basicProduct, 'basicProductForm');
        crud.dataForm(data.ext, 'basicProductForm');
        if (data.ext) {
            var ext = data.ext;
            var specifications = ext.specifications;
            var tableData = [];
            for (var i = 0; i < specifications.length; i++) {
                var specification = specifications[i];

                for (var j = 0; j < specifications.attributeValues.length; i++) {
                    // specifications.attributeValues[i].attributeValue
                }
               /* var to = {
                    'attributeName': specification.attributeName,
                    'attributeValues':.join(",")
                }*/
                tableData.push(to);
            }
            console.log(tableData)
            table.render({
                elem: '#specificationsTable',
                cols: [
                    {field: 'attributeName', title: '属性名称', width: 150},
                    {field: 'attributeValues', title: '属性值列表'}
                ],
                data: tableData
            });
        }
    }


});
