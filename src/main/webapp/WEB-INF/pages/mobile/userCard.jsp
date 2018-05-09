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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/mobile/lib/weui.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/mobile/css/jquery-weui.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/mobile/css/base.css">
<script src="${pageContext.request.contextPath}/mobile/js/base.js"></script>
<script
	src="${pageContext.request.contextPath}/mobile/lib/jquery-2.1.4.js"></script>
<script src="${pageContext.request.contextPath}/mobile/lib/fastclick.js"></script>
<script
	src="${pageContext.request.contextPath}/mobile/js/jquery-weui.js"></script>

<script type="text/javascript">
	$(function() {
		var msg = $("#msg").val();
		if (msg) {
			alert(msg);
			return;
		}
		
		var completeNum = $("#completeNum").val();
		var cardsId = $("#cardsId").val();
		var complete = $("#complete");
		if(completeNum <= 0){
			complete.bind("click", function(){
				alert("集齐卡片才能领取！");
			});
		}else{
			complete.attr("href","cardsComplete?cardsId="+cardsId);
		}
	});
</script>
</head>
<body ontouchstart>
	<input type="hidden" id="msg" value="${msg}">
	<div class="relative">
		<img src="images/bc.png" style="width: 100%">
		<h1 class="demos-title font" style="bottom: 92%;">我的卡片</h1>
		<div class="demos-content-padded mtj100">
			<div class="box1">
				<c:forEach items="${cards}" varStatus="i" var="card">
					<div class="cardbox">
						<c:if test="${card.cardNum != 0}">
						<div class="cardname" style="color: #ff4459;font-size:57px">${card.name}</div>
						<div class="cardnum">×${card.cardNum}</div>
						</c:if>
						<c:if test="${card.cardNum == 0}">
						<div class="cardname" style="color: gray;font-size:57px">${card.name}</div>
						<div class="cardnum">×${card.cardNum}</div>
						</c:if>
					</div>
				</c:forEach>
				<div class="clear"></div>
				<div class="mt100 mb30">
					<input type="hidden" value="${completeNum}" id="completeNum"> 
					<input type="hidden" value="${cardsId}" id="cardsId"> 
					<a id="complete" href="#" class="btn">我要兑换×${completeNum}</a>
				</div>
			</div>
		</div>
		<div class="boma">
			<h1 class="bomspan"
				onclick="window.location.href='cardsInfo?cardsId=${cardsId}';">集卡说明</h1>
		</div>

	</div>

</body>
</html>
