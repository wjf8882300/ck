<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri='http://www.springframework.org/security/tags'%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
 <!DOCTYPE html>
<html lang="en">
<head>
<title>锋锋科技后台管理系统</title>
<meta charset="utf-8">
<meta name="_csrf" content="${_csrf.token}" />
<link rel="stylesheet" href="/css/main.css"/>
</head>
<body>
	<!--Header-part-->
	<div id="header">
	  <h1><a href="/">锋锋科技后台管理系统</a></h1>
	</div>
	<!--close-Header-part--> 
	
	<div id="user-nav" class="navbar navbar-inverse">
	  <form id="logoutForm" action="/logout" method="post">
	  <ul class="nav" style="width: auto; margin: 0px;">
	    <li class="">
	    	<a title="" href="#" id="logout"><i class="icon icon-share-alt"></i> <span class="text">退出系统</span></a>
	    </li>
	  </ul>
	  </form>
	</div>
	
	<!--sidebar-menu-->
	<div id="sidebar" class="sidebar">
	  <ul>
	  <c:forEach var="item" items="${menuList }">
	  	<c:if test="${item.menuLevel == '1' }">
	  	  <li class="submenu"><a href="#"><i class="icon ${item.menuIcon }"></i> <span>${item.menuName }</span></a><b class="arrow fa fa-angle-down"></b>
	  	  <ul>
	  	  	<c:forEach var="subItem" items="${menuList }">
	  	  		<c:if test="${subItem.parentId == item.id }">
	  	  			<li><a href="javascript:void(0)" to-url="${subItem.menuUrl }" nav-n="${subItem.menuName },${subItem.menuFlag }"><i class="icon ${subItem.menuIcon }"></i><span style="margin-left:10px;">${subItem.menuName }</span></a></li>
	  	  		</c:if>
	  	  	</c:forEach>
	  	  </ul>
	      </li>
	  	</c:if>
	  </c:forEach>
	  </ul>
	</div>
	<!--sidebar-menu-->
	
	<div id="content">	
	<!--main-container-part-->
	<div class="main-content">
		<div id="layout1" class="l-layout mylayout" style="width: 99.2%; margin: 2px auto 0px; height: 446px;background-color: #ffffff;"> 
			<div position="center" id="framecenter" > 
	        </div>
		</div>
	</div>
	<!--end-main-container-part-->
	</div>
	
	<!--Footer-part-->
	<div class="row-fluid">
	  <div id="footer" class="span12"> 2016 &copy; 锋锋科技</div>
	</div>
	<!--end-Footer-part-->
	<script src="/js/lib/requirejs/require.js" require-module="page/index" data-main="/js/main.js"></script>	
</body>
</html>
