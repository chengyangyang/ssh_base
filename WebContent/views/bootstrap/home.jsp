<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="/views/common/JsAndCss.jsp"/>
    <link rel="stylesheet" href="${contextPath}/views/bootstrap/css/home.css">
    <%--<title>bootstrap的练习</title>--%>
    <title> <sitemesh:write property='title' /> bootstrap的练习</title>
    <sitemesh:write property='head' />
</head>
<body style="background-image:  url(/static/img/picture/view-mgt-01.png);background-repeat: repeat-y;background-position:top center;">
    <div class="container m-t40 h-800">
        <div id="nav-parent" class="col-md-2 h-p100 g-c" style="background-color: #1C2A35;color: #cac9c9;">
            <div class="row m-b10" style="border-bottom: 2px solid #17ff36;">
               <div class="col-md-10 col-md-offset-2">
                   <h4>导航菜单</h4>
               </div>
           </div>
        </div>
        <div class="col-md-10 h-p100" style="background-color: white">
            <div class="row h-41" style="border-bottom: 2px solid #17ff36;line-height: 41px">
                <div class="pull-left p-lr10">
                    <span>你好!</span>&nbsp;&nbsp;
                    <span>admin</span>&nbsp;
                    <span class="line-l-short sys-sitehome-icon t-5"></span>
                    <span>后台首页</span>
                </div>
            </div>
            <h6 class="">丝绸之路经济带技术</h6>
            <div class="h-p100">
            <%--<div class="h-p100" style="background-image: url(/static/img/picture/index-image.png);background-repeat: no-repeat;">--%>
               <%-- <p class="p-a" style="font-size: 30px;margin: 50px auto auto 50px;">
                    你好!
                </p>
                <p class="p-a" style="font-size: 30px;margin: 80px auto auto 50px;">
                    欢迎进入我的系统
                </p>--%>

                <%--使用这个的缺陷是,导航栏和url不会进行变动--%>
                <%--<iframe id="iframe-body" src="" width="100%" frameborder="0" scrolling="no" style="height:inherit;overflow:hidden;"></iframe>--%>
                <sitemesh:write property='body' />
            </div>
        </div>
    </div>

    <script src="${contextPath}/views/bootstrap/js/home.js"></script>
</body>

</html>
