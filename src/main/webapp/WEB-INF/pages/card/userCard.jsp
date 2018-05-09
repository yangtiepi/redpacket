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
	var userCardDataGrid = $("#userCardDataGrid");//数据表格
	var userCardDialog = $("#userCardDialog");//录入窗口
	var userCardForm = $("#userCardForm");//录入表单
	   
	//初始化组件
	userCardDataGrid.datagrid({//初始化数据表格
		fit:true,
		border:false,
		url:'userCard/query',
		singleSelect:true,
		fitColumns:true,
		striped:true,
		rownumbers:true,
		pagination:true,
		pageSize:10,
		pageList:[5,10,20,50],
		toolbar:'#userCard_toolbar'
	});
	
	
	
	//创建cmdType管理所有操作函数
	var cmdUserCard={
		//新增
		userCard_create:function(){
			// 1.清空对话框数据
			userCardForm.form("clear");
			// 2.打开添加对话框
			userCardDialog.dialog("open");
		},

		//保存
		userCard_save:function(){
			// 把表单的参数，通过AJAX方式提交到后台
			userCardForm.form("submit",{
				url:"/userCard/save",
				success:function(data){ 
					data = $.parseJSON(data);//转为json
					if(data.success){
						userCardDialog.dialog("close");
						$.messager.alert("温馨提示",data.message,"info",function(){
							// 刷新表格
							userCardDataGrid.datagrid("reload");
						});
					}else{
						$.messager.alert("温馨提示",data.message,"info");
					}
				}
			});
		},
		//取消
		userCard_cancel:function(){
			userCardDialog.dialog("close");
		},

		//删除
		userCard_del:function(){
			// 1.获取选中行信息
			var rowData = userCardDataGrid.datagrid("getSelected");
			console.debug(rowData);
			// 2.判断
			if(!rowData){
				$.messager.alert("温馨提示","你没有选中任何数据","info");
				return;
			}
			//提示用户是否删除
			$.messager.confirm("温馨提示","是否确认删除【"+rowData.name+"】?",function(yes){
				if(yes){
					// 获取数据的唯一标示
					var id = rowData.id;
					// 发送AJAX请求，修改ID对应数据状态
					$.get("/userCard/delete",{id:id},function(data){
						if(data.success){
							$.messager.alert("温馨提示",data.message,"info",function(){
								userCardDataGrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.message,"error");
						}
					},"json");
				}
			});
			
		}, 

		//刷新
		userCard_refresh:function(){
			userCardDataGrid.datagrid("reload");
		}

	};
	//对页面上所有按钮做一次统一的监听
	$("a[data-cmd]").on("click",function(){
		//获取data-cmd属性的值
		var cmd = $(this).data("cmd");
		console.debug(cmd);
		if (cmd) {
			cmdUserCard[cmd]();//执行对应的方法
		}
	});
});
</script>
	<!-- 数据表格 -->
	<table id="userCardDataGrid" >
		<!-- 定义表格的表头  -->
		<thead>
			<tr>
				<!-- field：指定需要显示列对应JSON数据属性 -->
				<th field="name" width="10" align="center">名称</th>
			</tr>
		</thead>
	</table>
	<!-- 工具条 -->
	<div id="userCard_toolbar">
	 	<div>
		 	<form method="post" id="userCard_searchForm">
				<a class="easyui-linkbutton" iconCls="icon-add" data-cmd="userCard_create">添加</a>
				<a class="easyui-linkbutton" iconCls="icon-remove" data-cmd="userCard_del">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload"  data-cmd="userCard_refresh">刷新</a>
		 	</form>
		 </div> 
	</div>


	<!-- 录入窗口 -->
	<div id="userCardDialog" class="easyui-dialog" style="width: 300px;height: 130px;"
	data-options="title:'编辑文章分类',modal:true,iconCls:'icon-edit',buttons:'#userCard_bs',closed:true">
			<form id="userCardForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id"></input>
			<div style="margin-top: 20px;margin-left: 35px" >
			 			分类名字：
			 			<input  name="name"  class="easyui-textbox" data-options="required:true" style="width: 150px">
			</div>		
			</form>
		</div>
	<!-- 录入窗口按钮 -->
	<div id="userCard_bs">
		<a class="easyui-linkbutton" iconCls="icon-save" data-cmd="userCard_save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="userCard_cancel">取消</a>
	</div>

</body>
</html>