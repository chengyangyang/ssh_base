<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>form表单的测试</title>
    <style rel="stylesheet">
        .jm-search-item{
            height: 24px;
            width: 110px;
        }
        label{
            padding-left: 10px;
        }
    </style>
</head>


<body>
   <div class="content-center">
        <div class="box-m10">
            <div class="box-b2 h-41" style="">
                <button class="btn btn-primary btn-sm" style="margin-top: 4px">
                    <span class="sys-add-icon"></span>
                    添加通知公告
                </button>
            </div>
            <div>
                <div class="form-inline">
                    <div class="form-group m-t10">
                        <label class="">标题</label>
                        <input type="text" class="jm-search-item">
                    </div>
                    <div class="form-group m-t10">
                        <label class="">发布单位</label>
                        <input type="text" class="jm-search-item">
                    </div>
                    <div class="form-group m-t10">
                        <label class="">所属平台</label>
                        <select>
                            <option value>请选择...</option>
                            <option value="gp">挂牌</option>
                            <option value="jj">竞价</option>
                        </select>
                    </div>
                    <div class="form-group m-t10">
                        <label class="">发布时间</label>
                        <input type="text" class="jm-search-item" id="startDate">
                        <span class="">--</span>
                        <input type="text" class="jm-search-item" id="endDate">
                    </div>
                </div>
            </div>
        </div>
   </div>

<script>
    $(function(){
        $("#startDate").on('click',function(){
            var enddate = $("#endDate").val();
            var obj = {
                elem:"#startDate",
                formmat:"YYYY-dd-MM"
            };
            enddate&&(obj.min = enddate);
            laydate(obj);
        })

    })
    window.onload = function(){
        $("#nav-parent").initLeftNav("4");
    }

</script>
</body>
</html>
