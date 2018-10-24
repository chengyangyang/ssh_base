<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <jsp:include page="/views/common/JsAndCss.jsp"/>
    <link rel="stylesheet" href="${contextPath}/views/bootstrap/css/home.css">
    <title>bootstrap的练习</title>
</head>
<body style="background-image:  url(/static/img/picture/view-mgt-01.png);background-repeat: repeat-y;background-position:top center;">
    <div class="container m-t40 h-800">
       <div id="nav-parent" class="col-md-2 h-p100 g-c" style="background-color: #1C2A35;color: #cac9c9;">
           <div class="row m-b10" style="border-bottom: 2px solid #17ff36">
               <div class="col-md-10 col-md-offset-2">
                   <h4>导航菜单</h4>
               </div>
           </div>

       </div>
        <div id="nav-body" class="col-md-10 h-p100">

        </div>
    </div>

</body>


<script src="${contextPath}/views/bootstrap/js/home.js"></script>
</html>
