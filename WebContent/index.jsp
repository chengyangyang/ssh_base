<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/views/common/JsAndCss.jsp"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
</head>
<body>

<div class="panel panel-success" style="width: 99%;margin: 0px auto;">
    <div class="panel-heading">
        <h1 class="panel-title text-center">我的整理目录</h1>
    </div>
    <div class="panel-body">
    <a href="${contextPath}/views/file/FileUpload.jsp" target="_bank" class="btn btn-default btn active" role="button">文件上传</a>

    <a href="${contextPath}/views/sitemesh/demo.jsp" target="_bank" class="btn btn-default btn active" role="button">sitemesh装饰</a>

    </div>
   
</div>

</body>
</html>