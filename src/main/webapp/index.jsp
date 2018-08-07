<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
    <!-- 引入全局 stylesheet 和 script -->
    <%-- <jsp:include page="${contextPath}/views/common/script.jsp"/>
    <jsp:include page="${contextPath}/views/common/stylesheet.jsp"/> --%>
    <title>Insert title here</title>
</head>
<body>
<form id="uploadForm" enctype="multipart/form-data">
    用户名：<input type="text" name="user"/><br/>
    文件1：<input type="file" name="file"/><br/>
    文件2：<input type="file" name="file02"/><br/>
</form>
<button id="upload">上传文件</button>
<button id="checkProgress">查看进度</button>


<form enctype="multipart/form-data" method="post" action="file/upload.action">
    文件3：<input type="file" name="file" multiple="multiple" /><br/>
  
    <input type="submit" value="提交">
</form>

</body>

<script type="text/javascript">
    $(function () {
        $("#upload").click(function () {
            var formData = new FormData($('#uploadForm')[0]);
            formData.append("param","bbbbbbbbbbb");
            $.ajax({
                type: 'post',
                url: "file/upload.action",
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
            }).success(function (data) {
                alert(data);
            }).error(function () {
                alert("上传失败");
            });

            $("#checkProgress").click(function () {
	            $.ajax({
	                type: 'post',
	                url: "file/getProgress.action",
	                data: {},
	                cache: false,
	                processData: false,
	                contentType: "application/json",
	            }).success(function (data) {
	                alert(data);
	            }).error(function () {
	                alert("上传失败");
	            });
            })

        });
    });
</script>

</html>