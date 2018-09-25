<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件下载</title>
</head>
<body>

<!-- 路径的拼接   "http://localhost:8090/file/openFile.action?fileCode="+tp+"&name="+pname  (其中tp和pname是变量)             -->

<a href="http://localhost:8090/file/openFile.action?fileCode=tp">文件的在线预览</a>
</br>

<a href="http://localhost:8090/file/openFile.action?fileCode=tp" download="">文件的下载</a>

</body>

<script>

</script>

</html>
