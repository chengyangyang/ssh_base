<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/views/common/JsAndCss.jsp"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>laydate</title>
</head>

<body>
    <div class="container">
        <div class="form-group">
            <input type="text" class="form-control" id="startDate" readonly="" placeholder="请输入开始时间">
        </div>

        <div class="form-group">
            <input type="text" class="form-control" id="endDate" readonly="" placeholder="请输入结束时间">
        </div>

    </div>

</body>

<script type="text/javascript">
    $(function () {
        $('#startDate').on('click',function(){
            var end = $('#endDate').val();
            var obj = {
                elem:'#startDate',
                show:true
            };
            end&&(obj.max = end);
            laydate.render(obj);
        })

        $('#endDate').on('click',function(){
            var start = $('#startDate').val();
            var obj = {
                elem:'#endDate',
                show:true
            };
            start&&(obj.min =start);
            laydate.render(obj);
        })

    });
</script>


</html>