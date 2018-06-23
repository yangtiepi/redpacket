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
			var RedpacketLogDataGrid = $("#RedpacketLogDataGrid");//数据表格
			var RedpacketLogQuery = $("#RedpacketLogQuery");//录入表单

			//初始化组件
			RedpacketLogDataGrid.datagrid({//初始化数据表格
				fit : true,
				border : false,
				url : 'redpacketLog/query',
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
				toolbar : '#RedpacketLog_toolbar',
                onLoadSuccess:function(){
                    $.ajax({
                        type: "POST",
                        async: false,
                        traditional:true,
                        data: RedpacketLogQuery.serializeJson(),
                        cache:false,
                        dataType: "json",
                        url: "/redpacketLog/queryTotalAmount",
                        success: function(data){
                            $("#totalAmount").text(data);
                            return false;
                        }
                    });
				}
			});
			
			//创建cmdRedpacketLog管理所有操作函数
			var cmdRedpacketLog={
				//高级查询
				RedpacketLog_search:function(){
					//把参数封装成一个json对象传到前台
					 RedpacketLogDataGrid.datagrid('load',RedpacketLogQuery.serializeJson());
				}
			};
			//对页面上所有按钮做一次统一的监听
			$("a[data-cmd]").on("click",function(){
				//获取data-cmd属性的值
				var cmd = $(this).data("cmd");//data-key="value"其实就是一个{key:value}类型的数据
				if (cmd) {
					cmdRedpacketLog[cmd]();//执行对应的方法
				}
			});

		});
	</script>
	<!-- 数据表格 -->
	<table id="RedpacketLogDataGrid">
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
	<div id="RedpacketLog_toolbar">
		<div>
			<form method="post" id="RedpacketLogQuery">
				微信名称<input class="easyui-textbox" name="userName" style="width: 150px">
				领取时间<input class="easyui-datetimebox" name="beginTime" style="width: 150px">-
				<input class="easyui-datetimebox" name="endTime" style="width: 150px">
				<a class="easyui-linkbutton" iconCls="icon-search"
					data-cmd="RedpacketLog_search">搜索</a>
				&nbsp;&nbsp;&nbsp;红包总金额:<span style="color: red" id="totalAmount"></span>
			</form>
		</div>
	</div>
</body>
</html>