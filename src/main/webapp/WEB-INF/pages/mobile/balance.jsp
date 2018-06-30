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
  <title>余额</title>
</head>

<style>
    .body{
        padding-bottom: 50px;
    }
    img{ 
    pointer-events: none; 
    } 
</style>
<body oncontextmenu="return false" onselectstart="return false">
<div class="header">
  <a href="javascript:back();">
    <img src="${pageContext.request.contextPath}/mobile/img/back.png" width="24">
  </a>
  <h1 class="title">余额</h1>
</div>
<div class="body">
  <div class="money-box">
    <div class="money-wrap">
      <span class="inner">
        <h3>余额</h3>
        <p>${user.amount}</p>
      </span>
    </div>
  </div>
  <div class="btn-box-yellow">
    <ul class="btn-group">
      <li class="btn-yellow">
        <span>转入微信余额</span>
        <img src="${pageContext.request.contextPath}/mobile/img/btn_center_bg.png">
      </li>
    </ul>
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
      <a href="${pageContext.request.contextPath}/mobile/userInfo" class="three">
        <span>个人中心</span>
      </a>
    </li>
  </ul>
</div>
<script rel="script" type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script>
  $(function () {
    // footer 切换
    $('.footer li').on('click',function () {
      $(this).addClass('active').siblings('li').removeClass('active')
    })
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

    function back(){
        window.history.back()
    }
</script>
</body>
</html>