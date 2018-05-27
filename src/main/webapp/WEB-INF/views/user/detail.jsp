<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
</head>
<body>
	<div id="wrapper">
	 <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-content nopadding">
            <form id="form-wizard" class="form-horizontal" method="post">
              <div id="form-wizard-1" class="step">
                <div class="control-group">
                  <label class="control-label">登录名</label>
                  <div class="controls">
                    <input id="loginName" type="text" name="loginName" />
                  </div>
                </div>
                <div class="control-group">
                  <label class="control-label">密码</label>
                  <div class="controls">
                    <input id="loginPassword" type="password" name="loginPassword" />
                  </div>
                </div>
                <div class="control-group">
                  <label class="control-label">手机号</label>
                  <div class="controls">
                    <input id="mobile" type="text" name="mobile" />
                  </div>
                </div>
              </div>
              <div class="control-group">
                  <label class="control-label">身份证姓名</label>
                  <div class="controls">
                    <input id="custName" type="text" name="custName" />
                  </div>
                </div>
                <div class="control-group">
                  <label class="control-label">身份证号码</label>
                  <div class="controls">
                    <input id="credentialsCode" type="text" name="credentialsCode" />
                  </div>
                </div>
              <div class="form-actions">
                <input id="bntSave" class="btn btn-success" type="submit" value="保存" />
                <input id="bntCanel" class="btn" type="button" value="取消"  />
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
	</div>
	<script src="/js/lib/requirejs/require.js" require-module="page/user/userDetail" data-main="/js/main.js"></script>
</body>
</html>