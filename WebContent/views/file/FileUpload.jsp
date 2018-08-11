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

<div class="progress" style="display: none;">
    <div class="progress-bar" role="progressbar" aria-valuenow="60"
         aria-valuemin="0" aria-valuemax="100" style="width: 0;">
        <span id="checkProgress"></span>
    </div>
</div>

<form id="uploadForm" enctype="multipart/form-data">
    用户名：<input type="text" name="user"/><br/>
    文件1：<input type="file" name="file"/><br/>
    文件2：<input type="file" name="file02"/><br/>
</form>
<button id="upload">上传文件</button>

<form enctype="multipart/form-data" method="post" action="file/upload.action">
    多文件上传：<input type="file" name="file" multiple="multiple" /><br/>
    <input type="submit" value="提交">
</form>

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