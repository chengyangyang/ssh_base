/**
 * ajax get 请求
 */
function ajaxGet(url,data){
    //var data = JSON.stringify(data);
    var returndata ={"code":200,"message":"数据获取成功","data":null};
    $.ajax({
        type: 'get',
        url: url,
        data: data,
        //cache: false,
        //processData: false,//是否序列化
        contentType: "application/json",
    }).success(function (responsedate) {
        if(typeof (responsedate) != "undefind" && responsedate != null){
            returndata.data = responsedate;
            return returndata;
        }else{
            returndata.code = 201;
            returndata.message = "返回的数据是空!";
            alert("返回的数据是空!");
            return returndata;
        }

    }).error(function () {
        returndata.code = 500;
        returndata.message = "请求错误!";
        alert("请求错误!");
        return returndata;

    });
}

/**
 * ajax post 请求
 */
function ajaxPost(url,data){
    var data = JSON.stringify(data);
    var result;
    $.ajax({
        type: 'post',
        url: url,
        data:data,
        async:false, // 开启关闭异步请求
        // dataType:"json",//接受格式
        //cache: false,
        //processData: true,
        contentType: "application/json",
    }).success(function (responsedate) {
        if(typeof (responsedate) != "undefind" && responsedate != null){
            result = responsedate;
        }else{
            returndata.code = 201;
            returndata.message = "返回的数据是空!";
            alert("返回的数据是空!");
        }

    }).error(function () {
        returndata.code = 500;
        returndata.message = "请求错误!";
        alert("请求错误!");
    });
    return result;
}


/**
 * 初始化左侧导航
 */
$.fn.initLeftNav = function(name){
    $("#nav-parent").find("a").each(function(index,item){
        if(typeof($(item).attr('code')) != "undefined" && $(item).attr('code') == name){
            $(this).parents('ul').css('display','block');
            //var val = $(this).attr('href1');//attr 自定义的属性    prop 固有的属性
            /* $("#iframe-body").attr("src",val);*/
            $(this).find("span").addClass("glyphicon-chevron-right");
            $(this).parent("div").parent("li").parents("li").each(function(index1,item1){
                if($(this).children("div").find("a").find("span").hasClass("glyphicon-plus")){
                    $(this).children("div").find("a").find("span").removeClass("glyphicon-plus");
                    $(this).children("div").find("a").find("span").addClass("glyphicon-minus");
                }
            })
            return false;
        }
    })

}