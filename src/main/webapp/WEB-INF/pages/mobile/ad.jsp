<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
  <head>
    <title>${ad.title}</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">

<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">

<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/lib/weui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/jquery-weui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/base.css">
<script src="${pageContext.request.contextPath}/mobile/lib/jquery-2.1.4.js"></script>
<script src="${pageContext.request.contextPath}/mobile/lib/fastclick.js"></script>
<script src="${pageContext.request.contextPath}/mobile/js/jquery-weui.js"></script>
<script type="text/javascript">
$(function(){
	var msg = $("#msg").val();
	if(msg){
		alert(msg+"!");
		window.location.href = document.referrer;
// 		return;
	}
	
	var clickNum = $("#clickNum").val();
	var homepageId = $("#homepageId").val();
	var adUrl = $("#adUrl").val();
	if(clickNum > 0){
		$(".bomttomh1").bind("click",function(){
			alert("你已参加过该活动！");
			if(homepageId){
				window.location.href="homepage?homepageId="+homepageId+"&page=1";
			}
		});
	}else{
		$(".bomttomh1").bind("click",function(){
			window.location.href='${ad.url}';
		});
	}
})

</script>
  </head>
 <body ontouchstart>
 	<input type="hidden" id="msg" value="${msg}">
     <div class="relative">
        <img src="images/bc.png" style="width:100%">
        <h1 class="demos-title font bt91">${ad.title}</h1>
        <div class="demos-content-padded mtj100">
          <img src="${pageContext.request.contextPath}/${ad.image}" style="width:100%">
        </div>
          <div class="demos-content-padded mtj20">
            <div  class="box">
              <div class="top">${ad.title}</div>
              <div class="mid">${ad.content}</div>
              <div class="miaoshu">${ad.info}</div>
            </div>
          </div>
      </div>   
 	<input type="hidden" id="clickNum" value="${clickNum}">
 	<input type="hidden" id="homepageId" value="${homepageId}">
 	<input type="hidden" id="adUrl" value="${ad.url}">
    <div class="bomtton">
		<h1 class="bomttomh1">点击进入</h1>
	</div>  

  </body>
</html>
