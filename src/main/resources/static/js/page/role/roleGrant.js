define(["jquery", "Common", "Constant", "jquery.validate", "messages_cn", "ligerui.ligerGrid", "ligerui.ligerComboBox"], function($, Common, Constant){
	
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
	var tree = null;
	var init = function() {
		tree = $("#menuTree").ligerTree({ 
	        checkbox: true,
	        isExpand: false, 
	        slide: false, 
	        url: Constant.menu.queryAll, 
    	    parms:{user:"1", start:1, length:1000}, 
    	    ajaxContentType:"application/json", 
    	    idField:'id', 
    	    parentIDField: 'parentId',
    	    idFieldName:'id',
    	    textFieldName:'menuName',
    	    onSuccess:function(data) {
    	    	// 取得原先权限
    	    	Common.queryById(Constant.menu.queryByRoleId, {roleId:dialog.get("data").id}, function(data) {
    				var menuList = data.result.data;
    				//遍历树   				
    				var parm = function (data)
			        {
    					 for(var i = 0; i< menuList.length; i++){
    						 if(data.id == menuList[i].id){
    							 return true;
    						 }
    					 }
			            return false;
			        };
			        tree.selectNode(parm); 
    			});
    	    }
	    });
	};
	
	return {
		init:init
	};
	
});