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
	var userSearchbox = $("#userSearchbox");//搜索框
	var userDataGrid = $("#userDataGrid");//数据表格
	var user_searchForm = $("#user_searchForm");//参数表单
	//搜索框
	userSearchbox.searchbox({
		prompt:'关键字',
		searcher:function(){ 
			var paramObj = {};
			var paramArr = user_searchForm.serializeArray();
			 $.each(paramArr,function(i,data){
				 paramObj[data.name] = data.value;
			 }); 
			 userDataGrid.datagrid("load",paramObj);
		}
	});
   //数据表格
    userDataGrid.datagrid({
    	fit:true,
		border:false,
		url:'user/query',
		singleSelect:true,
		fitColumns:true,
		striped:true,
		rownumbers:true,
		pagination:true,
		pageSize:20,
		pageList:[5,10,20,50],
		toolbar:'#user_toolbar'
	});
	
	//创建cmdType管理所有操作函数
	var cmdUser={
	}
	
	//对页面上所有按钮做一次统一的监听
	$("a[data-cmd]").on("click",function(){
		//获取data-cmd属性的值
		var cmd = $(this).data("cmd");//data-key="value"其实就是一个{key:value}类型的数据
		if (cmd) {
			console.debug(cmd);
			cmdUser[cmd]();//执行对应的方法
		}
	});
});
</script>
	<table id="userDataGrid" style="width: 600px;height: 300px;">
		<!--
			定义表格的表头 
		 -->
		<thead>
			<tr>
				<!-- field：指定需要显示列对应JSON数据属性 -->
				<th field="openid"  width="10" align="center" >用户ID</th>
				<th field="wechat"   width="10" align="center">微信名</th>
			</tr>
		</thead>
	</table>
	
	 <div id="user_toolbar">
	 	<div>
		 	<form method="post" id="user_searchForm">
				<%--<input id="userSearchbox" name="userName" style="width: 100px" ></input>--%>
		 	</form>
		 </div> 
	</div>
	
</body>
</html>