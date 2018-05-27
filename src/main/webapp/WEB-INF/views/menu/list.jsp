<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
</head>
<body>
	<div id="wrapper">
			<form class="form-inline" id="searchForm" name="searchForm">
				<div class="control-group">
                  <div class="controls">
                    <label class="control-label">菜单名称</label>
                    <input id="menuname" type="text" name="menuname" class="ui-wizard-content">
                    <input id="bntSearch" class="btn btn-primary ui-wizard-content ui-formwizard-button" type="button" value="查询">
                  </div>
                </div>	
                <div class="control-group">  
                  <div class="controls">
	              <button type="button" class="btn btn-success" id="bntAdd">新增</button>
	              <button type="button" class="btn btn-info" id="bntUpdate">编辑</button>
	              <button type="button" class="btn btn-danger" id="bntDelete">删除</button>
	              </div>
                </div>	
			</form>
			
			<div id="maingrid4" style="margin:0; padding:0"></div> 
	</div>
	<script src="/js/lib/requirejs/require.js" require-module="page/menu/menu" data-main="/js/main.js"></script>	
</body>
</html>