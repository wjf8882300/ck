/**
 * 登录
 */
$("#loginName").click(function(){
	if(!$("#error").hasClass("ckhide")) {
		$("#error").addClass("ckhide");
	}
});

$("#loginPassword").click(function(){
	if(!$("#error").hasClass("ckhide")) {
		$("#error").addClass("ckhide");
	}
});

//$("#bntLogin").click(function(){
//	var loginName = $("#loginName").val();
//	var loginPassword = $("#loginPassword").val();
//	$.ajax({ 
//        type:"post", 
//        url:"/loginPost", 
//        contentType:"application/json",              
//        dataType:"json",  
//        data:JSON.stringify({loginName:loginName, loginPassword:loginPassword}), 
//        cache:false,  
//        success:function(data){ 
//         //console.log("data=",data);
//        	if(data.result.hasOwnProperty("error")) {
//        		$("#error").html(data.result.message);
//                $("#error").removeClass("ckhide");
//        	}
//        	else {
//        		location.href = "index";
//        	}        
//        },
//        error:function() {
//        	$("#error").html('请求调用失败');
//        	 $("#error").removeClass("ckhide");
//        }
//     });  
//}); 

//登录验证
$('#loginform').submit(function(){
//	$("#loginPassword").val(des.strEnc($("#loginPassword").val(), $("meta[name='_csrf']").attr("content")));
	$("#loginPassword").val(des.strEnc($("#loginPassword").val(), "19b4e95f4656b24fdccc7488a98d596f");
	return true;
});
	
