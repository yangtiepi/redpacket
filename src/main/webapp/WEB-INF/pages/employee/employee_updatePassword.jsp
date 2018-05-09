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
	var employeeNameSearchbox = $("#employeeNameSearchbox");//搜索框
	var roleCombobox = $("#roleCombobox");//角色下拉框
	var employeeDataGrid = $("#employeeDataGrid");//数据表格
	var employee_searchForm = $("#employee_searchForm");//参数表单
	var employeeDialog = $("#employeeDialog");//录入窗口
	var employeeForm = $("#employeeForm");//录入表单
	var emp_password = $("#emp_password");//密码框

   //数据表格
    employeeDataGrid.datagrid({
    	fit:true,
		border:false,
		url:'employee/query',
		singleSelect:true,
		fitColumns:true,
		striped:true,
		rownumbers:true,
		pagination:true,
		pageSize:5,
		pageList:[5,10,20,50],
		toolbar:'#employee_toolbar'
	});
	
	//创建cmdType管理所有操作函数
	var cmdEmployee={
		//修改密码
		employee_updatePassword:function(){
			// 获取选中行信息
			var rowData = employeeDataGrid.datagrid("getSelected");
			console.debug(rowData);
			// 判断
			if(!rowData){
				$.messager.alert("温馨提示","你没有选中任何数据","info");
				return;
			}
			//清空对话框数据
			employeeForm.form("clear");
			//设置id
			var data;
			data['id']=rowData.id;
			// 把数据加载到对话框中,回显数据
			employeeForm.form("load",rowData);
			//打开添加对话框
			employeeDialog.dialog("open");
		},
		//刷新
		employee_refresh:function(){
			employeeDataGrid.datagrid("reload");
		},	
		//保存
		employee_save:function(){
			var password = $("#password").val();
			//判断密码
			var password = $("#password").val();
			var comfirmPassword = $("#comfirmPassword").val();
			if (password != comfirmPassword) {
				$.messager.alert("温馨提示","两次密码不一致！","info");
				//清空对话框数据
				employeeForm.form("clear");
				return;
			}
			// 把表单的参数，通过AJAX方式提交到后台
			employeeForm.form("submit",{
				url:"/employee/updatePassword",
				success:function(data){//data只是json字符串，需要手动转 
					data = $.parseJSON(data);
					if(data.success){
						employeeDialog.dialog("close");
						$.messager.alert("温馨提示",data.message,"info",function(){
							// 刷新表格
							employeeDataGrid.datagrid("load");
						});
					}else{
						$.messager.alert("温馨提示",data.message,"info",function(){
							$.messager.alert("温馨提示",data.message,"warning");
						});
					}
				}
			});
		},
		
		//取消
		employee_cancel:function(){
			employeeDialog.dialog("close");
		}
	}
	
	//对页面上所有按钮做一次统一的监听
	$("a[data-cmd]").on("click",function(){
		//获取data-cmd属性的值
		var cmd = $(this).data("cmd");//data-key="value"其实就是一个{key:value}类型的数据
		if (cmd) {
			cmdEmployee[cmd]();//执行对应的方法
		}
	});
});
</script>
	<table id="employeeDataGrid" style="width: 600px;height: 300px;">
		<!--
			定义表格的表头 
		 -->
		<thead>
			<tr>
				<!-- field：指定需要显示列对应JSON数据属性 -->
				<th align="center" field="id" 		width="10">ID</th>
				<th align="center" field="username"    width="20">账户账号</th>
				<th align="center" field="realName"   width="20">姓名</th>
				<th align="center" field="status"  width="10" data-options="formatter:formatEmployeeStatus">状态</th>
			</tr>
		</thead>
	</table>
	
	 <div id="employee_toolbar">
	 	<div>
		 	<form method="post" id="employee_searchForm">
			<a class="easyui-linkbutton" iconCls="icon-edit"  data-cmd="employee_updatePassword">修改密码</a>
			<a class="easyui-linkbutton" iconCls="icon-reload"  data-cmd="employee_refresh">刷新</a>
		 	</form>
		 </div> 
	</div>
	
	<!-- 新增或编辑表格 -->
	<div id="employeeDialog" class="easyui-dialog" style="width: 280px;height: 250px;padding: 20px;"
		data-options="modal:true,title:'修改密码',iconCls:'icon-edit',buttons:'#employee_bs',closed:true" >
		<form id="employeeForm"  method="post">
			<input type="hidden" name="id">
			 <div style="margin-top: 10px;">
			 	原密码&nbsp;：<input type="text" name="oldPassword"  class="easyui-textbox" data-options="required:true">
			 </div>
			 <div style="margin-top: 10px;">
			 	新密码&nbsp;：<input type="text" id="password"  name="password"  class="easyui-textbox" data-options="required:true">
			 </div>
			 <div style="margin-top: 10px;">
			 	确认密码：<input type="text" id="comfirmPassword" class="easyui-textbox" data-options="required:true">
			 </div>
		</form>
	</div>
	<div id="employee_bs">
		<a class="easyui-linkbutton" iconCls="icon-save" data-cmd="employee_save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel"  data-cmd="employee_cancel">取消</a>
	</div>
	
</body>
</html>