layui.define(["ztreecore", "ztreeexcheck", "ztreeexedit", 'layer'], function(e) {
    var $=layui.$;
    var rightClickCallback;
    var zTreeObj;
    var obj={
        zTreeAsync:function(obj){
            return zTreeAsync(obj);
        },
        zTree:function (obj) {
            return zTree(obj);
        }
    }

    //树的异步加载
    function zTreeAsync(obj){
        var url=obj.url;
        var id=obj.id;
        var setting = {
            async: {
                enable: true,
                url: url
            },
            callback : {
                onRightClick: OnRightClick,
                oncheck: null,
                onClick:null
            },
            check: {
                enable: false,
                chkboxType: 'null',
                chkStyle: 'null'
            }
        };
        if(obj.style){
            setting.check.enable=true;
            if(obj.style=='checkbox'){
                setting.check.chkboxType='checkbox';
                setting.check.chkboxType={ "Y": "p", "N": "s" };
            }else if(obj.style=='radio'){
                setting.check.chkboxType='radio';
                setting.check.radioType = "all";
            }
        }
        if(obj.rightClickCallback){
            rightClickCallback=obj.rightClickCallback;
        }
        if(obj.checkCallBack){
            setting.callback.onCheck=obj.checkCallBack;
        }
        if(obj.clickCallBack){
            setting.callback.onClick=obj.clickCallBack;
        }
        zTreeObj=$.fn.zTree.init($("#"+id), setting);
        return zTreeObj;
    }

    //树的同步加载
    function zTree(obj) {
        var zTreeObj;
        var setting = {
            callback : {
                onRightClick: null,
                onCheck:null,
                onClick:null
            },
            check: {
                enable: false,
                chkboxType: 'null',
                chkStyle: 'null'
            }
        };
        if(obj.style){
            setting.check.enable=true;
            if(obj.style=='checkbox'){
                setting.check.chkboxType='checkbox';
                setting.check.chkboxType={ "Y": "p", "N": "s" };
            }else if(obj.style=='radio'){
                setting.check.chkboxType='radio';
                setting.check.radioType = "all";
            }
        }
        if (obj.onclick) {
            setting.callback.onClick = obj.onclick;
        }
        if(obj.rightClickCallback){
            OnRightClick=obj.rightClickCallback;
        }
        if(obj.checkCallBack){
            setting.callback.onCheck=obj.checkCallBack;
        }
        $.ajax({
            type:'GET',
            url: obj.url,
            async:false,
            success:function (result) {
                if(result.code==0){
                    zTreeObj = $.fn.zTree.init($("#tree-area"), setting, result.data);
                }else{
                    layer.msg(result.msg,{icon:2})
                }
            },
            error:function (result) {
                var msg=result.responseJSON.msg || '服务异常';
                layer.msg(msg,{icon:2})
            }
        })
        
    }

    //树的右击事件
    function OnRightClick(event,treeId,treeNode) {
        if(event && treeNode && zTreeObj){
            zTreeObj.selectNode(treeNode,false,false);
            var width=$(event.target).width();
            if(rightClickCallback){
                rightClickCallback(event.clientX, event.clientY,treeNode);
            }
        }
    }

    e('zTree',obj);
});