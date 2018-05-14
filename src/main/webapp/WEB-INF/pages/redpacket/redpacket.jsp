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
	var redpacketDataGrid = $("#redpacketDataGrid");//数据表格
	var redpacketDialog = $("#redpacketDialog");//录入窗口
	var redpacketForm = $("#redpacketForm");//录入表单
	var cardsCombobox = $("#cardsCombobox");//卡集
	   
	//初始化组件
	redpacketDataGrid.datagrid({//初始化数据表格
		fit:true,
		nowrap:false,
		border:false,
		url:'redpacket/query',
		singleSelect:true,
		fitColumns:true,
		striped:true,
		rownumbers:true,
		pagination:true,
		pageSize:10,
		pageList:[5,10,20,50],
		toolbar:'#redpacket_toolbar'
	});
	
	cardsCombobox.combobox({
	   url:'cards/list',
       valueField:'id',
       textField:'name',
       prompt:'卡集',
    });
	
	
	
	//创建cmdType管理所有操作函数
	var cmdRedpacket={
		//新增
		redpacket_create:function(){
			// 1.清空对话框数据
			redpacketForm.form("clear");
			// 2.打开添加对话框
			redpacketDialog.dialog("open");
		},
		//编辑
		redpacket_edit:function(){
			// 1.获取选中行信息
			var rowData = redpacketDataGrid.datagrid("getSelected");
			// 2.判断
			if(!rowData){
				$.messager.alert("温馨提示","你没有选中任何数据","info");
				return;
			}
			// 3.清空对话框数据
			redpacketForm.form("clear");
			// 4.打开添加对话框
			redpacketDialog.dialog("open");
			// 5.把数据加载到对话框中,回显数据
			redpacketForm.form("load",rowData);
		},

		//保存
		redpacket_save:function(){
			// 把表单的参数，通过AJAX方式提交到后台
			redpacketForm.form("submit",{
				url:"redpacket/save",
				success:function(data){ 
					data = $.parseJSON(data);//转为json
					if(data.success){
						redpacketDialog.dialog("close");
						$.messager.alert("温馨提示",data.message,"info",function(){
							// 刷新表格
							redpacketDataGrid.datagrid("reload");
						});
					}else{
						$.messager.alert("温馨提示",data.message,"info");
					}
				}
			});
		},
		//取消
		redpacket_cancel:function(){
			redpacketDialog.dialog("close");
		},

		//删除
		redpacket_del:function(){
			// 1.获取选中行信息
			var rowData = redpacketDataGrid.datagrid("getSelected");
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
					$.get("/redpacket/delete",{id:id},function(data){
						if(data.success){
							$.messager.alert("温馨提示",data.message,"info",function(){
								redpacketDataGrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.message,"error");
						}
					},"json");
				}
			});
			
		}, 

		//刷新
		redpacket_refresh:function(){
			redpacketDataGrid.datagrid("reload");
		}

	};
	//对页面上所有按钮做一次统一的监听
	$("a[data-cmd]").on("click",function(){
		//获取data-cmd属性的值
		var cmd = $(this).data("cmd");
		console.debug(cmd);
		if (cmd) {
			cmdRedpacket[cmd]();//执行对应的方法
		}
	});
});
function disableRedpacket(id){
	$.messager.confirm("温馨提示","确认停用吗?",function(yes){
		if(yes){
			// 发送AJAX请求，修改ID对应数据状态
			$.get("redpacket/disable",{id:id},function(data){
				console.log(data);
				if(data.success){
					$.messager.alert("温馨提示",data.message,"info",function(){
						$("#redpacketDataGrid").datagrid("reload");
					});
				}
			},"json");
		}
	});
}
function enableRedpacket(id){
	$.messager.confirm("温馨提示","确认启用吗?",function(yes){
		if(yes){
			// 发送AJAX请求，修改ID对应数据状态
			$.get("redpacket/enable",{id:id},function(data){
				console.log(data);
				if(data.success){
					$.messager.alert("温馨提示",data.message,"info",function(){
						$("#redpacketDataGrid").datagrid("reload");
					});
				}
			},"json");
		}
	});
}
</script>
	<!-- 数据表格 -->
	<table id="redpacketDataGrid" >
		<!-- 定义表格的表头  -->
		<thead>
			<tr>
				<!-- field：指定需要显示列对应JSON数据属性 -->
				<th field="name" width="20" align="center">名称</th>
				<th field="info" width="50" align="center">说明</th>
				<th field="ratio" width="20" align="center" >出现概率（%）</th>
				<th field="qrcode" width="10" align="center" data-options="formatter:formatImg">二维码</th>
				<th field="status" width="10" align="center" data-options="formatter:formatRedpacketStatus">状态</th>
			</tr>
		</thead>
	</table>
	<!-- 工具条 -->
	<div id="redpacket_toolbar">
	 	<div>
		 	<form method="post" id="redpacket_searchForm">
				<a class="easyui-linkbutton" iconCls="icon-add" data-cmd="redpacket_create">添加</a>
				<a class="easyui-linkbutton" iconCls="icon-edit" data-cmd="redpacket_edit">编辑</a>
				<a class="easyui-linkbutton" iconCls="icon-remove" data-cmd="redpacket_del">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload"  data-cmd="redpacket_refresh">刷新</a>
		 	</form>
		 </div> 
	</div>


	<!-- 录入窗口 -->
	<div id="redpacketDialog" class="easyui-dialog" style="width: 400px;height: 340px;"
	data-options="title:'编辑红包',modal:true,iconCls:'icon-edit',buttons:'#redpacket_bs',closed:true">
			<form id="redpacketForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id"></input>
				<div style="margin-top: 20px;margin-left: 35px" >
	 				红包名字：
	 				<input  name="name"  class="easyui-textbox" data-options="required:true" style="width: 250px">
				</div>		
				<div style="margin-top: 20px;margin-left: 35px" >
	 				最小金额：
	 				<input  name="min"  class="easyui-numberbox" data-options="required:true,min:0.01,precision:2" style="width: 250px;">
				</div>	
				<div style="margin-top: 20px;margin-left: 35px" >
	 				最大金额：
	 				<input  name="max"  class="easyui-numberbox" data-options="required:true,min:0.01,precision:2" style="width: 250px;">
				</div>
				<div style="margin-top: 20px;margin-left: 35px" >
					出现概率：
					<input  name="ratio" id="ratio"  class="easyui-numberbox" data-options="required:true,max:100,min:0.01,precision:2" style="width: 250px">
				</div>
				<div style="margin-top: 20px;margin-left: 35px" >
	 				红包说明：
	 				<input  name="info"  class="easyui-textbox" data-options="required:true,multiline:true" style="width: 250px;height:50px">
				</div>	
			</form>
		</div>
	<!-- 录入窗口按钮 -->
	<div id="redpacket_bs">
		<a class="easyui-linkbutton" iconCls="icon-save" data-cmd="redpacket_save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="redpacket_cancel">取消</a>
	</div>

</body>
</html>