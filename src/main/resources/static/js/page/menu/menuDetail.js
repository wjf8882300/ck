define(["jquery", "Common", "Constant", "jquery.validate", "messages_cn", "ligerui.ligerGrid", "jquery.uniform", "ligerui.ligerComboBox"], function($, Common, Constant){
	
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
	$("#form-wizard").validate({
		rules:{
			menuName:{
				required:true
			},
			menuFlag:{
				required:true
			},
			menuURL:{
				required:true
			},
			parentId:{
				required:true
			}
		},
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		},
		submitHandler: function(form) { 
			Common.saveData(Constant.menu.save, 
					 {	id:dialog.get("data").id,
						menuName:$("#menuName").val(),
						menuFlag:$("#menuFlag").val(),
						menuUrl:$("#menuURL").val(),
						parentId:$("#parentId_val").val(),
						menuIcon:$("#menuIcon").val(),
						isEnabled:$('input[name="isEnabled"]:checked').val(),
						menuDesc:$("#menuDesc").val()
					 }, function(data) {
						 Common.successMsg("保存成功");
						 parent.$("#maingrid4").ligerGrid("reload");			     
					     dialog.close();
					 });
		}
	});
	
	$("#bntCanel").click(function() {
    	dialog.close();
    });
    
    function init() {   
    	$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
    	var parentId = $("#parentId").ligerComboBox({
    		resize: false,
            width : 220, 
            selectBoxWidth: 220,
            selectBoxHeight: 250, 
            valueField: 'id', 
            textField: 'menuName',
            treeLeafOnly: false,
            tree: { url: Constant.menu.queryAll, 
            	    parms:{user:"1", start:1, length:1000}, 
            	    ajaxContentType:"application/json", 
            	    checkbox: false, 
            	    idField:'id', 
            	    parentIDField: 'parentId',
            	    idFieldName:'id',
            	    textFieldName:'menuName',
            	    autoCheckboxEven:false},
            onSelected:function(value,text) {
            	
            }
        });
    	var dialogData = dialog.get("data");//获取data参数
		if(dialogData.name === "update") {
			Common.queryById(Constant.menu.queryById, {id:dialogData.id}, function(data) {
				var menu = data.result.data;
				$("#menuName").val(menu.menuName);
				$("#menuFlag").val(menu.menuFlag);
				$("#menuURL").val(menu.menuUrl);
				$("#menuIcon").val(menu.menuIcon);
				//$("#menuEnabled").val(menu.isEnabled);
				$("#menuDesc").val(menu.menuDesc);
				if(menu.parentId !== "0") {
					parentId.setValue(menu.parentId);
				}				
				$('input:radio[name="isEnabled"][value="'+menu.isEnabled+'"]').prop('checked', true);
				$.uniform.update();
			});
		}
    }
    
    return {
    	init:init
    };
});
