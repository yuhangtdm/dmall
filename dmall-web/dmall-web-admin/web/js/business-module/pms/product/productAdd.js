layui.use(['form', 'crud', 'dtree', 'formSelects'], function () {
    var $ = layui.jquery,
        form = layui.form,
        dtree = layui.dtree,
        formSelects = layui.formSelects,
        crud = layui.crud;

    var parentIndex = layer.index;
    // 属性列表
    var allAttributeArr = [];
    // 属性类别列表
    var allAttributeTypeArr = [];
    // 规格列表
    var specificationsArr = [];
    // 卖点列表
    var salePointArr = [];
    // 参数列表
    var paramArr = [];
    var body = $("body");

    // 初始化页面
    init();

    // 分类选中事件
    dtree.on("chooseDone('selTree')", function (obj) {
        var arr = [];
        for (var i = 0; i < obj.checkbarParams.length; i++) {
            arr.push(obj.checkbarParams[i].nodeId);
        }
        var brandObj = {
            "categoryIds": arr
        }
        // 给品牌赋值
        crud.post(pmsUrl + '/brand/list', brandObj, function (response) {
            for (var i = 0; i < response.data.length; i++) {
                $("#brand").append("<option value=" + response.data[i].id + ">" + response.data[i].name + "</option>");
            }
            form.render();
        });
        var attributeObj = {
            "threeCategoryIds": arr
        }
        // 取属性列表
        crud.post(pmsUrl + '/attribute/list', attributeObj, function (response) {
            allAttributeArr = response.data;
            var attribute = {
                'attributeId': '',
                'attributeValues': []
            };
            specificationsArr.push(attribute);
            salePointArr.push(attribute);
            initAttribute('specifications');
            initAttribute('salePoint');
        });

        var attributeTypeObj = {
            "categoryIds": arr
        }
        // 取属性类别列表
        crud.post(pmsUrl + '/attributeType/list', attributeTypeObj, function (response) {
            allAttributeTypeArr = response.data;
            var attributeType = {
                'attributeTypeId': '',
                'attributes': []
            };
            var attribute = {
                'attributeId': '',
                'attributeValues': []
            };
            attributeType.attributes.push(attribute);
            paramArr.push(attributeType);
            initParam(paramArr, allAttributeTypeArr, allAttributeArr);
        });
    });

    /**
     * 页面初始化函数
     */
    function init() {
        crud.initSelect('unit');
        crud.initDate('onMarketTime');
        crud.selectTree('selTree', pmsUrl + '/category/tree/0/4', 'all');
        crud.initSelect('brand');
    }

    /**
     * 初始化参数
     */
    function initParam() {
        var attributeTypeHtml = '';
        for (var i = 0; i < paramArr.length; i++) {
            attributeTypeHtml += '<div id=param_"' + i + '" index ="' + i + '" type="param" class="layui-form-item"><div class="layui-inline">';
            var attrType = paramArr[i];
            attributeTypeHtml += '<label class="layui-form-label">选择属性类别</label>';
            attributeTypeHtml += '<div class="layui-input-inline"><select lay-filter="attributeType"><option value=""></option>';
            var selected = null;
            for (var j = 0; j < allAttributeTypeArr.length; j++) {
                if (attrType.attributeTypeId && attrType.attributeTypeId === allAttributeTypeArr[j].id) {
                    selected = allAttributeTypeArr[j];
                    attributeTypeHtml += '<option selected value="' + allAttributeTypeArr[j].id + '">' + allAttributeTypeArr[j].showName + '</option>';
                } else {
                    attributeTypeHtml += '<option  value="' + allAttributeTypeArr[j].id + '">' + allAttributeTypeArr[j].showName + '</option>';
                }
            }
            attributeTypeHtml += '</select></div>';

            var attributeArr = attrType.attributes;

            attributeTypeHtml += getAttributeHtml(attributeArr);

        }
        $("#param").html('').append(attributeTypeHtml);
        form.render();
    }

    /**
     * 初始化属性
     */
    function initAttribute(type) {
        var attributeHtml = '';
        if (type === 'specifications') {
            attributeHtml = getAttributeHtml(specificationsArr, type);
        } else if (type === 'salePoint') {
            attributeHtml = getAttributeHtml(salePointArr, type);
        }
        $("#" + type).html('').append(attributeHtml);
        form.render();
    }

    /**
     * 获取属性html
     */
    function getAttributeHtml(arr, type) {
        var attributeHtml = '';
        for (var i = 0; i < arr.length; i++) {
            if (type) {
                attributeHtml += '<div id="' + type + "_" + i + '" index="' + i + '" type="' + type + '" class="layui-form-item"><div class="layui-inline">';
            }
            var attribute = arr[i];
            attributeHtml += '<label class="layui-form-label">选择属性</label>';
            attributeHtml += '<div class="layui-input-inline"><select><option value=""></option>';
            var selected = null;
            for (var j = 0; j < allAttributeArr.length; j++) {
                if (attribute.attributeId !== '' && attribute.attributeId === allAttributeArr[j].id) {
                    selected = allAttributeArr[j];
                    attributeHtml += '<option selected value="' + allAttributeArr[j].id + '">' + allAttributeArr[j].showName + '</option>';
                } else {
                    attributeHtml += '<option  value="' + allAttributeArr[j].id + '">' + allAttributeArr[j].showName + '</option>';
                }
            }
            attributeHtml += '</select></div>';

            if (selected) {
                attributeHtml = getAttributeValueHtml(selected, attribute, attributeHtml);
            }
            attributeHtml += '&nbsp;<button type="button" class="layui-btn layui-btn-primary add">+</button>&nbsp;';
            if (specificationsArr.length !== 1) {
                attributeHtml += '<a type="button" class="layui-btn layui-btn-primary sub">-</a>';
            }
            attributeHtml += '</div></div><br/>';
        }
        return attributeHtml;

    }

    /**
     * 获取属性值html
     */
    function getAttributeValueHtml(attribute, attr, attributeHtml) {
        if (attribute.inputType === 2) {
            // 从列表录入
            attributeHtml += '<label class="layui-form-label">选择属性值</label><div class="layui-input-inline">';
            var inputList = attribute.inputList;
            if (inputList) {
                var inputArr = inputList.split(",");
                for (var k = 0; k < inputArr.length; k++) {
                    var inputArrElement = inputArr[k];
                    if (crud.contains(attr.attributeValues, inputArrElement)) {
                        attributeHtml += '<input type="checkbox" checked  value="' + inputArrElement + '" lay-skin="primary"  title="' + inputArrElement + '" >';
                    } else {
                        attributeHtml += '<input type="checkbox" value="' + inputArrElement + '" lay-skin="primary"  title="' + inputArrElement + '" >';
                    }
                }
            }
            attributeHtml += '</div>';
            if (attribute.handAddStatus === 'Y') {
                attributeHtml += '<label class="layui-form-label">新增属性值</label>';
                attributeHtml += '<div class="layui-input-inline"><input type="text" placeholder="多个用逗号隔开" class="layui-input"></div>';
            }
        } else {
            // 手工录入
            attributeHtml += '<label class="layui-form-label">新增属性值</label>';
            attributeHtml += '<div class="layui-input-inline"><input type="text" placeholder="多个用逗号隔开" class="layui-input"></div>';
        }
        return attributeHtml;
    }

    /**
     * 监听属性选择事件
     */
    form.on('select', function (data) {
        var parentId = $(data.elem).parent().parent().parent().attr("index");
        var type = $(data.elem).parent().parent().parent().attr("type");
        if (type === 'specifications') {
            selectAttr(specificationsArr, parentId, data);
            initAttribute(type);
        } else if (type === 'salePoint') {
            selectAttr(salePointArr, parentId, data);
            initAttribute(type);
        } else {
            for (var i = 0; i < paramArr.length; i++) {
                if (i === parseInt(parentId)) {
                    paramArr[i].attributes[0].attributeId = data.value;
                }
            }
        }
    });

    function selectAttr(arr, parentId, data) {
        for (var i = 0; i < arr.length; i++) {
            if (i === parseInt(parentId)) {
                arr[i].attributeId = data.value;
            }
        }
    }

    /**
     * 新增事件
     */
    body.on('click', '.add', function () {
        var parentId = $(this).parent().parent().attr("index");
        var type = $(this).parent().parent().attr("type");
        if (type === 'specifications') {
            add(specificationsArr, parentId, type);
        } else if (type === 'salePoint') {
            add(salePointArr, parentId, type);
        } else {
            add(paramArr, parentId, type);
        }
    });

    function add(arr, parentId, type) {
        var attribute = {
            'attributeId': '',
            'attributeValues': []
        };
        if (type === 'param') {
            var attributeType = {
                'attributeTypeId': '',
                'attributes': []
            };
            attributeType.attributes.push(attribute);
            splice(arr, attributeType, parentId);
            initParam();
        } else {
            splice(arr, attribute, parentId);
            initAttribute(type);
        }
    }

    function splice(arr, obj, parentId) {
        for (var j = 0; j < arr.length; j++) {
            if (j === parseInt(parentId)) {
                arr.splice(j + 1, 0, obj);
            }
        }
    }

    /**
     * 减少事件
     */
    body.on('click', '.sub', function () {
        var parentId = $(this).parent().parent().attr("index");
        var type = $(this).parent().parent().attr("type");
        if (type === 'specifications') {
            specificationsArr = specificationsArr.filter(function (element, index) {
                return index !== parseInt(parentId);
            });
            initAttribute(type);
        } else if (type === 'salePoint') {
            salePointArr = salePointArr.filter(function (element, index) {
                return index !== parseInt(parentId);
            });
            initAttribute(type);
        } else {
            paramArr = paramArr.filter(function (element, index) {
                return index !== parseInt(parentId);
            });
            initParam();
        }
    });


    /**
     * 监听属性类别选择事件
     */
    form.on('select(attributeType)', function (data) {
        console.log('attributeType')
        var parentId = $(data.elem).parent().parent().parent().attr("index");
        for (var i = 0; i < paramArr.length; i++) {
            if (i === parseInt(parentId)) {
                paramArr[i].attributeTypeId = data.value;
            }
        }
        initParam(paramArr);
    });

    /**
     * 监听复选框选中事件
     */
    form.on('checkbox', function (data) {
        var parentId = $(data.elem).parent().parent().parent().attr("index");
        var type = $(data.elem).parent().parent().parent().attr("type");
        var arr = [];
        $('#' + type + "_" + parentId + ' input[type=checkbox]:checked').each(function () {
            arr.push($(this).val());
        });

        if (type === 'specifications') {
            setAttributeValues(specificationsArr, arr, parentId);
        } else if (type === 'salePoint') {
            setAttributeValues(salePointArr, arr, parentId);
        } else {
            for (var i = 0; i < paramArr.length; i++) {
                if (i === parseInt(parentId)) {
                    paramArr[i].attributes[0].attributeValues = arr;
                }
            }
        }
    });

    function setAttributeValues(attrArr, arr, parentId) {
        for (var i = 0; i < attrArr.length; i++) {
            if (i === parseInt(parentId)) {
                attrArr[i].attributeValues = arr;
            }
        }
    }

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        return false;
    });

})
;
