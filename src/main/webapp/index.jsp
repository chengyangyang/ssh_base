<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="t/upload" method="post" enctype="multipart/form-data">
用户名：<input type="text" name="user"/><br/>
文件1：<input type="file" name="file01"/><br/>
文件2：<input type="file" name="file02"/><br/>
<input type="submit" value="提交">
</form>

<form action="t/getuploadurl" method="post" enctype="multipart/form-data">

<input type="submit" value="提交1">
</form>
</body>
</html>