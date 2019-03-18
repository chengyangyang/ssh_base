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
    <a href="${contextPath}/views/test.jsp" target="_bank" class="btn btn-default btn active" role="button">个人临时测试</a>

    <a href="${contextPath}/views/file/FileUpload.jsp" target="_bank" class="btn btn-default btn active" role="button">文件上传</a>

    <a href="${contextPath}/views/file/fileDownLoad.jsp" target="_bank" class="btn btn-default btn active" role="button">文件文件下载</a>

    <a href="${contextPath}/views/sitemesh/demo.jsp" target="_bank" class="btn btn-default btn active" role="button">sitemesh装饰</a>

    <a href="${contextPath}/views/mq/mq.jsp" target="_bank" class="btn btn-default btn active" role="button">消息队列activeMQ的使用</a>

    <a href="${contextPath}/views/email/email.jsp" target="_bank" class="btn btn-default btn active" role="button">发送邮件</a>

    <a href="${contextPath}/views/validate/form.jsp" target="_bank" class="btn btn-default btn active" role="button">基于bootstrapvalidator form表单的校验</a>

    <a href="${contextPath}/views/bootstrap/home.jsp" target="_bank" class="btn btn-default btn active" role="button">bootstrap的练习</a>

    <a href="${contextPath}/views/layui/laydate.jsp" target="_bank" class="btn btn-default btn active" role="button">laydate练习</a>

    <a href="${contextPath}/views/websocket/login.jsp" target="_bank" class="btn btn-default btn active" role="button">websocket练习</a>

    </div>
   
</div>

</body>
</html>