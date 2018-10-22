<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/views/common/JsAndCss.jsp"/>
    <title>文件下载</title>
</head>
<body>
    <div class="container">

        <div class="row m-t10">

            <!-- 路径的拼接   "http://localhost:8090/file/openFile.action?fileCode="+tp+"&name="+pname  (其中tp和pname是变量)             -->
            <div class="col-md-4 col-md-offset-4">
                <a class="btn btn-success" href="http://localhost:8090/file/openFile.action?fileCode=tp">文件的在线预览</a>
            </div>
        </div>

        <div class="row m-t10">
            <div class="col-md-4 col-md-offset-4">
                <a class="btn btn-info" href="http://localhost:8090/file/openFile.action?fileCode=tp" download="">文件的下载</a>
            </div>
        </div>
    </div>

</body>

<script>

</script>

</html>
