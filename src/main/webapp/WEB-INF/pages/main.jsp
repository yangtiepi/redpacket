<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信红包首页</title>

<!-- 引入页面公共资源:easyui主题/皮肤;easyui图标样式;easyui依赖jquery库(jquery.min.js);easyui核心文件;easyui汉化文件 -->
<%@include file="/WEB-INF/pages/common.jsp"%>

<script type="text/javascript">
	$(function() {
		//主面板
		var mainTabs = $("#mainTabs");
		console.log($("#menuTree"));
		$("#menuTree").tree({
			url : 'menu',
			//注册点击事件,node当前被点击的节点对象
			onClick : function(node) {
				//获取节点的文本作为标题;
				var title = node.text;
				//获取需要打开页面地址;
				var url = node.url;
				if (url != null) {
					//判断选项卡面板是否存在
					//exists which 表明指定的面板是否存在，'which'参数可以是选项卡面板的标题或索引。 
					if (mainTabs.tabs("exists", title)) {
						//select which 选择一个选项卡面板，'which'参数可以是选项卡面板的标题或者索引。
						mainTabs.tabs("select", title);
						return;
					}
					//添加功能面板
					mainTabs.tabs("add", {
						title : title,
						iconCls : 'icon-filter',
						href : url,
						closable : true
					});
				}
			}
		});
	});
</script>

<style>
.list_case_left {
	position: absolute;
	left: 10%;
	font-size: 130px;
	font-weight: 200;
	color: #fff;
	text-shadow: 1px 0px #5E5E5E, 1px 2px #5E5E5E, 3px 1px #5E5E5E, 2px 3px
		#5E5E5E, 4px 2px #5E5E5E, 4px 4px #5E5E5E, 5px 3px #5E5E5E, 5px 5px
		#5E5E5E, 7px 4px #5E5E5E, 6px 6px #5E5E5E, 8px 5px #5E5E5E, 7px 7px
		#5E5E5E, 9px 6px #5E5E5E, 9px 8px #5E5E5E, 11px 7px #5E5E5E, 10px 9px
		#5E5E5E, 12px 8px #5E5E5E, 11px 10px #5E5E5E, 13px 9px #5E5E5E, 12px
		11px #5E5E5E, 15px 10px #5E5E5E, 13px 12px #5E5E5E, 16px 11px #5E5E5E,
		15px 13px #5E5E5E, 17px 12px #5E5E5E
}
</style>
</head>

<!-- body变成easyui的layout组件 -->
<body class="easyui-layout">

	<!-- 自定义换肤工具 -开始........................................................................-->
	<!--  <div id="Themes-tools">
        <span style="color:#999">换肤:</span>
		<span style="color:#999">
			<select id="cb-theme" class="combobox-f combo-f" onchange="onChangeTheme($('#cb-theme option:selected').val())" data-options="border:false,editable:false,panelHeight:'auto'">
	有不兼容问题			<option value="default" selected>Default</option>
	有不兼容问题			<option value="gray" >Gray</option>
	有不兼容问题			<option value="black">Black</option>
	有不兼容问题			<option value="bootstrap">Bootstrap</option>
	有不兼容问题			<option value="metro">Metro</option>
				<option value="metro-blue">Metro Blue</option>
				<option value="metro-gray" selected>Metro Gray</option>
				<option value="metro-green">Metro Green</option>
				<option value="metro-orange">Metro Orange</option>
				<option value="metro-red">Metro Red</option>
				<option value="ui-cupertino">Ui-Cupertino</option>
				<option value="ui-dark-hive">Ui-Dark-Hive</option>
				<option value="ui-pepper-grinder">Ui-Pepper-Grinder</option>
				<option value="ui-sunny">Ui-Sunny</option>
			</select>
		</span>
		<span style="color:#999">&emsp;</span>
    </div> -->
	<!-- 自定义换肤工具  -结束..................................................................-->

	<!-- 主面板区域 （中间）-->
	<div data-options="region:'center'">
		<div id="mainTabs" class="easyui-tabs"
			data-options="fit:true,border:false,tools:'#Themes-tools'">
			<div align="center"
				data-options="title:'欢迎您使用本系统',iconCls:'icon-filter'">
				<!-- 				<h1 style="text-align: center;margin-top: 180px;">微信红包管理系统正在加载中<br/>请稍后.... </h1> -->
				<!-- 				<img alt="" src="${pageContext.request.contextPath}/images/wle.jpg" width="100%"> -->
				<!-- 				<div style="padding: 30px"></div> -->
				<!-- 				<h1 style="text-align: center;margin-top: 180px;"><font size="200" style="font-family:  Segoe Script">我们一起来寻宝</font></h1> -->
								<h1 style="text-align: center;margin-top: 180px;"><font size="200" class="list_case_left">常德尚弘云木业有限公司</font></h1>
				<!-- 				<img width="100%" height="100%" src="images/homepage.png"> -->
			</div>
		</div>
	</div>
	<!--
	 	网页的系统菜单（西）
	 	split:true 可以拖动大小	 
	-->
	<div
		data-options="region:'west',title:'系统菜单',iconCls:'icon-filter',split:true"
		style="width: 150px;">
		<ul id="menuTree"></ul>
	</div>


	<!-- 网页标题头 (北) 背景颜色调整为和logo背景色相同-->
	<!-- <div data-options="region:'north',border:false" style="height: 80px;background-color: #DDDDDD">
		<table cellpadding="1" cellspacing="1">
			<tr>
				添加logo图标
				<td>&nbsp;<img alt="" src="${pageContext.request.contextPath}/images/32.gif" style="height: 70px;width: 80px"> </td>
				<td><h1>&nbsp;微信红包管理系统（V2.0）</h1> </td>
			</tr>
		</table>
	</div> -->

	<div data-options="region:'north',border:false"
		style="height: 50px; background-color: #DDDDDD">
		<table cellpadding="1" cellspacing="1">
			<tr>
				<td>&nbsp;<img alt=""
					src="${pageContext.request.contextPath}/images/32.gif"
					style="height: 40px; width: 50px">
				</td>
				<td><font size="5">&nbsp;微信红包管理系统</font></td>
				<td width="500px" style="padding-top: 20px; padding-right: 30px">
					&nbsp;<font style="top: 20px; left: 30px">${employee_in_session.realName}</font>
					<a href="logout"><font color="red">注销</font></a>
				</td>
			</tr>
		</table>
	</div>

	<!-- 网页版权 (南) -->
	<div data-options="region:'south',border:false"
		style="height: auto; text-align: center;">
		尚宏云木业</br> © 2015-2025 版权所有
	</div>
</body>

</html>