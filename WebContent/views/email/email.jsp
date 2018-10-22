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
                <div class="form-group has-success has-feedback">
                    <input type="text" placeholder="请输入要发送的邮箱地址" class="form-control" id="emailSendAdress" aria-describedby="inputSuccess2Status">
                    <input class="form-control input-ms" id="emailSubject" type="text" placeholder="发送的邮件名称">
                    <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
                </div>
                <textarea class="form-control content"  rows="6"></textarea>

                <button type="button" class="btn btn-success queuebtn m-t10">发送邮件</button>

            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    $(function () {
        var requestParam = {};
        $(".queuebtn").click(function () {
            var content = $(".content").val();
            requestParam.to = $("#emailSendAdress").val();
            requestParam.subject = $("#emailSubject").val();
            requestParam.content = content;
            var da = ajaxPost("/email/sendEmail.action",requestParam);
        });

    });
</script>
</body>
</html>
