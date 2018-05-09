<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>${ad.title}</title>
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
$(function(){
	var msg = $("#msg").val();
	var adId = $("#adId").val();
	var homepageId = $("#homepageId").val();
	if(msg){
		alert(msg);
		if(adId != null && adId != "" && homepageId != null && homepageId != "" ){
			window.location.href="ad?adId="+adId+"&homepageId="+homepageId;
		}
		return;
	}
	
	var userCardId = $("#userCardId").val();
	var redpacketLogId = $("#redpacketLogId").val();
	$("#confirmRedpacket").bind("click", function(){
		if(redpacketLogId){
			// 发送AJAX请求，修改ID对应数据状态
			$.get("confirmRedpacket",{userCardId:userCardId,redpacketLogId:redpacketLogId},function(data){
				console.log(data);
				obj = data.data;
				console.log(obj);
				if(data.success){
					if(obj.success == "true"){
						alert(obj.msg);
						window.location.href="cardsList";
					}else{
						alert(obj.msg);
						if(obj.homepageId){
						window.location.href="ad?adId="+obj.adId +"&homepageId"+obj.homepageId;
						}
					}
				}
			},"json");
		}else{
			alert("您的领取次数已用完");
		}
	});
});

</script>
</head>
<body ontouchstart>
<input type="hidden" id="msg" value="${msg}">
<input type="hidden" id="adId" value="${adId}">
<input type="hidden" id="homepageId" value="${homepageId}">
	<div class="relative">
		<img src="images/bc.png" style="width: 100%">
		<!-- <h1 class="demos-title font">领取红包</h1>-->

		<div class="demos-content-padded mtj118" >
			<div class="box " style="    padding-top: 0px;">
				<div class="center " style="font-size: 22px;line-height: 3.1;">现金红包</div>
				<div class="center ">
					<c:if test="${card.image != null}">
					<div class="cardname" >
					<img width="100%" src="${pageContext.request.contextPath}/${card.image}">
					</div>
					</c:if>
				</div>
				<div class="center midspan1 ">￥${redpacketLog.amount}</div>
				<div class="center miaoshu ">今日还可以领取${ableDayNum}个红包,本周${ableWeekNum}个,本月${ableMonthNum}个</div>
			</div>

		</div>
	</div>
	<input type="hidden" id="redpacketLogId" value="${redpacketLog.id}">
	<input type="hidden" id="userCardId" value="${userCard.id}">
	<a  class="btnscan mt10" id="confirmRedpacket">领取</a>

</body>
</html>
