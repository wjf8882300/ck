define(["jquery", "Common", "Constant", "jquery.validate", "messages_cn", "ligerui.ligerGrid"], function($, Common, Constant){
	
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
	$("#form-wizard").validate({
		rules:{
			loginName:{
				required:true
			},
			loginPassword:{
				required: true,
				minlength:6,
				maxlength:20
			},
			mobile:{
				isMobile:true
			},
			custName:{
				minlength:2,
				maxlength:20
			},
			credentialsCode:{
				isIdCardNo:true
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
			Common.saveData(Constant.user.save, 
					 {id:dialog.get("data").id,
					  loginName:$("#loginName").val(),
				 	  loginPassword:$("#loginPassword").val(),
				 	  mobile:$("#mobile").val(),
				 	  custName:$("#custName").val(),
				 	  credentialsCode:$("#credentialsCode").val()
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
			Common.queryById(Constant.user.queryById, {id:dialogData.id}, function(data) {
				var user = data.result.data;
				$("#loginName").val(user.loginName);
				$("#loginPassword").val(user.loginPassword);
				$("#custName").val(user.custName);
				$("#credentialsCode").val(user.credentialsCode);
			});
		}
    }
    
    return {
    	init:init
    };
});
