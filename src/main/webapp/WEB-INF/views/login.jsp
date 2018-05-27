<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri='http://www.springframework.org/security/tags'%>
<!DOCTYPE html>
<html lang="en">
    
<head>
        <title>锋锋后台管理系统</title><meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="_csrf" content="${_csrf.token}" />
		<link rel="stylesheet" href="css/login.css" />
    </head>
    <body>
        <div id="loginbox">            
            <form id="loginform" class="form-vertical" action="/loginPost" method="post">
				 <div class="control-group normal_text"> <h3><img src="img/logo.png" alt="Logo" /></h3></div>
					<c:if test="${not empty error}">
						<div id="error" class="alert alert-error alert-block">
					 		${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
					 	</div>
					</c:if>
					 
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input id="loginName" name="loginName" type="text" placeholder="用户名" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input id="loginPassword" name="loginPassword" type="password" placeholder="密码" />
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-info" id="to-recover">找回密码</a></span>
                    <span class="pull-right"><button type="submit" class="btn btn-success" >登录</button></span>
                    <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
                </div>
            </form>
            <form id="recoverform" action="#" class="form-vertical">
				<p class="normal_text">请输入邮箱地址，系统将会给您发送一封以找回密码</p>
				
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input type="text" placeholder="邮箱地址" />
                        </div>
                    </div>
               
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-success" id="to-login">&laquo; 返回登录</a></span>
                    <span class="pull-right"><a class="btn btn-info">找回</a></span>
                </div>
            </form>
        </div>
        
        <script src="/js/lib/requirejs/require.js" require-module="page/login" data-main="../js/main.js"></script>
    </body>

</html>
