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
	                  <label class="control-label">菜单名称</label>
	                  <div class="controls">
	                    <input id="menuName" type="text" name="menuName" />
	                  </div>
	                </div>
	                <div class="control-group">
	                  <label class="control-label">菜单标识</label>
	                  <div class="controls">
	                    <input id="menuFlag" type="text" name="menuFlag" />
	                  </div>
	                </div>
	                <div class="control-group">
	                  <label class="control-label">菜单地址</label>
	                  <div class="controls">
	                    <input id="menuURL" type="text" name="menuURL" />
	                  </div>
	                </div>
	              </div>
	              <div class="control-group">
	                  <label class="control-label">上级菜单</label>
	                  <div class="controls">
	                    <input id="parentId" type="text" name="parentId"/>
	                  </div>
	                </div>
	                <div class="control-group">
	                  <label class="control-label">菜单图标</label>
	                  <div class="controls">
	                    <input id="menuIcon" type="text" name="menuIcon" />
	                  </div>
	                </div>
	                <div class="control-group">
	                  <label class="control-label">是否启用</label>
	                  <div class="controls">
						<label>
		                  <input id="menuEnabled" type="radio" name="isEnabled" value="01" />
		                                                启用</label>
		                <label>
		                  <input id="menuDisabled" type="radio" name="isEnabled" value="02" />
		                                                禁用</label>
					</div>
	                </div>
	                <div class="control-group">
	                  <label class="control-label">描述</label>
	                  <div class="controls">
	                    <input id="menuDesc" type="text" name="menuDesc" />
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
	<script src="/js/lib/requirejs/require.js" require-module="page/menu/menuDetail" data-main="/js/main.js"></script>	
</body>
</html>