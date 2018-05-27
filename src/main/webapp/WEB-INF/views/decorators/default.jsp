<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@include file="../taglib.jsp"%>
<!DOCTYPE html>
<html lang="">
<head>
<title>锋锋科技</title>
<meta charset="UTF-8" />
<meta name="keywords" content="" >
<meta name="description" content="" >
<meta name="HandheldFriendly" content="true">
<meta name="MobileOptimized" content="width">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="_csrf" content="${_csrf.token}" />
<link rel="stylesheet" href="${ctx }/css/default.css" />
<style>
	body{
		background-color: #ffffff;
	}
	
	#wrapper{
	position: relative;
	 	margin: 0;
	  	 padding: 8px 20px 0px; 
	    -webkit-transform: translate3d(0, 0, 0); 
	    -moz-transform: translate3d(0, 0, 0); 
	    -ms-transform: translate3d(0, 0, 0); 
	    transform: translate3d(0, 0, 0); 
	    -webkit-backface-visibility: hidden; 
	    -moz-backface-visibility: hidden; 
	    -ms-backface-visibility: hidden; 
	    backface-visibility: hidden; 
	 
	    -webkit-perspective: 1000; 
	    -moz-perspective: 1000; 
	    -ms-perspective: 1000; 
	    perspective: 1000;
	    
	    overflow-x: hidden; 
	    overflow-y: hidden;
	}
</style>

<%-- 导入页面特有的JS，CSS等 --%>
<decorator:head />
</head>
<body>

<!--main-container-part-->
<decorator:body />

<!--end-main-container-part-->



</body>
</html>