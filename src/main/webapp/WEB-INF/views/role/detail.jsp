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
	                  <label class="control-label">角色名称</label>
	                  <div class="controls">
	                    <input id="roleName" type="text" name="roleName" />
	                  </div>
	                </div>
	                <div class="control-group">
	                  <label class="control-label">角色标识</label>
	                  <div class="controls">
	                    <input id="roleKey" type="text" name="roleKey" />
	                  </div>
	                </div>
	                <div class="control-group">
	                  <label class="control-label">描述</label>
	                  <div class="controls">
	                    <input id="roleDesc" type="text" name="roleDesc" />
	                  </div>
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
	<script src="/js/lib/requirejs/require.js" require-module="page/role/roleDetail" data-main="/js/main.js"></script>	
</body>
</html>