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
    var returndata ={"code":200,"message":"数据获取成功","data":null};
    var data = JSON.stringify(data);
    $.ajax({
        type: 'post',
        url: url,
        data:data,
        // dataType:"json",//接受格式
        //cache: false,
        //processData: true,
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
 * 初始化左侧导航
 */
$.fn.initLeftNav = function(name){
    $("#nav-parent").find("span").each(function(index,item){
        $(this).removeClass("glyphicon-chevron-right");
    })
    $("#nav-parent").find("a").each(function(index,item){
        if(typeof($(item).attr('code')) != "undefined" && $(item).attr('code') == name){
            $(this).parents('ul').css('display','block');
            //var val = $(this).attr('href1');//attr 自定义的属性    prop 固有的属性
            /* $("#iframe-body").attr("src",val);*/
            $(this).find("span").addClass("glyphicon-chevron-right");
            return false;
        }
    })

}