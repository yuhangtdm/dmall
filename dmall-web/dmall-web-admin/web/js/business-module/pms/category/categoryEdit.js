layui.use(['form', 'crud'], function () {
    var $ = layui.jquery,
        form = layui.form,
        crud = layui.crud;

    // iframe页面代码示例
    /** 获取本页面url中的参数值 */
    var getUrlParam = function(name) {
        var url = location.search;
        url = url.substring(url.indexOf("?"));
        if (url.indexOf("?") !== -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for ( var i = 0; i < strs.length; i++) {
                if (name === strs[i].split("=")[0]) {
                    return unescape(strs[i].split("=")[1]);
                }
            }
        }
        return "";
    }

    var nodeId = getUrlParam("nodeId"),
        parentId = getUrlParam("parentId"),
        context = decodeURI(getUrlParam("context")),  // 注意，此处对context做了一次转码
        leaf = getUrlParam("leaf"),
        level = getUrlParam("level"),
        spread = getUrlParam("spread")

    // 赋值
    form.val("show_form",{
        "nodeId" : nodeId,
        "parentId" : parentId,
        "context" : context,
        "leaf" : leaf,
        "level" : level,
        "spread" : spread
    });
    form.render(); //刷新表单

})
;
