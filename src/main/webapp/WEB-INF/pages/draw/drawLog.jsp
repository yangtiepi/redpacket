<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<script type="text/javascript">
		var params = {};//查询条件
		$(function() {
			//缓存要用到的组件
			var DrawLogDataGrid = $("#DrawLogDataGrid");//数据表格
			var DrawLogQuery = $("#DrawLogQuery");//录入表单

			//初始化组件
			DrawLogDataGrid.datagrid({//初始化数据表格
				fit : true,
				border : false,
				url : 'drawLog/query',
                queryParams : {
                    type : 1
                },
				singleSelect : true,
				fitColumns : true,
				striped : true,
				rownumbers : true,
				pagination : true,
				pageSize : 10,
				pageList : [ 5, 10, 20, 50 ],
				toolbar : '#DrawLog_toolbar'
			});
			
			//创建cmdDrawLog管理所有操作函数
			var cmdDrawLog={
				//高级查询
				DrawLog_search:function(){
					//把参数封装成一个json对象传到前台
					 DrawLogDataGrid.datagrid('load',DrawLogQuery.serializeJson());
				}
			};
			//对页面上所有按钮做一次统一的监听
			$("a[data-cmd]").on("click",function(){
				//获取data-cmd属性的值
				var cmd = $(this).data("cmd");//data-key="value"其实就是一个{key:value}类型的数据
				if (cmd) {
					cmdDrawLog[cmd]();//执行对应的方法
				}
			});

		});
	</script>
	<!-- 数据表格 -->
	<table id="DrawLogDataGrid">
		<!-- 定义表格的表头  -->
		<thead>
			<tr>
				<!-- field：指定需要显示列对应JSON数据属性 -->
				<th field="userName" width="10" align="center">微信名称</th>
				<th field="openid" width="10" align="center">用户ID</th>
				<th field="amount" width="10" align="center">红包金额</th>
				<th field="receiveTime" width="10" align="center"
					data-options="formatter:formatDateTime">领取时间</th>
			</tr>
		</thead>
	</table>
	<!-- 工具条 -->
	<div id="DrawLog_toolbar">
		<div>
			<form method="post" id="DrawLogQuery">
				微信名称<input class="easyui-textbox" name="userName" style="width: 150px">
				领取时间<input class="easyui-datetimebox" name="beginTime" style="width: 150px">-
				<input class="easyui-datetimebox" name="endTime" style="width: 150px">
				<a class="easyui-linkbutton" iconCls="icon-search"
					data-cmd="DrawLog_search">搜索</a>
			</form>
		</div>
	</div>
</body>
</html>