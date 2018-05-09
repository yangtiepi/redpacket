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
			var adClickLogDataGrid = $("#adClickLogDataGrid");//数据表格
			var adClickLogDialog = $("#adClickLogDialog");//录入窗口
			var adClickLogForm = $("#adClickLogForm");//录入表单
			var adClickLogQuery = $("#adClickLogQuery");//搜索表单

			//初始化组件
			adClickLogDataGrid.datagrid({//初始化数据表格
				fit : true,
				border : false,
				url : 'adClickLog/queryGroupAd',
				singleSelect : true,
				fitColumns : true,
				striped : true,
				rownumbers : true,
				pagination : true,
				pageSize : 10,
				pageList : [ 5, 10, 20, 50 ],
				toolbar : '#adClickLog_toolbar'
			});
			
			//创建cmdAdClickLog管理所有操作函数
			var cmdAdClickLog={
				//高级查询
				adClickLog_search:function(){
					//把参数封装成一个json对象传到前台
					 adClickLogDataGrid.datagrid('load',adClickLogQuery.serializeJson()); 
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
		function showAdClickLog(adId) {
			var adClickLogDataGrid = $("#adClickLogDataGrid");
			var adClickLogDialog = $("#adClickLogDialog");
			var adClickLogDetailDataGrid = $("#adClickLogDetailDataGrid");
			if (!params['beginTime']) {
				params['beginTime'] = '';
			}
			if (!params['endTime']) {
				params['endTime'] = '';
			}
			// 获取选中行信息
			var rowData = adClickLogDataGrid.datagrid("getSelected");
			//初始表格
			adClickLogDetailDataGrid.datagrid({
				fit : true,
				border : false,
				url : 'adClickLog/query',
				queryParams : {
					adId : adId,
					beginTime : params["startTime"],
					endTime : params["endTime"]
				},
				fitColumns : true,
				striped : true,
				nowrap : false,
				rownumbers : true,
				pagination : true,
				pageSize : 10,
				pageList : [ 5, 10, 20, 50 ],
				toolbar : '#redpacketLog_toolbar'
			});
			adClickLogDialog.dialog("open");
		}
	</script>
	<!-- 数据表格 -->
	<table id="adClickLogDataGrid">
		<!-- 定义表格的表头  -->
		<thead>
			<tr>
				<!-- field：指定需要显示列对应JSON数据属性 -->
				<th field="adTitle" width="10" align="center">广告主标题</th>
				<th field="clickNum" width="10" align="center">点击次数</th>
				<th field="adId" width="10" align="center"
					data-options="formatter:formatAdClickLogDetail">详情</th>
			</tr>
		</thead>
	</table>
	<!-- 工具条 -->
	<div id="adClickLog_toolbar">
		<div>
			<form method="post" id="adClickLogQuery">
				广告主标题:<input class="easyui-textbox" name="adTitle" style="width: 150px">
				时间:<input class="easyui-datetimebox" name="beginTime" style="width: 150px">
				-<input class="easyui-datetimebox" name="endTime" style="width: 150px">
				<a class="easyui-linkbutton" iconCls="icon-search"
					data-cmd="adClickLog_search">搜索</a>
			</form>
		</div>
	</div>
	<!-- 点击明细窗口 -->
	<div id="adClickLogDialog" class="easyui-dialog"
		style="width: 800px; height: 400px;"
		data-options="title:'点击明细',modal:true,iconCls:'icon-edit',closed:true">
		<!-- 数据表格 -->
		<table id="adClickLogDetailDataGrid">
			<!-- 定义表格的表头  -->
			<thead>
				<tr>
					<th field="openid" width="10" align="center">用户id</th>
					<th field="wechat" width="10" align="center">微信名</th>
					<th field="clickTime" width="20" align="center"
						data-options="formatter:formatDateTime">点击时间</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>