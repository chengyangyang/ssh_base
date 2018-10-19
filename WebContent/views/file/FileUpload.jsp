<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/views/common/JsAndCss.jsp"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<body>
<div class="container p-t50">
    <div style="" class=" content-center w-500 g-c h-600">

        <div class="form-group">
            <label>用户名</label>
            <input class="form-control" placeholder="请输入用户名">
        </div>
        <div class="form-group">
            <label>密码</label>
            <input class="form-control" placeholder="请输入密码">
        </div>

        <form id="uploadForm" enctype="multipart/form-data">
            <label>文件1</label>
            <input type="file" name="file"/>

            <label>文件2</label>
            <input type="file" name="file02"/>
            <button type="button" class="btn btn-primary btn-ms btn-block" id="upload">上传文件</button>

        </form>

        <form enctype="multipart/form-data" method="post" action="file/upload.action">

            <label style="">多文件上传</label>
            <input type="file" name="file" multiple="multiple" />

            <input type="submit" class="btn btn-default col-md-4 col-md-offset-4" value="提交">
        </form>

        <div class="progress" style="display: none;">
            <div class="progress-bar" role="progressbar" aria-valuenow="60"
                 aria-valuemin="0" aria-valuemax="100" style="width: 0;">
                <span id="checkProgress"></span>
            </div>
        </div>


    </div>
</div>


</body>

<script type="text/javascript">
    $(function () {
        var pro;
        $("#upload").click(function () {
        	//显示样式
        	$(".progress").show();
        	pro = setInterval(progress,1000);
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
                clearInterval(pro);
                $(".progress").css("display","none");
                alert("上传失败");
            });
           
        });
        function progress () {
            $.ajax({
                type: 'post',
                url: "file/getProgress.action",
                data: {},
                cache: false,
                processData: false,
                contentType: "application/json",
            }).success(function (data) {
                if(typeof (data.percent) != "undefind" && data.percent != null){
                   var per = data.percent +'%';
                    $(".progress-bar").css("width",per);
                    $("#checkProgress").html(per);
                    if(per == "100%"){
                        clearInterval(pro);
                        $(".progress").hide();
                    }
                }
                console.log(data);
            }).error(function () {
                alert("上传失败");
            });
        }

    });
</script>
</html>