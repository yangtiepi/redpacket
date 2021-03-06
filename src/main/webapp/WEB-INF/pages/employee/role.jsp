<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
$(function(){
	//在页面加载完毕以后,首先声明组件
	var roleDatagrid = $("#roleDatagrid");
	var roleDialog = $("#roleDialog");
	var role_rightTable = $("#role_rightTable");
	var role_leftTable = $("#role_leftTable");
	var roleForm = $("#roleForm");
	//初始化
	roleDatagrid.datagrid({
		singleSelect:true,
		url:'role/list',
		fit:true,
		fitColumns:true,
		pagination:true,
		rownumbers:true,
		toolbar:'#role_toolbar'
	});
	role_rightTable.datagrid({
		singleSelect:true,
		url:'permission/query',
		fit:true,
		fitColumns:true,
		pagination:true,
		rownumbers:true,
		onDblClickRow:function(rowIndex,rowData){
			// 1、 获取行数据，rowData
			console.debug(rowData);
			// 2、 检查选中数据是否存在
			var selectedPermissionArr = role_leftTable.datagrid("getRows");
			// 是否重复标志
			var repect = false;
			$.each(selectedPermissionArr,function(i,p){
				if(p.id==rowData.id){
					repect = true;
				}
			});
			
			// 3、获取已选权限表格，向表格中添加数据
			if(!repect){
				role_leftTable.datagrid("appendRow",rowData);
			}else{
				 $.messager.alert("温馨提示","您未选中要编辑的信息","info");
			}
		}
	});
	role_leftTable.datagrid({
		singleSelect:true,
		fit:true,
		fitColumns:true,
		onDblClickRow:function(rowIndex,rowData){
			// 1、 获取行数据，rowData
			role_leftTable.datagrid("deleteRow",rowIndex);
		}
	});

	//定义事件对象
	cmdRole = {
		role_create:function(){
			roleForm.form("clear");
			roleDialog.dialog("open");
			role_leftTable.datagrid('loadData',{total:0,rows:[]});
			role_rightTable.datagrid("load");
		},
		role_refresh:function(){
			roleDatagrid.datagrid("reload");
		},
		role_edit:function(){
			roleForm.form("clear");
			//加载右边的所有的权限列表
			role_rightTable.datagrid("load");
			//获取选中行
			var row = roleDatagrid.datagrid("getSelected");
			//判断是否有选中某一行
			if(!row){
				$.messager.alert("提示","您未选中要编辑的信息","info");
				return;
			}
			//删除左边的所有的权限列表
			role_leftTable.datagrid('loadData',{total:0,rows:[]});
			//选中
			//打开用户录入对话框
			roleDialog.dialog("open");
			//load data 读取记录填充到表单中。数据参数可以是一个字符串或一个对象类型，如果是字符串则作为远程URL，否则作为本地记录。  
			//回显
			$.get("/role/findPermissions?id="+row.id,function(data){
				console.debug(data);
				$.each(data,function(index,value){
					role_leftTable.datagrid("appendRow",value);
				});
	 		});
			roleForm.form("load",row);
		},
		role_del:function(){
			//获取选中行
			var row = roleDatagrid.datagrid("getSelected");
			//未选中
			if(!row){
				$.messager.alert("提示","您未选中要删除的信息","info");
				return;
			}
			
			//选中
			$.messager.confirm("确认框","是否确定删除  [<font color='red'>"+row.name +"</font>] 信息吗?",function(b){
				if(b){
					//通过ajex传入需要删除的id
					var id = row.id;
					$.post("/role/delete",{id:id},function(data){
						if("success" == data){
							$.messager.alert("提示","删除成功","info");
							//表格重新加载数据
							roleDatagrid.datagrid("reload");
						}else{
							$.messager.alert("提示","删除失败","error");
						}
					});
				}
			});
		},
		role_save:function(){
			//获得所选择的权限数组
			var pers = role_leftTable.datagrid("getRows");
			//定义需要向后台传递的数组对象
			roleForm.form('submit', {    
			    url:'role/save',    
			    onSubmit: function(param){    
					$.each( pers, function(i,val){
						param["permissions["+i+"].id"] = val.id;
					});
			    },
			    success:function(data){    
			    	//把获得的json形式的字符串转换为json对象
					data = $.parseJSON(data);
					//保存成功关闭对话框
					roleDialog.dialog("close");
					//显示提示信息
					$.messager.alert('温馨提示',"操作状态：" +data.message,'info',function(){
						//在关闭弹窗后的一个回调函数
						//刷新数据
						roleDatagrid.datagrid("reload");
					});
			    } 
			});  
		},
		role_cancel:function(){
			//关闭对话框
			roleDialog.dialog("close");
		}
	};
	
	
	
	$("a[data-cmd]").on("click",function(){
		var cmd = $(this).data("cmd");
		if (cmd) {
			cmdRole[cmd]();
		}
	});
});
</script>
	<table style="width:500px;height:300px" id="roleDatagrid">
		<thead>
			<tr>
				<th align="center" field="sn"    width="100">角色编号</th>
				<th align="center" field="name"    width="100">角色名称</th>
			</tr>
		</thead>
	</table>
	
	<div id="role_toolbar">
		<a data-cmd="role_create" class="easyui-linkbutton" iconCls="icon-add" >添加</a>
		<a data-cmd="role_edit" class="easyui-linkbutton" iconCls="icon-edit" >编辑</a>
		<a data-cmd="role_del" class="easyui-linkbutton" iconCls="icon-remove" >删除</a>
		<a data-cmd="role_refresh" class="easyui-linkbutton" iconCls="icon-reload" >刷新</a>
	</div>
	
	<div id="roleDialog" class="easyui-dialog" style="width:1000px;height:480px;"
	data-options="modal:true,iconCls:'icon-edit',title:'角色编辑',closed:true,buttons:'#role_bs'" >
		<form id="roleForm" method="post">
			<div class="easyui-layout" style="width:983px;height:400px;">
				 <div data-options="region:'north',split:true" style="height:40px;" align="left">
					<input id="resourceId" type="hidden" name="id" />
				 	<div style="margin-top: 5px;margin-left: 5px" >
				 	角色编号:
				 	<input  name="sn" class="easyui-textbox" data-options="required:true" style="width: 150px;" />&nbsp;&nbsp;&nbsp;
				 	角色名称:
				 	<input  name="name" class="easyui-textbox" data-options="required:true" style="width: 150px;" />
					</div>
				 </div> 
				 <div data-options="region:'west',title:'已选权限',split:true" style="width:200px;">
				 	<table style="width:200px;height:200px" id="role_leftTable">
						<thead>
							<tr>
								<th field="name"    width="10">权限名称</th>
								<th field="type"    width="10">权限类型</th>
							</tr>
						</thead>
					</table>
				 </div>   
	    		 <div data-options="region:'center',title:'所有权限 '" style="padding:5px">
	    		 	<table style="width:480px;height:200px" id="role_rightTable">
						<thead>
							<tr>
								<th field="name"    width="10">权限名称</th>
								<th field="type"    width="10">权限类型</th>
								<th field="resource"    width="50">权限资源</th>
							</tr>
						</thead>
					</table>
	    		 </div>
			</div>
    	</form>
	</div>
	
	<div id="role_bs">
		<a class="easyui-linkbutton"  data-cmd="role_save" data-options="iconCls:'icon-add'">保存</a>
		<a class="easyui-linkbutton" data-cmd="role_cancel" data-options="iconCls:'icon-cancel'">取消</a>
	</div>
</body>
</html>