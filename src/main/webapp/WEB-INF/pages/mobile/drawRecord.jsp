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
  <title>抽奖记录</title>
</head>
<body>
  <div class="header">
    <a href="#" onclick="history.back();">
      <img src="${pageContext.request.contextPath}/mobile/img/back.png" width="24">
    </a>
    <h1 class="title">抽奖记录</h1>
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
    <div class="slide">
      <div id="scrollDiv">
        <ul class="scroll-group">
          <c:forEach var="drawLog" items="${drawLogList}">
            <li class="scroll-list">
              <div class="name">${drawLog.userName}</div>
              <div class="content">
                <p>抽到<span>"${drawLog.remark}"</span></p>
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
<script rel="script" type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/mobile/js/jq_scroll.js"></script>
  <script>
    $(function () {
      // footer 切换
      $('.footer li').on('click',function () {
        $(this).addClass('active').siblings('li').removeClass('active')
      })
      $("#scrollDiv").Scroll({line:1,speed:500,timer:2000});
    })
  </script>
</body>
</html>