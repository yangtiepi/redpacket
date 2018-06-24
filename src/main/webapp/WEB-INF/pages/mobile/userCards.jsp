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
  <title>集字兑换</title>
</head>
<body>
<div class="header">
  <a href="#" onclick="history.back();">
    <img src="${pageContext.request.contextPath}/mobile/img/back.png" width="24">
  </a>
  <h1 class="title">集字兑换</h1>
</div>
<div class="body">
  <div class="top">
    <div class="row">
      <div class="col start">
        <img class="img record" src="${pageContext.request.contextPath}/mobile/img/icon_change.png">
      </div>
      <div class="col end">
        <img class="img logo" src="${pageContext.request.contextPath}/mobile/img/logo.png">
      </div>
    </div>
  </div>
  <div class="tips-box">
    <div class="tips-wrap">
      <h2>集字兑换说明</h2>
      <p>说明说明说明说明说明说明说明说明说明说明说明说明说明说明说明说明说明说明</p>
    </div>
  </div>
  <div class="word-box">
    <ul class="word-group">
      <c:forEach items="${cards}" var="card">

        <li class="word-item">
          <div>
            <img src="${pageContext.request.contextPath}/mobile/img/small_bg.png">
          </div>
          <span class="word">${card.name}</span>
          <span class="number">${card.cardNum}</span>
        </li>
      </c:forEach>
    </ul>
  </div>
  <div class="btn-box-yellow">
    <ul class="btn-group">
      <li class="btn-yellow" onclick="exchange(1)">
        <span>三张兑换</span>
        <img src="${pageContext.request.contextPath}/mobile/img/btn_center_bg.png">
      </li>
      <li class="btn-yellow" onclick="exchange(2)">
        <span>五张兑换</span>
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
    <li class="active">
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
<div class="shade"></div>
<div class="popup">
  <div class="pop-header">
    <h2 id="message">兑换成功</h2>
  </div>
  <div class="pop-body">
    <p>验证码：<span id="code">XOISODAS</span></p>
  </div>
  <div class="pop-footer">
    <p>请现场凭验证码领取奖品</p>
  </div>
</div>
<script rel="script" type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script>
  $(function () {
    // footer 切换
    $('.footer li').on('click',function () {
      $(this).addClass('active').siblings('li').removeClass('active')
    })
    // 关闭弹窗
    $('.shade').on('click',function () {
      //关闭弹窗
      $('.shade').fadeOut();
      $('.popup').fadeOut();
      window.location.reload();
    })
  })

  var isClick =false;
  function exchange(cardsId){
      if(isClick){
          return;
      }
      $.ajax({
          type: "POST",
          async: false,
          traditional:true,
          data: {
              "cardsId":cardsId
          },
          cache:false,
          dataType: "json",
          url: "/mobile/exchange",
          success: function(data){
              if(data.success){
                  $("#code").html(data.data.code);
                  // 打开弹窗
                  $('.shade').fadeIn();
                  $('.popup').fadeIn();
                  isClick = true;
              }else{
                  alert("兑换失败,"+data.message);
              }
          }
      });
  }
</script>
</body>
</html>