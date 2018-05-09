<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<title>真爱有礼</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">

<meta name="description"
	content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">

<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/lib/weui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/jquery-weui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/base.css">
<script src="${pageContext.request.contextPath}/mobile/js/base.js"></script>
<script src="${pageContext.request.contextPath}/mobile/lib/jquery-2.1.4.js"></script>
<script src="${pageContext.request.contextPath}/mobile/lib/fastclick.js"></script>
<script src="${pageContext.request.contextPath}/mobile/js/jquery-weui.js"></script>

<script type="text/javascript">
$(function(){
	var msg = $("#msg").val();
	if(msg){
		alert(msg);
		return;
	}
	
	var homepageId = $("#homepageId").val();
	var page = parseInt($("#page").val()) + 1;	
	$("#changeAds").bind("click", function(){
		window.location.href="homepage?homepageId="+homepageId+"&page=" + page;
	});
});
</script>
</head>
<body ontouchstart>
	<input type="hidden" id="msg" value="${msg}">
     <div class="relative">
        <img src="images/bc.png" style="width:100%">
        <h1 class="demos-title font bt84" style="bottom:84%">集卡列表</h1>
       
          <div class="demos-content-padded mtj118">
          <c:forEach items="${cardss}" varStatus="i" var="cards" >  
            <div  class="box mt10">
              <div class="center"><span class='centerspan'>卡片名称<span></div>
              <div class="midspan center  mb10">${cards.name}</div>
              <a href="userCard?cardsId=${cards.id}" class="btnscan">查看</a>
            </div>
          </c:forEach>  
          </div>
      </div>   
<!--     <div class="bomtton"> -->
<!--       <h1 class="bomttomh1">查看更多</h1> -->
<!--     </div>   -->
  </body>
</html>
