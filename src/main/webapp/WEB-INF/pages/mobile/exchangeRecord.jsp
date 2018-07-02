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
  <title>兑奖记录</title>
</head>
<style>
  .slide{
    padding: 0;
    height: 250px;
    overflow: scroll;
    box-sizing: border-box;
  }
  .scroll-group{
    background: rgba(0,0,0,.3);
    /*height: 200px;*/
  }
  .scroll-group .scroll-list{
    display: flex;
    align-items: center;
    justify-content: space-around;
  }
</style>

<style>
    .body{
        padding-bottom: 50px;
    }
</style>
<body oncontextmenu="return false" onselectstart="return false">
<div class="header">
  <a href="javascript:;" id="back_btn">
    <img src="img/back.png" width="24">
  </a>
  <h1 class="title">兑奖记录</h1>
</div>
<div class="body">
  <div class="top">
    <div class="row">
      <div class="col start">
        <img class="img record" src="${pageContext.request.contextPath}/mobile/img/icon_exchange.png">
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
  <div class="slide">
    <div id="scrollDiv">
      <ul class="scroll-group">
      <c:forEach var="cardsComplete" items="${cardsCompleteList}">
        <li class="scroll-list">
          <div class="name">${cardsComplete.userName}</div>
          <div class="content">
            <p>兑换了<span>"${cardsComplete.cardsName}"</span></p>
          </div>
          <div class="time">
            <fmt:formatDate value="${drawLog.receiveTime}" var="receiveTime" pattern="HH:mm:ss"/>
              ${receiveTime}
          </div>
        </li>
      </c:forEach>
      </ul>
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
      <a href="${pageContext.request.contextPath}/mobile/userInfo" class="three">
        <span>个人中心</span>
      </a>
    </li>
  </ul>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/mobile/js/jquery.min.js"></script>
<script type="text/javascript" src="js/jq_scroll.js"></script>
<script>
    $(function () {
        // footer 切换
        $('.footer li').on('click',function () {
            $(this).addClass('active').siblings('li').removeClass('active')
        })
        // $("#scrollDiv").Scroll({line:1,speed:500,timer:2000});
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
</html></html>