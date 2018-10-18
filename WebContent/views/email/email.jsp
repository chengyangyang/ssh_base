<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/views/common/JsAndCss.jsp"/>
<head>
    <title>发送邮件</title>
</head>
<body>
<form role="form">
    <div class="form-group">

        <div class="row" >
            <div class="col-md-6 col-md-offset-3 text-center">
                <label for="name" style="font-size: 20px">发送邮件</label>
                <textarea class="form-control content"  rows="6"></textarea>

                <button type="button" class="btn btn-success queuebtn">发送邮件</button>

            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    $(function () {
        var requestParam = {};
        $(".queuebtn").click(function () {
            var content = $(".content").val();
            requestParam.message = content;
            requestParam.subject = "测试";
            requestParam.to = "296421181@qq.com";
            requestParam.content = "邮件内容";
            var da = ajaxPost("/email/sendEmail.action",requestParam);
        });

    });
</script>
</body>
</html>
