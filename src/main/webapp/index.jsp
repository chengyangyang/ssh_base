<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<form id="uploadForm" enctype="multipart/form-data">
用户名：<input type="text" name="user"/><br/>
文件1：<input type="file" name="file"/><br/>
文件2：<input type="file" name="file02"/><br/>
</form>
<button id="upload">上传文件</button>
</body>

<script type="text/javascript">
    $(function () {
        $("#upload").click(function () {
            var formData = new FormData($('#uploadForm'));
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
        });
    });
</script>

</html>