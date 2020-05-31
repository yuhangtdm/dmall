layui.use(['form', 'crud', 'dtree', 'formSelects'], function () {
    var $ = layui.jquery,
        form = layui.form,
        dtree = layui.dtree,
        formSelects = layui.formSelects,
        crud = layui.crud;

    var parentIndex = layer.index;
    // 属性列表
    var allAttributeArr = [];
    // 规格列表
    var specificationsArr = [];
    // 卖点列表
    var salePointArr = [];
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
            initAttribute(specificationsArr, allAttributeArr, 'specifications');
            initAttribute(salePointArr, allAttributeArr, 'salePoint');
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
     * 初始化属性
     */
    function initAttribute(specificationsArr, allAttributeArr, type) {
        var attributeHtml = '';
        var arr = [];
        if (type === 'specifications') {
            arr = specificationsArr;
        } else if (type === 'salePoint') {
            arr = salePointArr;
        }
        for (var i = 0; i < arr.length; i++) {
            attributeHtml += '<div id="' + type + "_" + i + '" index="' + i + '" type="' + type + '" class="layui-form-item"><div class="layui-inline">';
            var attr = specificationsArr[i];
            attributeHtml += '<label class="layui-form-label">选择属性</label>';
            attributeHtml += '<div class="layui-input-inline"><select><option value=""></option>';
            var selected = null;
            for (var j = 0; j < allAttributeArr.length; j++) {
                if (attr.attributeId !== '' && attr.attributeId === allAttributeArr[j].id) {
                    selected = allAttributeArr[j];
                    attributeHtml += '<option selected value="' + allAttributeArr[j].id + '">' + allAttributeArr[j].showName + '</option>';
                } else {
                    attributeHtml += '<option  value="' + allAttributeArr[j].id + '">' + allAttributeArr[j].showName + '</option>';
                }
            }
            attributeHtml += '</select></div>';
            if (selected) {
                attributeHtml = initAttributeValue(selected, attr, attributeHtml);
            }
            attributeHtml += '&nbsp;<button type="button" class="layui-btn layui-btn-primary add">+</button>&nbsp;';
            if (specificationsArr.length !== 1) {
                attributeHtml += '<a type="button" class="layui-btn layui-btn-primary sub">-</a>';
            }
            attributeHtml += '</div></div></div><br/>';
        }
        $("#" + type).html('').append(attributeHtml);
        form.render();
    }

    /**
     * 初始化属性值
     */
    function initAttributeValue(attribute, attr, attributeHtml) {
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
     * 新增事件
     */
    body.on('click', '.add', function () {
        var parentId = $(this).parent().parent().attr("index");
        var type = $(this).parent().parent().attr("type");
        var attribute = {
            'attributeId': '',
            'attributeValues': []
        };
        if (type === 'specifications') {
            var length1 = specificationsArr.length;
            for (var i = 0; i < length1; i++) {
                if (i === parseInt(parentId)) {
                    specificationsArr.splice(i + 1, 0, attribute);
                }
            }
            initAttribute(specificationsArr, allAttributeArr, type);
        } else if (type === 'salePoint') {
            var length2 = salePointArr.length;
            for (var j = 0; j < length2; j++) {
                if (j === parseInt(parentId)) {
                    salePointArr.splice(j + 1, 0, attribute);
                }
            }
            initAttribute(salePointArr, allAttributeArr, type);
        }

    });

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
            initAttribute(specificationsArr, allAttributeArr, type);
        } else if (type === 'salePoint') {
            salePointArr = salePointArr.filter(function (element, index) {
                return index !== parseInt(parentId);
            });
            initAttribute(salePointArr, allAttributeArr, type);
        }

    });

    /**
     * 监听属性选择事件
     */
    form.on('select', function (data) {
        var parentId = $(data.elem).parent().parent().parent().attr("index");
        var type = $(data.elem).parent().parent().parent().attr("type");
        var arr = [];
        if (type === 'specifications') {
            arr = specificationsArr;
        } else if (type === 'salePoint') {
            arr = salePointArr;
        }
        for (var i = 0; i < arr.length; i++) {
            if (i === parseInt(parentId)) {
                if (type === 'specifications') {
                    specificationsArr[i].attributeId = data.value;
                } else if (type === 'salePoint') {
                    salePointArr[i].attributeId = data.value;
                }
            }
        }
        if (type === 'specifications') {
            initAttribute(specificationsArr, allAttributeArr, type);
        } else if (type === 'salePoint') {
            initAttribute(salePointArr, allAttributeArr, type);
        }
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
        for (var i = 0; i < specificationsArr.length; i++) {
            if (i === parseInt(parentId)) {
                if (type === 'specifications') {
                    specificationsArr[i].attributeValues = arr;
                } else if (type === 'salePoint') {
                    salePointArr[i].attributeValues = arr;
                }
            }
        }
    });

    //监听提交
    form.on('submit(saveBtn)', function (data) {
        return false;
    });

});
