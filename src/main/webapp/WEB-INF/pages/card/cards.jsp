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
	var cardsDataGrid = $("#cardsDataGrid");//数据表格
	var cardsDialog = $("#cardsDialog");//录入窗口
	var cardsForm = $("#cardsForm");//录入表单
	   
	//初始化组件
	cardsDataGrid.datagrid({//初始化数据表格
		fit:true,
		border:false,
		url:'cards/query',
		singleSelect:true,
		fitColumns:true,
		striped:true,
		rownumbers:true,
		pagination:true,
		pageSize:10,
		pageList:[5,10,20,50],
		toolbar:'#cards_toolbar'
	});
	
	
	
	//创建cmdType管理所有操作函数
	var cmdCards={
		//新增
		cards_create:function(){
			// 1.清空对话框数据
			cardsForm.form("clear");
			// 2.打开添加对话框
			cardsDialog.dialog("open");
		},
		//编辑
		cards_edit:function(){
			// 1.获取选中行信息
			var rowData = cardsDataGrid.datagrid("getSelected");
			// 2.判断
			if(!rowData){
				$.messager.alert("温馨提示","你没有选中任何数据","info");
				return;
			}
			// 3.清空对话框数据
			cardsForm.form("clear");
			// 4.打开添加对话框
			cardsDialog.dialog("open");
			// 5.把数据加载到对话框中,回显数据
			cardsForm.form("load",rowData);
		},

		//保存
		cards_save:function(){
			// 把表单的参数，通过AJAX方式提交到后台
			cardsForm.form("submit",{
				url:"cards/save",
				success:function(data){ 
					data = $.parseJSON(data);//转为json
					if(data.success){
						cardsDialog.dialog("close");
						$.messager.alert("温馨提示",data.message,"info",function(){
							// 刷新表格
							cardsDataGrid.datagrid("reload");
						});
					}else{
						$.messager.alert("温馨提示",data.message,"info");
					}
				}
			});
		},
		//取消
		cards_cancel:function(){
			cardsDialog.dialog("close");
		},

		//删除
		cards_del:function(){
			// 1.获取选中行信息
			var rowData = cardsDataGrid.datagrid("getSelected");
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
					$.get("cards/delete",{id:id},function(data){
						if(data.success){
							$.messager.alert("温馨提示",data.message,"info",function(){
								cardsDataGrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.message,"error");
						}
					},"json");
				}
			});
			
		}, 

		//刷新
		cards_refresh:function(){
			cardsDataGrid.datagrid("reload");
		}

	};
	//对页面上所有按钮做一次统一的监听
	$("a[data-cmd]").on("click",function(){
		//获取data-cmd属性的值
		var cmd = $(this).data("cmd");
		console.debug(cmd);
		if (cmd) {
			cmdCards[cmd]();//执行对应的方法
		}
	});
});
function disableCards(id){
	$.messager.confirm("温馨提示","确认停用吗?",function(yes){
		if(yes){
			// 发送AJAX请求，修改ID对应数据状态
			$.get("cards/disable",{id:id},function(data){
				if(data.success){
					$.messager.alert("温馨提示",data.message,"info",function(){
						$("#cardsDataGrid").datagrid("reload");
					});
				}
			},"json");
		}
	});
}
function enableCards(id){
	$.messager.confirm("温馨提示","确认启用吗?",function(yes){
		if(yes){
			// 发送AJAX请求，修改ID对应数据状态
			$.get("cards/enable",{id:id},function(data){
				if(data.success){
					$.messager.alert("温馨提示",data.message,"info",function(){
						$("#cardsDataGrid").datagrid("reload");
					});
				}
			},"json");
		}
	});
}
</script>
	<!-- 数据表格 -->
	<table id="cardsDataGrid" >
		<!-- 定义表格的表头  -->
		<thead>
			<tr>
				<!-- field：指定需要显示列对应JSON数据属性 -->
				<th field="name" width="10" align="center">完成条件</th>
				<th field="info" width="40" align="center">奖品名称</th>
				<%--<th field="status" width="10" align="center" data-options="formatter:formatCardsStatus">状态</th>--%>
			</tr>
		</thead>
	</table>
	<!-- 工具条 -->
	<div id="cards_toolbar">
	 	<div>
		 	<form method="post" id="cards_searchForm">
				<%--<a class="easyui-linkbutton" iconCls="icon-add" data-cmd="cards_create">添加</a>--%>
				<a class="easyui-linkbutton" iconCls="icon-edit" data-cmd="cards_edit">修改</a>
				<%--<a class="easyui-linkbutton" iconCls="icon-remove" data-cmd="cards_del">删除</a>--%>
				<%--<a class="easyui-linkbutton" iconCls="icon-reload"  data-cmd="cards_refresh">刷新</a>--%>
		 	</form>
		 </div> 
	</div>


	<!-- 录入窗口 -->
	<div id="cardsDialog" class="easyui-dialog" style="width: 400px;height: 300px;"
	data-options="title:'编辑集卡',modal:true,iconCls:'icon-edit',buttons:'#cards_bs',closed:true">
			<form id="cardsForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id"></input>
			<input type="hidden"  name="name" >
			<div style="margin-top: 20px;margin-left: 35px" >
	 			奖品名称：
	 			<input  name="info"  class="easyui-textbox" data-options="required:true,multiline:true" style="width: 250px;height:150px">
			</div>		
			</form>
		</div>
	<!-- 录入窗口按钮 -->
	<div id="cards_bs">
		<a class="easyui-linkbutton" iconCls="icon-save" data-cmd="cards_save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cards_cancel">取消</a>
	</div>

</body>
</html>