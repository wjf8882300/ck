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
                <ul id="menuTree"></ul> 
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
	<script src="/js/lib/requirejs/require.js" require-module="page/role/roleGrant" data-main="/js/main.js"></script>	
</body>
</html>