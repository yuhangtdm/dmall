layui.define(['layer', 'table', 'form', 'miniPage', 'formSelects', 'laydate', 'iconPicker', 'dtree'], function (e) {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var miniPage = layui.miniPage;
    var form = layui.form;
    var formSelects = layui.formSelects;
    var laydate = layui.laydate;
    var iconPicker = layui.iconPicker;
    var dtree = layui.dtree;

    // 请求后台的统一地址前缀
    window.requestUrl = 'http://localhost:7010';
    window.bmsUrl = requestUrl + '/dmall-service-impl-background';
    window.cartUrl = requestUrl + '/dmall-service-impl-cart';
    window.mmsUrl = requestUrl + '/dmall-service-impl-member';
    window.orderUrl = requestUrl + '/dmall-service-impl-order';
    window.payUrl = requestUrl + '/dmall-service-impl-pay';
    window.pmsUrl = requestUrl + '/dmall-service-impl-product';
    window.searchUrl = requestUrl + '/dmall-service-impl-search';
    window.ssoUrl = requestUrl + '/dmall-service-impl-sso';
    window.webAdminUrl = requestUrl + '/dmall-web-admin';

    var obj = {
        get: function (url, callBack, parentIndex) {
            get(url, callBack, parentIndex);
        },
        post: function (url, requestData, callBack) {
            post(url, requestData, callBack);
        },
        put: function (url, requestData, callBack) {
            put(url, requestData, callBack);
        },
        delete: function (text, url, endCallback) {
            deleteById(text, url, endCallback);
        },
        confirm: function (text, url) {
            confirm(text, url);
        },
        msg: function (content) {
            msg(content);
        },
        msgBack: function (content, callback) {
            msgContentBack(content, callback);
        },
        error: function (content) {
            error(content);
        },
        errorBack: function (content, callback) {
            errorBack(content, callback);
        },
        getToken: function () {
            return getToken();
        },
        initPage: function (id, url, cols) {
            initPage(id, url, cols);
        },
        open: function (href, title, endCallback) {
            open(href, title, endCallback);
        },
        openT: function (href, title, endCallback) {
            openT(href, title, endCallback);
        },
        openTree: function (title, treeUrl, checkedUrl, confirmUrl) {
            openTree(title, treeUrl, checkedUrl, confirmUrl);
        },
        search: function (tableId, data) {
            return search(tableId, data);
        },
        fillForm(url, formId, parentIndex) {
            fillForm(url, formId, parentIndex);
        },
        dataForm(data, formId) {
            dataForm(data, formId);
        },
        formatDate(date) {
            return formatDate(date);
        },
        initSelect(selectId) {
            initSelect(selectId);
        },
        initFormSelect(formId) {
            initFormSelect(formId);
        },
        initDate(domId, format, type) {
            initDate(domId, format, type);
        },
        initFormDate(formId, format, type) {
            initFormDate(formId, format, type);
        },
        showImg(imgUrl) {
            return showImg(imgUrl);
        },
        showIcon(id) {
            showIcon(id);
        },
        getDesc(code, selectName) {
            return getDesc(code, selectName);
        },
        toJson: function (formId) {
            return toJson(formId);
        }
    }

    /**
     * get请求
     */
    function get(url, callback, parentIndex) {
        $.ajax({
            type: 'get',
            url: url,
            async: false,
            beforeSend: function (request) {
                layer.load(1);
                request.setRequestHeader("source", "admin");
                request.setRequestHeader("token", getToken());
            },
            success: function (response) {
                if (response.code === '0') {
                    callback(response);
                } else if (response.code === '408') {
                    // 用户未登陆 跳转登录页面
                    errorBack('登录已失效', function () {
                        window.location.href = 'page/login.html';
                    });
                } else {
                    errorBack(response.msg, function () {
                        layer.close(parentIndex);
                    });
                }
            },
            complete: function () {
                layer.closeAll('loading');
            },
            error: function () {
                layer.msg('服务器冒烟了', {icon: 2})
            }
            ,
        })
        ;
    }

    /**
     * post请求
     * loading成功 要求请求必须异步
     */
    function post(url, requestData, callback) {
        var loading;
        $.ajax({
            type: 'POST',
            url: url,
            async: true,
            data: JSON.stringify(requestData),
            contentType: 'application/json;charset=utf-8',
            beforeSend: function (request) {
                loading = layer.msg('正在提交', {icon: 16, shade: 0.3, time: 0});
                request.setRequestHeader("source", "admin");
                request.setRequestHeader("token", getToken());
            },
            success: function (response) {
                if (response.code === '0') {
                    msgBack(response, callback);
                } else if (response.code === '408') {
                    // 用户未登陆 跳转登录页面
                    errorBack('登录已失效', function () {
                        window.location.href = 'page/login.html';
                    });
                } else {
                    error(response.msg);
                }
            },
            complete: function () {
                layer.close(loading);
            },
            error: function () {
                layer.msg('服务器冒烟了', {icon: 2})
            },
        });
    }

    /**
     *put请求
     */
    function put(url, requestData, callback) {
        var loading;
        $.ajax({
            type: 'PUT',
            url: url,
            data: JSON.stringify(requestData),
            contentType: 'application/json;charset=utf-8',
            async: true,
            beforeSend: function (request) {
                loading = layer.msg('正在提交', {icon: 16, shade: 0.3, time: 0});
                request.setRequestHeader("source", "admin");
                request.setRequestHeader("token", getToken());
            },
            success: function (response) {
                if (response.code === '0') {
                    msgBack(response, callback);
                } else if (response.code === '408') {
                    // 用户未登陆 跳转登录页面
                    errorBack('登录已失效', function () {
                        window.location.href = 'page/login.html';
                    });
                } else {
                    error(response.msg);
                }
            },
            complete: function () {
                layer.close(loading);
            },
            error: function () {
                layer.msg('服务器冒烟了', {icon: 2})
            },
        });

    }

    /**
     * 删除操作
     */
    function deleteById(text, url, endCallback) {
        layer.confirm(text, {icon: 3, title: '提示'}, function (index) {
            deleteOperation(url, function () {
                layer.close(index);
                if (endCallback) {
                    endCallback();
                } else {
                    $("button[lay-filter='formSearch']").click();
                }
            });
        });
    }

    /**
     * 删除
     */
    function deleteOperation(url, callback) {
        var loading;
        $.ajax({
            type: 'DELETE',
            url: url,
            async: false,
            beforeSend: function (request) {
                loading = layer.msg('正在提交', {icon: 16, shade: 0.3, time: 0});
                request.setRequestHeader("source", "admin");
                request.setRequestHeader("token", getToken());
            },
            success: function (response) {
                if (response.code === '0') {
                    msgBack(response, callback);
                } else if (response.code === '408') {
                    // 用户未登陆 跳转登录页面
                    errorBack('登录已失效', function () {
                        window.location.href = 'page/login.html';
                    });
                } else {
                    error(response.msg);
                }
            },
            complete: function () {
                layer.close(loading);
            },
            error: function () {
                layer.msg('服务器冒烟了', {icon: 2})
            },
        });
    }

    /**
     * 确认框
     */
    function confirm(text, url) {
        layer.confirm(text, {icon: 3, title: '提示'}, function (index) {
            layer.close(index);
            get(url, function () {
                //  刷新页面的搜索
                $("button[lay-filter='formSearch']").click();
            });
        });
    }

    /**
     * 成功提示 默认弹出1秒
     */
    function msg(content) {
        layer.msg(content, {
            icon: 1,
            time: 1000
        });
    }

    /**
     * 成功提示 默认弹出1秒后执行回调函数
     * 要求 先弹出消息 再关闭弹窗 必须是如下的写法
     */
    function msgBack(response, callback) {
        layer.msg(response.msg, {
            icon: 1,
            time: 1000
        }, function () {
            callback(response);
        });
    }

    function msgContentBack(content, callback) {
        layer.msg(content, {
            icon: 1,
            time: 1000
        }, callback);
    }

    /**
     * 错误提示 默认弹出3秒
     */
    function error(content) {
        layer.msg(content, {icon: 2})
    }

    /**
     * 错误提示 默认弹出1秒后执行回调函数
     */
    function errorBack(content, callback) {
        layer.msg(content, {
            icon: 2,
            time: 2000
        }, callback);
    }

    /**
     * 获取token
     */
    function getToken() {
        if (layui.data('token').value && layui.data('token').value !== undefined) {
            return layui.data('token').value;
        }
    }

    /**
     * 公共表格初始化方法
     */
    function initPage(id, url, cols) {
        table.render({
            elem: '#' + id,
            id: id,
            url: url,
            method: 'post',
            contentType: 'application/json',
            headers: {
                source: 'admin',
                token: getToken()
            },
            cellMinWidth: 100,
            toolbar: '#toolbarDemo',
            text: {
                none: '暂无相关数据'
            },
            cols: cols,
            page: true,
            loading: true,
            request: {
                pageName: 'current',
                limitName: 'size'
            },
            done: function (res, curr, count) {
                if (res.code === '408') {
                    // 用户未登陆 跳转登录页面
                    errorBack('登录已失效', function () {
                        window.location.href = 'page/login.html';
                    })
                }
            },
            parseData: function (res) { //res 即为原始返回的数据
                if (res.data != null) {
                    return {
                        "code": res.code, //解析接口状态
                        "msg": res.msg, //解析提示文本
                        "count": res.data.count, //解析数据长度
                        "data": res.data.data //解析数据列表
                    }
                }
            }
        });
    }

    /**
     * 格式化时间
     */
    function formatDate(date) {
        if (date) {
            return layui.util.toDateString(date, 'yyyy-MM-dd HH:mm:ss');
        } else {
            return '';
        }
    }

    /**
     * 打开页面
     * @param href 页面地址
     * @param title 页面标题
     * @param endCallBack 关闭页面的回调函数
     */
    function open(href, title, endCallBack) {
        var content = miniPage.getHrefContent(href);
        var openWH = miniPage.getOpenWidthHeight();
        layer.open({
            title: title,
            type: 1,
            shade: 0.2,
            maxmin: true,
            shadeClose: true,
            area: [openWH[0] + 'px', openWH[1] + 'px'],
            offset: [openWH[2] + 'px', openWH[3] + 'px'],
            content: content,
            end: function () {
                //  刷新页面的搜索
                if (endCallBack) {
                    endCallBack();
                } else {
                    $("button[lay-filter='formSearch']").click();
                }
            }
        });
    }

    function openT(href, title, endCallBack) {
        var content = miniPage.getHrefContent(href);
        var openWH = miniPage.getOpenWidthHeight();
        layer.open({
            title: title,
            type: 1,
            shade: 0.2,
            maxmin: true,
            shadeClose: true,
            area: [openWH[0] + 'px', openWH[1] + 'px'],
            offset: [openWH[2] + 'px', openWH[3] + 'px'],
            content: content,
            btn: ['提交'],
            yes: function (index, layero) {
                //获取子页面的body元素
                layero.find('#submit').click();
            },
            // 层销毁后会回调
            end: function () {
                //  刷新页面的搜索
                if (endCallBack) {
                    endCallBack();
                } else {
                    $("button[lay-filter='formSearch']").click();
                }
            }
        });
    }

    /**
     * 打开带复选框的树
     * @param title 弹框的标题
     * @param treeUrl 树数据源的地址
     * @param checkedUrl 选中框数据源的地址
     * @param confirmUrl 确认选择的地址
     */
    function openTree(title, treeUrl, checkedUrl, confirmUrl) {
        layer.open({
            type: 1,
            title: title,
            area: ["400px", "80%"],
            content: '<ul id="openTree" class="dtree" data-id="0"></ul>',
            btn: ['确认选择'],
            success: function (layero, index) {
                var DTree = dtree.render({
                    elem: "#openTree",
                    url: treeUrl,
                    method: 'get',
                    headers: {
                        "source": "admin",
                        "token": getToken()
                    },
                    checkbar: true,
                    dataStyle: "layuiStyle",
                    skin: "laySimple",
                    response: {statusCode: '0', title: "name"},
                    success: function (response) {
                        if (response.code === '0') {
                            checkArr(response.data, checkedUrl);
                        }
                    }
                });
            }
            ,
            yes: function (index, layero) {
                var checked = dtree.getCheckbarJsonArrParam("openTree"); // 获取当前选中节点
                // 构建选中的数据
                var checkedObj = {
                    id: id,
                    relateIds: checked.nodeId
                };
                post(confirmUrl, checkedObj, function () {
                    layer.close(index);
                })
            }
        });
    }

    /**
     * 复选框的处理
     */
    function checkArr(arr, checkedUrl) {
        get(checkedUrl, function (response) {
            doCheck(arr, response.data);
        })
    }

    /**
     * 树 默认打开 复选框根据后台数据进行处理
     */
    function doCheck(arr, data) {
        for (var i = 0; i < arr.length; i++) {
            var checkArr = [];
            var checkObj = {
                'type': '0'
            };
            if (contains(data, arr[i].id)) {
                checkObj.checked = '1';
            } else {
                checkObj.checked = '0';
            }
            checkArr.push(checkObj);
            arr[i].checkArr = checkArr;
            arr[i].spread = true;
            if (arr[i].children) {
                doCheck(arr[i].children, data);
            }
        }
    }

    /**
     * list.contains
     */
    function contains(a, obj) {
        var i = a.length;
        while (i--) {
            if (a[i].toString() === obj.toString()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 搜索的公共方法
     */
    function search(tableId, data) {
        table.reload(tableId, {
            page: {
                curr: 1
            },
            where: data.field,
        });
        return false;
    }

    /**
     * 为表单填充值
     */
    function fillForm(url, formId, parentIndex) {
        $.ajax({
            type: 'get',
            url: url,
            beforeSend: function (request) {
                request.setRequestHeader("source", "admin");
                request.setRequestHeader("token", getToken());
            },
            success: function (response) {
                if (response.code === '0') {
                    // 进行数据填充
                    initForm(response.data, formId);
                    // 初始化下拉框
                    initFormSelect(formId);
                    // 初始化日期
                    initFormDate(formId);
                    form.render();
                } else if (response.code === '408') {
                    layer.close(parentIndex);
                    // 用户未登陆 跳转登录页面
                    errorBack('登录已失效', function () {
                        window.location.href = 'page/login.html';
                    });
                } else {
                    layer.close(parentIndex);
                    error(response.msg);
                }
            },
            error: function () {
                layer.msg('服务器冒烟了', {icon: 2});
                layer.close(parentIndex);
            },
        });
    }

    /**
     * 为表单填充值
     */
    function dataForm(data, formId) {
        // 进行数据填充
        initForm(data, formId);
        // 初始化下拉框
        initFormSelect(formId);
        // 初始化日期
        initFormDate(formId);
        form.render();
    }

    /**
     * 为表单赋值
     */
    function initForm(bean, formId) {
        for (var prop in bean) {
            var value = bean[prop];
            // null过滤
            if (Object.prototype.toString.call(value) === "[object Null]") {
                continue;
            }
            // 对象递归调用该方法
            if (Object.prototype.toString.call(value) === "[object Object]") {
                initForm(value, formId);
            }
            initData(prop, value, formId);
        }
    }

    /**
     * 表单内简单类型的赋值
     */
    function initData(prop, value, formId) {
        $("#" + formId).find("[name='" + prop + "'],[name='" + prop + "[]']").each(function () {
            var tagName = $(this)[0].tagName;
            var type = $(this).attr('type');
            // input标签
            if (tagName === 'INPUT') {
                // 单选框
                if (type === 'radio') {
                    $(this).attr('checked', value === $(this).val());
                } else if (type === 'checkbox') {
                    //复选框支持数组和字符串以逗号分隔两种
                    if (Object.prototype.toString.call(value) === "[object Array]") {
                        // 数组
                        for (var i = 0; i < value.length; i++) {
                            if ($(this).val() === value[i]) {
                                $(this).attr('checked', true);
                            }
                        }
                    } else {
                        var values = value.split(",");
                        for (var j = 0; j < values.length; j++) {
                            if ($(this).val() === values[j]) {
                                $(this).attr('checked', true);
                            }
                        }
                    }
                } else {
                    $(this).val(value);
                }
            } else if (tagName === 'IMG') {
                // 图片
                $(this).attr('src', value);
            } else if (tagName === 'SELECT') {
                //多选框支持数组和字符串以逗号分隔两种
                if (Object.prototype.toString.call(value) === "[object Array]") {
                    var selectValue = '';
                    for (var k = 0; k < value.length; k++) {
                        selectValue = value[k] + ",";
                    }
                    $(this).attr('value', selectValue.substring(0, selectValue.length - 1));
                } else {
                    $(this).attr('value', value);
                }
            } else if (tagName === 'TEXTAREA') {
                // 文本域
                $(this).val(value);
            }
        });
    }

    /**
     * 初始化表单的所有下拉框 包括单选 多选 联动选择  树
     */
    function initFormSelect(formId) {
        $("#" + formId).find("select").each(function () {
            select($(this));
        });
    }

    /**
     * 初始化下拉框 包括单选 多选 联动选择  树
     */
    function initSelect(selectId) {
        select($("#" + selectId));
    }

    /**
     * 下拉框具体的加载方法
     */
    function select(sel) {
        var value = sel.val() || sel.attr("value");
        sel.empty();
        sel.append("<option value=''>请选择</option>");
        var xm = sel.attr("xm-select");
        // 下拉框的类型 normal-普通单选框  linkage-联动框 tree-树 region-省市区
        var type = sel.attr("selectType");
        // 下拉框的请求地址
        var url = sel.attr("selectUrl");

        // 省市区、联动、树
        selectOther(xm, type, url, value);

        // 选择名称
        var selectName = sel.attr("selectName");
        // 单选、普通多选
        if (url) {
            selectByUrl(url, xm, type, value, sel);
        } else if (selectName) {
            selectByJson(selectName, xm, type, value, sel);
        }
    }

    /**
     * 省市区
     * 联动
     * 树
     */
    function selectOther(xm, type, url, value) {
        // 省市区
        if (type === 'region') {
            formSelects.data(xm, 'server', {
                url: 'api/region.json',
                linkage: true,
                success: function () {
                    if (value) {
                        formSelects.value(xm, value.split(","));
                    }
                }
            });
        } else if (type === 'linkage') {
            // 联动
            formSelects.data(xm, 'server', {
                url: url,
                linkage: true,
                success: function () {
                    if (value) {
                        formSelects.value(xm, value.split(","));
                    }
                }
            });
        } else if (type === 'tree') {
            // 树
            formSelects.data(xm, 'server', {
                url: url,
                success: function () {
                    if (value) {
                        formSelects.value(xm, value.split(","));
                    }
                }
            });
        }
        form.render();
    }

    /**
     * 下拉框 数据源来自请求的接口
     */
    function selectByUrl(url, xm, type, value, sel) {
        if (xm) {
            //多选
            formSelects.data(xm, 'server', {
                url: url,
                success: function () {
                    formSelects.value(xm, value.split(","));
                }
            });
        } else {
            // 单选
            $.getJSON(url, function (response) {
                for (var i = 0; i < response.data.length; i++) {
                    sel.append("<option value=" + response.data[i].value + ">" + response.data[i].name + "</option>");
                }
                if (value) {
                    sel.val(value);
                }
                form.render();
            });
        }
    }

    /**
     * 下拉框 数据源来自静态化json
     */
    function selectByJson(selectName, xm, type, value, sel) {
        var selectData = layui.data('selectData').value;
        if (selectData) {
            var arr = [];
            for (var k = 0; k < selectData.count; k++) {
                if (selectName === selectData.data[k].selectName) {
                    arr = selectData.data[k].items;
                }
            }
            if (xm) {
                // 多选
                formSelects.data(xm, 'local', {
                    arr: arr
                });
            } else {
                // 单选
                for (var i = 0; i < arr.length; i++) {
                    if (arr[i].name) {
                        sel.append("<option value=" + arr[i].value + ">" + arr[i].name + "</option>");
                    } else {
                        sel.append("<option value=" + arr[i].value + ">" + arr[i].value + "</option>");
                    }
                }
                if (value) {
                    sel.val(value);
                }

                form.render();
            }
        }
    }

    /**
     * 初始化表单内的日期
     */
    function initFormDate(formId, format, type) {
        $("#" + formId).find(".date").each(function () {
            initDate($(this).id, format, type);
        });
    }

    /**
     * 初始化日期
     * @param domId
     * @param format
     * @param type
     */
    function initDate(domId, format, type) {
        // 日期默认格式
        if (!format) {
            format = 'yyyy-MM-dd';
        }
        // type默认类型
        if (!type) {
            type = 'date';
        }
        var val = $("#" + domId).val();
        if (val) {
            laydate.render({
                elem: '#' + domId,
                format: format,
                type: type,
                value: layui.util.toDateString(parseInt(val), 'yyyy-MM-dd')
            });
        } else {
            laydate.render({
                elem: '#' + domId,
                format: format,
                type: type
            });
        }
    }

    /**
     * 初始化图标
     */
    function showIcon(id) {
        // 图标选择器的使用
        iconPicker.render({
            // 选择器，推荐使用input
            elem: '#' + id,
            // 数据类型：fontClass/unicode，推荐使用fontClass
            type: 'fontClass',
            // 是否开启搜索：true/false
            search: true,
            // 点击回调
            click: function (data) {
                $("#" + id).val(data.icon);
            }
        });
    }

    /**
     * 展示图片
     */
    function showImg(imgUrl) {
        if (imgUrl) {
            return '<img src=' + imgUrl + ' />';
        } else {
            return '';
        }
    }

    /**
     * 根据code获取desc
     */
    function getDesc(code, selectName) {
        var selectData = layui.data('selectData').value;
        if (selectData) {
            var arr = [];
            for (var k = 0; k < selectData.count; k++) {
                if (selectName === selectData.data[k].selectName) {
                    arr = selectData.data[k].items;
                }
            }
            for (var i = 0; i < arr.length; i++) {
                if (code === arr[i].value) {
                    return arr[i].name;
                }
            }
            return '';
        }
    }

    /**
     * 格式化表单对象的方法
     */
    function toJson(formId) {
        var o = {};
        var form = $("#" + formId);
        var data = form.serializeArray();
        $.each(data, function () {
            var name = this.name;
            var value = this.value;
            var paths = name.split(".");
            var len = paths.length;
            var obj = o;
            if (name && name !== "undefined") {
                $.each(paths, function (i, e) {
                    if (i === len - 1) {
                        if (obj[e]) {
                            obj[e] = obj[e] + "," + value;
                        } else {
                            obj[e] = value || '';
                        }
                    } else {
                        if (!obj[e]) {
                            obj[e] = {};
                        }
                    }
                    obj = o[e];
                })
            }
        });
        return o;
    }


    e('crud', obj);
})