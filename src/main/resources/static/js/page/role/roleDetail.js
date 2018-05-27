define(["jquery", "Common", "Constant", "jquery.validate", "messages_cn", "ligerui.ligerGrid"], function($, Common, Constant){
	
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
	$("#form-wizard").validate({
		rules:{
			roleName:{
				required:true
			},
			roleFlag:{
				required: true
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
			Common.saveData(Constant.role.save, 
					 {id:dialog.get("data").id,
				      roleName:$("#roleName").val(),
				      roleKey:$("#roleKey").val(),
					  roleDesc:$("#roleDesc").val()
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
    	var dialogData = dialog.get("data");//获取data参数
		if(dialogData.name === "update") {
			Common.queryById(Constant.role.queryById, {id:dialogData.id}, function(data) {
				var role = data.result.data;
				$("#roleName").val(role.roleName);
				$("#roleKey").val(role.roleKey);
				$("#roleDesc").val(role.roleDesc);
			});
		}
    }
    
    return {
    	init:init
    };
});
