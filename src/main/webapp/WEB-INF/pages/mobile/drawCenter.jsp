<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>焦点轮播图3D旋转木马特效动画切换jQuery插件</title>
    <meta name="keywords" content="焦点,轮播图,3D,旋转木马,特效动画切换,jQuery插件"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mobile/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mobile/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/new.css" type="text/css" media="screen">

</head>

<body>

<div class="header">
    <a href="#">
        <img src="${pageContext.request.contextPath}/mobile/img/back.png" width="24">
    </a>
    <h1 class="title">奖励记录</h1>
</div>
<div class="body">
    <div class="logo" style="text-align: center;margin-top: 15px;margin-bottom: 30px;">
        <img src="${pageContext.request.contextPath}/mobile/img/logo.png" width="150" alt="">
    </div>
    <div class="result">
        <h2 class="text-center" style="font-size: 17px;font-weight:bold;color: #ffffff;letter-spacing: 1px;">你购买的是</h2>
        <p class="text-center" style="margin-top: 10px;font-weight:bold;font-size: 20px;color: #f8bc0c;letter-spacing: 3px;">正品正品正品</p>
    </div>
    <!--轮播历史区域-->
    <div class="history" style="margin-top: 24px;">
        <p class="text-center" style="color: #fff;font-size: 14px;">奖励记录</p>
        <div class="slide">
            <div id="scrollDiv">
                <ul class="scroll-group">
                    <c:forEach var="drawLog" items="${drawLogs}">
                        <li class="scroll-list">
                            <div class="text-center colorYellow">
                                    ${drawLog.userName}
                                    <fmt:formatDate value="${drawLog.receiveTime}" var="receiveTime" pattern="HH:mm:ss"/>
                                    在${receiveTime}
                                    抽中了${drawLog.remark}
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <div class="row-bot">
        <div class="center-shadow">
            <div class="carousel-container">
                <div id="carousel">
                    <div class="carousel-feature">
                        <a target="_blank">
                            <div class="front">
                                <img class="carousel-image" alt="" src="${pageContext.request.contextPath}/mobile/img/bg_bg.png">
                            </div>
                            <div class="back" style="display: none;">
                                <p style="text-align: center;font-size: 18px;color: #f8bc0c;">谢谢惠顾</p>
                                <img class="carousel-image" alt="" src="${pageContext.request.contextPath}/mobile/img/hot_bg_big.png" style="position: absolute;left: 0;top:0;right: 0;bottom: 0;z-index: -1">
                            </div>
                        </a>
                    </div>
                    <div class="carousel-feature">
                        <a target="_blank">
                            <div class="front">
                                <img class="carousel-image" alt="" src="${pageContext.request.contextPath}/mobile/img/bg_bg.png">
                            </div>
                            <div class="back" style="display: none;">
                                <p style="text-align: center;font-size: 18px;color: #f8bc0c;">谢谢惠顾</p>
                                <img class="carousel-image" alt="" src="${pageContext.request.contextPath}/mobile/img/hot_bg_big.png" style="position: absolute;left: 0;top:0;right: 0;bottom: 0;z-index: -1">
                            </div>
                        </a>
                    </div>
                    <div class="carousel-feature">
                        <a target="_blank">
                            <div class="front">
                                <img class="carousel-image" alt="" src="${pageContext.request.contextPath}/mobile/img/bg_bg.png">
                            </div>
                            <div class="back" style="display: none;">
                                <p style="text-align: center;font-size: 18px;color: #f8bc0c;">谢谢惠顾</p>
                                <img class="carousel-image" alt="" src="${pageContext.request.contextPath}/mobile/img/hot_bg_big.png" style="position: absolute;left: 0;top:0;right: 0;bottom: 0;z-index: -1">
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <ul>
        <li class="active">
            <a href="#" class="first">
                <span>抽奖中心</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/mobile/userCards" class="second">
                <span>集字兑换</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/mobile/userInfo" class="three">
                <span>个人中心</span>
            </a>
        </li>
    </ul>
</div>

<script rel="script" type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/mobile/js/jq_scroll.js"></script>
<script src="${pageContext.request.contextPath}/mobile/js/jquery.featureCarousel.js" type="text/javascript"></script>
<script type="text/javascript">
    var hasClicked = false;
    var code;
    $("#scrollDiv").Scroll({line: 1, speed: 500, timer: 2000});
    $(document).ready(function () {
        $("#carousel").featureCarousel({
            autoPlay: false,
            trackerIndividual: false,
            trackerSummation: false,
            topPadding: 20,
            smallFeatureWidth: .9,
            smallFeatureHeight: .9,
            sidePadding: 0,
            smallFeatureOffset: 40,
            clickedCenter: function (item) {
                if(hasClicked){
                    return alert('你已经抽过奖了')
                }else{
                    hasClicked = true
                }
                drawResult(item);
                // item.find("p").text("0.25元");
                // item.find('.front').hide();
                // var imageWidth = item.width();
                // var height = item.height();
                // console.log(imageWidth,height);
                // item.find('.back').prop('style',"position: relative;display: flex;align-items: center;justify-content: center").width(imageWidth).height(height);
            }
        });

        if(!window.localStorage){
            alert("当前浏览器版本过低！");
            return false;
        }else{
            var storage=window.localStorage;
            code = storage.code;
        }
    });
    function drawResult(item){
        if(!code){
            return alert('你没有抽奖次数！');
        }
        $.ajax({
            type: "POST",
            async: false,
            traditional:true,
            data: {
                "code":code
            },
            cache:false,
            dataType: "json",
            url: "/mobile/draw",
            success: function(data){
                if(data.success){
                    var info = data.info;
                    if(info.type == 1){
                        item.find("p").text(info.amount+"元");
                    }else if(info.type == 2){
                        item.find("p").text(info.name);
                    }else{
                        item.find("p").text("谢谢惠顾");
                    }

                    item.find('.front').hide();
                    var imageWidth = item.width();
                    var height = item.height();
                    console.log(imageWidth,height);
                    item.find('.back').prop('style',"position: relative;display: flex;align-items: center;justify-content: center").width(imageWidth).height(height);
                }else{
                    return alert("抽奖失败,请稍后重试！");
                }
            }
        });
    }
</script>
</body>
</html>