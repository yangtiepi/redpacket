<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mobile/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mobile/css/main.css">
    <title>个人中心</title>
</head>
<body>
<input type="hidden" id="code" value="${code}">
<div class="header">
    <a href="#" onclick="history.back();">
        <img src="${pageContext.request.contextPath}/mobile/img/back.png" width="24">
    </a>
    <h1 class="title">个人中心</h1>
</div>
<div class="body">
    <div class="top">
        <div class="row">
            <div class="col start">
                <img class="img record" src="${pageContext.request.contextPath}/mobile/img/icon_z.png">
            </div>
            <div class="col end">
                <img class="img logo" src="${pageContext.request.contextPath}/mobile/img/logo.png">
            </div>
        </div>
    </div>
    <div class="avatar-box">
        <div class="avatar-img">
            <img src="${user.headImage}">
        </div>
        <div class="nickname-box">
            <p class="nickname">${user.username}</p>
        </div>
    </div>
    <div class="describe">
        <p>累计扫码<span> ${drawLogSum.num}</span>次</p>
        <p>累计红包<span> ${drawLogSum.amount} </span>元</p>
    </div>
    <div class="card-group">
        <ul>
            <li class="card1">
                <a href="${pageContext.request.contextPath}/mobile/drawRecord">
                    <img src="${pageContext.request.contextPath}/mobile/img/icon_center_cj.png">
                    <p>抽奖记录</p>
                </a>
            </li>
            <li class="card2">
                <a href="${pageContext.request.contextPath}/mobile/userCards">
                    <img src="${pageContext.request.contextPath}/mobile/img/icon_center_card.png">
                    <p>我的卡片</p>
                </a>
            </li>
            <li class="card3">
                <a href="${pageContext.request.contextPath}/mobile/balance">
                    <img src="${pageContext.request.contextPath}/mobile/img/icon_center_money.png">
                    <p>余额</p>
                </a>
            </li>
        </ul>
    </div>
    <div class="notice">
        <div class="notice-item">
            <h2>公告</h2>
            <p>固定文字固定文字固定文字固定文字固定文字固定文字固定文字</p>
        </div>
        <div class="notice-item" style="margin-top: 15px;">
            <h2>友情提示</h2>
            <p>固定文字固定文字固定文字固定文字固定文字固定文字固定文字</p>
        </div>
    </div>
</div>
<div class="footer">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/mobile/drawCenter" class="first">
                <span>抽奖中心</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/mobile/userCards" class="second">
                <span>集字兑换</span>
            </a>
        </li>
        <li class="active">
            <a href="#" class="three">
                <span>个人中心</span>
            </a>
        </li>
    </ul>
</div>
<script rel="script" type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/mobile/js/jq_scroll.js"></script>
<script>
    $(function () {
        // footer 切换
        $('.footer li').on('click', function () {
            $(this).addClass('active').siblings('li').removeClass('active')
        })
        $("#scrollDiv").Scroll({line: 1, speed: 500, timer: 2000});

        if(!window.localStorage){
            alert("当前浏览器版本过低！");
            return false;
        }else{
            var storage=window.localStorage;
            var code = $("#code").val();
            if(code){
                storage["code"]=code;
            }
        }
    })
</script>
</body>
</html>