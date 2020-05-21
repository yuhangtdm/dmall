layui.use(['form', 'crud', 'dtree', 'inputTags'], function () {
    var form = layui.form,
        dtree = layui.dtree,
        crud = layui.crud,
        $ = layui.$,
        inputTags = layui.inputTags;
    // 当前弹出层，防止ID被覆盖
    var parentIndex = layer.index;

    crud.singleTree('selTree', pmsUrl + '/category/tree/0');

    inputTags.render({
        elem: '#inputTags',//定义输入框input对象
        content: ['sss'],//默认标签
        aldaBtn: true,//是否开启获取所有数据的按钮
        done: function (value) { //回车后的回调
        }
    });

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        crud.post(pmsUrl + '/attributeType', data.field, function () {
            layer.close(parentIndex);
        });
        return false;
    });
});
