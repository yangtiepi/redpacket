<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<script type="text/javascript">
$(function(){
	//缓存要用到的组件
	var cardsCompleteDataGrid = $("#cardsCompleteDataGrid");//数据表格
	var cardsCompleteDialog = $("#cardsCompleteDialog");//录入窗口
	var cardsCompleteForm = $("#cardsCompleteForm");//录入表单
	var cardsCompleteQuery = $("#cardsCompleteQuery");//录入表单
	   
	//初始化组件
	cardsCompleteDataGrid.datagrid({//初始化数据表格
		fit:true,
		border:false,
		url:'cardsComplete/query',
		singleSelect:true,
		fitColumns:true,
		striped:true,
		rownumbers:true,
		pagination:true,
		pageSize:10,
		pageList:[5,10,20,50],
		toolbar:'#cardsComplete_toolbar'
	});
	
	//创建cmdAdClickLog管理所有操作函数
	var cmdAdClickLog={
		//高级查询
		cardsComplete_search:function(){
			//把参数封装成一个json对象传到前台
			 cardsCompleteDataGrid.datagrid('load',cardsCompleteQuery.serializeJson()); 
		}
	};
	//对页面上所有按钮做一次统一的监听
	$("a[data-cmd]").on("click",function(){
		//获取data-cmd属性的值
		var cmd = $(this).data("cmd");//data-key="value"其实就是一个{key:value}类型的数据
		if (cmd) {
			cmdAdClickLog[cmd]();//执行对应的方法
		}
	});
	
});
	function receiveCards(id){
		//提示用户是否删除
		$.messager.confirm("温馨提示","确认用户领取吗?",function(yes){
			if(yes){
				// 发送AJAX请求，修改ID对应数据状态
				$.get("cardsComplete/receice",{id:id},function(data){
					console.log(data);
					if(data.success){
						$.messager.alert("温馨提示",data.message,"info",function(){
							$("#cardsCompleteDataGrid").datagrid("reload");
						});
					}
				},"json");
			}
		});
	}
</script>
	<!-- 数据表格 -->
	<table id="cardsCompleteDataGrid" >
		<!-- 定义表格的表头  -->
		<thead>
			<tr>
				<!-- field：指定需要显示列对应JSON数据属性 -->
				<th field="wechat" width="10" align="center">用户名称</th>
				<th field="cards" width="10" align="center" data-options="formatter:formatName">主卡</th>
				<th field="code" width="10" align="center">验证码</th>
				<th field="completeTime" width="10" align="center" data-options="formatter:formatDateTime">时间</th>
				<th field="status" width="10" align="center" data-options="formatter:formatCardsCompleteStatus">状态</th>
			</tr>
		</thead>
	</table>
	<!-- 工具条 -->
	<div id="cardsComplete_toolbar">
		<div>
			<form method="post" id="cardsCompleteQuery">
				兑换码:<input class="easyui-textbox" name="code" style="width: 150px">
				<a class="easyui-linkbutton" iconCls="icon-search"
					data-cmd="cardsComplete_search">搜索</a>
			</form>
		</div>
	</div>	


</body>
</html>