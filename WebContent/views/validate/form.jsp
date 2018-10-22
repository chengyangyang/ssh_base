<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/views/common/JsAndCss.jsp"/>
    <title>form表单的校验</title>
</head>
<body>

    <div class="container">
        <div style="" class="content-center w-500 m-t40 h-600">

            <!-- class都是bootstrap定义好的样式，验证是根据input中的name值 -->
            <form id="defaultForm" class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-2">用户名</label>
                    <div class="col-md-10">
                        <input class="form-control" name="username" type="text" placeholder="请输入用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">密码</label>
                    <div class="col-md-10">
                        <input class="form-control" name="password" type="password" placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">邮箱</label>
                    <div class="col-md-10">
                        <input class="form-control" name="email" type="text" placeholder="请输入邮箱">
                    </div>
                </div>
                <div class="form-group">
                    <input class="btn btn-success col-md-4 col-md-offset-4" name="submit" type="submit" value="提交">
                </div>
            </form>
        </div>
    </div>
    <script type="text/javascript">

        $(document).ready(function(){
            $("#defaultForm").bootstrapValidator({
                message:"不进行校验的值!",
                feedbackIcons:{//输入不同的状态,显示的图片的样式不同
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields:{//校验
                    username:{//键名username和name中的值对应
                        validators:{
                            notEmpty:{//非空校验
                                message:"用户名称不能为空"
                            },
                            stringLength:{//长度校验
                                min:6,
                                max:10,
                                message:"用户名长度在6-10位之间"
                            },
                            regexp:{//使用正则表达式
                                regexp:getReg().num,
                                message:"只能使用数字"
                            },
                            /*identical: {//相同
                                field: 'password', //需要进行比较的input name值
                                message: '两次密码不一致'
                            },
                            different: {//不能和用户名相同
                                field: 'username',//需要进行比较的input name值
                                message: '不能和用户名相同'
                            },*/
                        }
                    },
                    password:{
                        validators:{
                            notEmpty:{
                                message:"密码不能为空"
                            }
                        }
                    },
                    email:{
                        validators:{
                            notEmpty:{
                                message:"邮箱地址不能为空"
                            },
                            emailAddress:{//邮箱地址的校验
                                message:"邮箱格式不正确"
                            }

                        }
                    }

                }

            })


        })

    </script>
</body>


</html>
