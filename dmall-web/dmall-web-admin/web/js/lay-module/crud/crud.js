layui.define(['layer', 'table', 'form', 'miniPage'], function (e) {
    var layer = layui.layer;
    var $ = layui.$;
    var table = layui.table;
    var miniPage = layui.miniPage;
    var form = layui.form;

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
        get: function (url, callBack) {
            get(url, callBack);
        },
        post: function (url, requestData, callBack) {
            post(url, requestData, callBack);
        },
        msg: function (content) {
            msg(content);
        },
        msgBack: function (content, callback) {
            msgBack(content, callback);
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
        formatDate: function (date) {
            return formatDate(date);
        },
        open: function (href, title) {
            open(href, title);
        },

        deleteById: function (url, callbak) {
            deleteById(url, callbak);
        },

        initSimplePage: function (id, url, cols) {
            initSimplePage(id, url, cols);
        },
        toJson: function (formId) {
            return toJson(formId);
        },
        validateNumber: function (number) {
            validateNumber(number);
        },

    }

    /**
     * get请求
     */
    function get(url, callback) {
        $.ajax({
            type: 'get',
            url: url,
            beforeSend: function (request) {
                request.setRequestHeader("source", "admin");
                request.setRequestHeader("token", getToken());
            },
            success: function (response) {
                if (response.code === '0') {
                    msgBack(response.msg, callback(response));
                } else if (response.code === '408') {
                    // 用户未登陆 跳转登录页面
                    errorBack('登录已失效', function () {
                        window.location.href = 'page/login.html';
                    });
                } else {
                    error(response.msg);
                }
            },
            error: function (response) {
                layer.msg(response.msg, {icon: 2})
            },
        });
    }

    /**
     * post请求
     */
    function post(url, requestData, callback) {
        $.ajax({
            type: 'POST',
            url: url,
            data: JSON.stringify(requestData),
            contentType: 'application/json;charset=utf-8',
            beforeSend: function (request) {
                parent.layer.load(1);
                request.setRequestHeader("source", "admin");
                request.setRequestHeader("token", getToken());
            },
            success: function (response) {
                parent.layer.closeAll('loading');
                if (response.code === '0') {
                    callback(response);
                } else if (response.code === '408') {
                    // 用户未登陆 跳转登录页面
                    errorBack('登录已失效', function () {
                        window.location.href = 'page/login.html';
                    });
                } else {
                    error(response.msg);
                }
            },
            error: function (response) {
                parent.layer.closeAll('loading');
                layer.msg(response.msg, {icon: 2})
            },
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
     */
    function msgBack(content, callback) {
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
            time: 1000
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
     */
    function open(href, title) {
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
            // 层销毁后会回调
            end :function () {
                //  刷新页面的搜索
                $("button[lay-filter='formSearch']").click();
            }
        });

    }

    function validateNumber(number) {
        if (parseInt(number) < 0 || number.indexOf(".") > -1) {
            layer.msg('输入值不能为负数或小数', {type: 2});
            return false;
        }
    }


    /**
     * 有回调的保存
     */
    function saveOrUpdate(url, requestData, callbak) {
        $.ajax({
            type: 'POST',
            url: url,
            data: requestData,
            async: false,
            contentType: 'application/json;charset=utf-8',
            traditional: false,
            beforeSend: function (request) {
                request.setRequestHeader("source", "admin");
                parent.layer.load(1);
            },
            success: function (data) {
                parent.layer.closeAll('loading');
                if (data.code === '0') {
                    parent.layer.msg(data.msg, {
                        icon: 1,
                        time: 1000
                    }, function () {
                        if (callbak) {
                            callbak();
                        } else {
                            // 关闭子页面
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        }
                    });
                } else {
                    parent.layer.msg(data.msg, {icon: 2})
                }
            },
            error: function (data) {
                layer.closeAll('loading');
                layer.msg(data.msg, {icon: 2})
            },

        });
    }

    /**
     * 公共删除方法
     */
    function deleteById(url, callback) {
        $.ajax({
            type: 'GET',
            url: url,
            async: false,
            beforeSend: function () {
                layer.load(1);
            },
            success: function (data) {
                if (data.code === '0') {
                    layer.msg(data.msg, {
                        icon: 1,
                        time: 1000
                    }, function () {
                        layer.closeAll();
                        callback();
                    });
                } else {
                    layer.msg(data.msg, {icon: 2})
                }
            },
            error: function (data) {
                var msg = "";
                if (data && data.responseJSON && data.responseJSON.msg) {
                    msg = data.responseJSON.msg;
                } else {
                    msg = '服务故障';
                }
                layer.msg(msg, {icon: 2})
            },
            complete: function () {
                layer.closeAll('loading');
            }
        });
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
            if (name && name != "undefined") {
                $.each(paths, function (i, e) {
                    if (i == len - 1) {
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

    /**
     * 弹窗保存之后的回调方法
     */
    function saveAfter() {
        window.parent.location.reload();//刷新父页面
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);//关闭父窗口
    }

    form.verify({
        password: function (value) {
            var passReg = '(?![0-9A-Z]+$)(?![0-9a-z]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$';
            var r = '/^[^\u4e00-\u9fa5]+$/';
            if (!passReg.match(value) && r.match(value)) {
                return "密码必须包含数字、大小写字母,不包含汉字,且至少六位";
            }
        },
        bigZero: function (value) {
            if (parseFloat(value) < 0) {
                return "数字必须大于0";
            }
        },
        twoDecimal: function (value) {
            var decimalReg = '/^\\d+(\\.\\d{1,2})?$/';
            if (!decimalReg.match(value)) {
                return "只能有两位小数点";
            }
        }
    });


    e('crud', obj);
})