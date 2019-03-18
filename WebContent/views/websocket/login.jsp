<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/views/common/JsAndCss.jsp"/>
<head>
    <title>websocket登录</title>
</head>
<body>
<div class="container">

    <div class="m-t30">
        请输入登录的用户名称：<input id="user_name" type="text">
        <button id="login">登录</button>
        请输入发送人的用户名称：<input id="to_user_name" type="text">
    </div>
    <div>
        <div>你用户名称为<span id="r_user_name" style="color: white;background-color: black"></span></div>
        <div>sessionId为<span id="r_session_id" style="color: white;background-color: black"></span></div>
    </div>

    <div>
        <div
            style="width: 600px; height: 240px; overflow-y: auto; border: 1px solid #333;" id="show">
            <div id="showChatMessage"></div>
            <div id="showText"/>
            <input type="text" size="80" id="msg" name="msg" placeholder="输入聊天内容" />
            <input type="button" value="发送" id="sendBn" name="sendBn" onclick="send()">
        </div>


    </div>


</div>

</body>

<script type="text/javascript">
    $(function () {
        // 登录
        $("#login").click(function(){
            var data = {};
            data.userName = $("#user_name").val();
            var result = ajaxPost("/websocket/login.action",data);
            if(result.msg == "success"){
                $("#r_user_name").text(result.data.userName);
                $("#r_session_id").text(result.data.id);
                startWebsockt();
            }
        });

    });

    // websocket 监控
    var ws = null;
    function startWebsockt(){
        // 判断当前浏览器是否支持
        if('WebSocket' in window){
            ws = new WebSocket("ws://localhost:80/chat")
        }else{
            alert("当前浏览器不支持websocket！")
        }
        /**
         * 监控三种状态的js变化
         *
         */
        ws.onopen = function(message) {
            // 连接回调
        };
        ws.onclose = function(message) {
            // 断开连接回调
        };
        ws.onmessage = function(message) {
            // 消息监听
            showMessage(message.data);
        };
        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function() {
            ws.close();
        };
        //关闭连接
        function closeWebSocket() {
            ws.close();
        }
    };

    //发送消息
    function send() {

        var input = document.getElementById("msg");
        var text = input.value;
        var to_user_name = document.getElementById("to_user_name");
        var toUser = to_user_name.value;
        // 消息体JSON 对象 对应JAVA 的 Msg对象
        var data = {
            // 定点发送给其他用户的userId
            toId: toUser,
            data: text
        }

        ws.send(JSON.stringify(data));
        input.value = "";
    }

    function showMessage(message) {
        /*var text = document.createTextNode(JSON.parse(message).data);
        var br = document.createElement("br")
        var div = document.getElementById("showChatMessage");
        div.appendChild(text);
        div.appendChild(br);*/
        var text = document.createTextNode(message);
        document.getElementById("showText").appendChild(text);
    }


</script>
</html>
