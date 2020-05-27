layui.use(['form', 'crud'], function () {
    var crud = layui.crud;

    crud.initSimplePage('setAttribute', pmsUrl + '/attribute/list', buildColumn(), buildQueryObj());

    /**
     * 构建table列
     */
    function buildColumn() {
        return [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'id', title: '属性编号', width: 300},
            {field: 'showName', title: '展示名称', width: 300},
            {
                field: 'canScreen', title: '是否可筛选', width: 300, templet: function (d) {
                    if (d.canScreen){
                        var c1 = d.canScreen === 1;
                        var c2 = d.canScreen === 2;
                        var c3 = d.canScreen === 3;
                        var  result = '';
                        if (c1){
                            result += '<input type="radio" value="1" checked  title="不可筛选">';
                        }else {
                            result += '<input type="radio" value="1"   title="不可筛选">';
                        }
                        if (c2){
                            result += '<input type="radio" checked value="2" title="单选" >';
                        }else {
                            result += '<input type="radio" value="2" title="单选" >';
                        }
                        if (c3){
                            result += '<input type="radio" checked value="3" title="多选">';
                        }else {
                            result += '<input type="radio" value="3" title="多选">';
                        }
                        return result;
                    }else {
                        return '<input type="radio" value="1"  title="不可筛选">' +
                        '<input type="radio" value="2" title="单选" >' +
                        '<input type="radio" value="3" title="多选">';
                    }
                }
            }
        ]];
    }

    function buildQueryObj() {
        return {
            "categoryId": oneLevelId,
            "threeCategoryId": categoryId
        }
    }

});
