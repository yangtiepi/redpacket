<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<html lang="zh-cn" manifest="demo.appcache">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mobile/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mobile/css/main.css">
    <title>抽奖记录</title>
</head>

<style>
    .body{
        padding-bottom: 50px;
    }
</style>
<body oncontextmenu="return false" onselectstart="return false">
<div class="header">
    <a href="javascript:;" id="back_btn">
        <img src="${pageContext.request.contextPath}/mobile/img/back.png" width="24">
    </a>
    <h1 class="title">抽奖结果</h1>
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
    <div class="word-box">
        <div class="word-wrap">
            <div class="center">
                <h2>微信红包</h2>
                <p>${drawLog.amount}元</p>
            </div>
        </div>
    </div>
    <div class="btn-wrap">
        <div class="btn-box">
            <a href="${pageContext.request.contextPath}/mobile/userInfo">
                <img src="${pageContext.request.contextPath}/mobile/img/btn_center_bg.png" width="100%">
                <span>领取</span>
            </a>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/mobile/js/jquery.min.js"></script>
<script>
    $(function () {
    })
    $(function () {
    var isPageHide = false;
    window.addEventListener('pageshow', function () {
        if (isPageHide) {
            window.location.reload();
        }
    });
    window.addEventListener('pagehide', function () {
        isPageHide = true;
    });
})

    $(function(){
	if($('#back_btn')){
	$('#back_btn').attr({'onclick':'history.go(-1)'})
	}

})
</script>
</body>
</html>