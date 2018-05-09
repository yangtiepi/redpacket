<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<title>${title}</title>
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
	
	var adMaxNum = parseInt($("#adMaxNum").val());
	var clickNum = parseInt($("#clickNum").val());
	$(".imgbox").each(function(index,element){
		if(clickNum >= adMaxNum){
			$(element).removeAttr("click");
			$(element).bind("click", function(){
				alert("今天寻宝已结束，请明天再来寻宝!");
				return;
			});
//		}
// 		if($(this).attr("clickedNum") != "0"){
// 			$(this).bind("click", function(){
// 				alert("已点击过该广告！");
				
// 			});
 		}else{
			$(element).removeAttr("click");
			$(element).bind("click", function(){
				window.location.href='ad?adId='+$(this).attr("adId")+'&homepageId='+homepageId;
				
			});
 		}
	});
});
</script>
</head>
<body ontouchstart>
<input type="hidden" id="msg" value="${msg}">
<input type="hidden" id="adMaxNum" value="${adMaxNum}">
<input type="hidden" id="clickNum" value="${clickNum}">
	<header class="demos-header">
		<h1 class="h1title">${title}</h1>
	</header>
	<img src="${pageContext.request.contextPath}/${image}" style="width: 100%">
	<div class="center">
		<img src="images/tip.png" style="width: 100%"> <span
			class="tipspan">${info}</span>
	</div>
	<div class="demos-content-padded" style="padding-top: 40px;">
		 <c:forEach items="${ads}" varStatus="i" var="ad" >  
			<div class="imgbox"  clickedNum="${ad.clickedNum}" adId="${ad.id}">
				<img src="${pageContext.request.contextPath}/${ad.smallImg}"> 
				<span>${ad.title}</span>
			</div>
        </c:forEach>  
	</div>
	<div class="bomtton">
		<input type="hidden" id="homepageId" value="${id}">
		<input type="hidden" id="page" value="${page}">
		<h1 class="bomttomh1" id="changeAds">换一批</h1>
	</div>
	<div style="height: auto; text-align: center;">
		本活动由广州寻宝互动传媒有限责任公司承办
	</div>
</body>
</html>
