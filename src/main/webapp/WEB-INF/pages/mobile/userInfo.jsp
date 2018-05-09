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
<script
	src="${pageContext.request.contextPath}/mobile/lib/jquery-2.1.4.js"></script>
<script src="${pageContext.request.contextPath}/mobile/lib/fastclick.js"></script>
<script
	src="${pageContext.request.contextPath}/mobile/js/jquery-weui.js"></script>
<script type="text/javascript">
	$(function() {
		var msg = $("#msg").val();
		if(msg){
			alert(msg);
			return;
		}
		
	

	})
		function withdraw(){
			$.ajax({
			    url:'withdraw',
			    type:'get',
			    success:function(data){
			        if(data.success){
			        	alert("提现成功！");
			        }else{
			        	alert(data.msg);
			        }
			    },
			    error:function(xhr,textStatus){
			       alert("提现失败！");
			    }
			})
		}
</script>
</head>
<body ontouchstart>
	<input type="hidden" id="msg" value="${msg}">
	<div >
		<img src="images/bc.png" style="width: 100%" onclick="withdraw()">
		<h1 class="demos-title font">账户余额</h1>

		<div class="demos-content-padded mtj118">
			<div class="box mt10">
				<div class="center mt40">
					<img src="images/money.png" style="width: 120px">
				</div>
				<div class="money center mb10">￥${user.amount}</div>
				<a href="" onclick="withdraw()" class="btnscan">转入微信余额</a>
			</div>

		</div>
	</div>

</body>
</html>
