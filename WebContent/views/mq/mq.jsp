<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/views/common/JsAndCss.jsp"/>
<head>
    <title>消息队列的使用</title>


</head>
<body>

<form role="form">
    <div class="form-group">

        <div class="row" >
            <div class="col-md-6 col-md-offset-3 text-center">
                <label for="name" style="font-size: 20px">请输入要发布的消息</label>
             <textarea class="form-control content"  rows="6"></textarea>

                <button type="button" class="btn btn-success queuebtn">发送Queue消息</button>
                <button type="button" class="btn btn-success topicbtn">发送Topic消息</button>
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
            var da = ajaxPost("/mq/sendMessageQueue.action",requestParam);
        });
        $(".topicbtn").click(function () {
            var content = $(".content").val();
            requestParam.message = content;
            var da = ajaxGet("/mq/sendMessageTopic.action",requestParam);
        });
        
    });
</script>
</body>
</html>
