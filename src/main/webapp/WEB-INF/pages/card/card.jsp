<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<hecard>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</hecard>
<body>

<script type="text/javascript">
$(function(){
	//缓存要用到的组件
	var cardDataGrid = $("#cardDataGrid");//数据表格
	var cardDialog = $("#cardDialog");//录入窗口
	var cardForm = $("#cardForm");//录入表单
	var cardQuery = $("#cardQuery");//录入表单
	var cardsCombobox = $("#cardsCombobox");//红包下拉框
	   
	//初始化组件
	cardDataGrid.datagrid({//初始化数据表格
		fit:true,
		border:false,
		url:'card/query',
		singleSelect:true,
		fitColumns:true,
		striped:true,
		rownumbers:true,
		pagination:true,
		pageSize:10,
		pageList:[5,10,20,50],
		toolbar:'#card_toolbar'
	});
	
	
	//修改概率框最大值
	var updateRatioMax = function(param){
		$("#ratio").numberbox("options").max = param.ratio;
		if(parseInt($("#ratio").val()) > parseInt(param.ratio)){
			console.log($("#ratio").val());
			console.log(param.ratio);
			$('#ratio').numberbox('setValue',param.ratio);
		}
	}
	
	//创建cmdType管理所有操作函数
	var cmdCard={
		//新增
		card_create:function(){
			cardsCombobox.combobox({
			   url:'cards/getOfRatio?cardId=-1',
		       valueField:'id',
		       textField:'name',
		       prompt:'主卡',
		       onSelect:updateRatioMax
			});
			// 1.清空对话框数据
			cardForm.form("clear");
			// 2.打开添加对话框
			cardDialog.dialog("open");
		},
		//编辑
		card_edit:function(){
			// 3.清空对话框数据
			cardForm.form("clear");
			// 1.获取选中行信息
			var rowData = cardDataGrid.datagrid("getSelected");
			// 2.判断
			if(!rowData){
				$.messager.alert("温馨提示","你没有选中任何数据","info");
				return;
			}

			// 4.打开添加对话框
			cardDialog.dialog("open");

			cardForm.form("load",rowData);
		},

		//保存
		card_save:function(){
			// 把表单的参数，通过AJAX方式提交到后台
			cardForm.form("submit",{
				url:"card/save",
				success:function(data){ 
					data = $.parseJSON(data);//转为json
					if(data.success){
						cardDialog.dialog("close");
						$.messager.alert("温馨提示",data.message,"info",function(){
							// 刷新表格
							cardDataGrid.datagrid("reload");
						});
					}else{
						$.messager.alert("温馨提示",data.message,"info");
					}
				}
			});
		},
		//取消
		card_cancel:function(){
			cardDialog.dialog("close");
		},

		//删除
		card_del:function(){
			// 1.获取选中行信息
			var rowData = cardDataGrid.datagrid("getSelected");
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
					$.get("card/delete",{id:id},function(data){
						if(data.success){
							$.messager.alert("温馨提示",data.message,"info",function(){
								cardDataGrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.message,"error");
						}
					},"json");
				}
			});
			
		}, 

		//刷新
		card_refresh:function(){
			cardDataGrid.datagrid("reload");
		},
		//高级查询
		card_search:function(){
			//把参数封装成一个json对象传到前台
			 cardDataGrid.datagrid('load',cardQuery.serializeJson()); 
		}

	};
	//对页面上所有按钮做一次统一的监听
	$("a[data-cmd]").on("click",function(){
		//获取data-cmd属性的值
		var cmd = $(this).data("cmd");
		console.debug(cmd);
		if (cmd) {
			cmdCard[cmd]();//执行对应的方法
		}
	});
});
</script>
	<!-- 数据表格 -->
	<table id="cardDataGrid" >
		<!-- 定义表格的表头  -->
		<thead>
			<tr>
				<!-- field：指定需要显示列对应JSON数据属性 -->
				<th field="name" width="10" align="center">名称</th>
				<%--<th field="cards" width="10" align="center" data-options="formatter:formatName">主卡</th>--%>
				<%--<th field="image" width="10" align="center" data-options="formatter:formatImg">图片</th>--%>
				<th field="ratio" width="10" align="center">出现概率（%）</th>
			</tr>
		</thead>
	</table>
	<!-- 工具条 -->
	<div id="card_toolbar">
	 	<div>
		 	<form method="post" id="cardQuery">
				<a class="easyui-linkbutton" iconCls="icon-add" data-cmd="card_create">添加</a>
				<a class="easyui-linkbutton" iconCls="icon-edit" data-cmd="card_edit">编辑</a>
				<a class="easyui-linkbutton" iconCls="icon-remove" data-cmd="card_del">删除</a>
				<%--卡片名称:<input class="easyui-textbox" name="name" style="width: 150px">--%>
				<%--主卡名称:<input class="easyui-textbox" name="cardsName" style="width: 150px">--%>
				<%--<a class="easyui-linkbutton" iconCls="icon-search"--%>
					<%--data-cmd="card_search">搜索</a>--%>
		 	</form>
		 </div> 
	</div>


	<!-- 录入窗口 -->
	<div id="cardDialog" class="easyui-dialog" style="width: 300px;height: 260px;"
	data-options="title:'编辑广告',modal:true,iconCls:'icon-edit',buttons:'#card_bs',closed:true">
			<form id="cardForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id"/>
			<div style="margin-top: 20px;margin-left: 35px" >
	 			卡片名称：
	 			<input  name="name"  class="easyui-textbox" data-options="required:true" style="width: 150px">
			</div>	
			<div style="margin-top: 20px;margin-left: 35px" >
	 			出现概率：
	 			<input  name="ratio" id="ratio"  class="easyui-numberbox" data-options="required:true,max:100,min:0.0001,precision:4" style="width: 150px">
			</div>	
			<%--<div style="margin-top: 15px;margin-left: 35px" >--%>
				<%--选择主卡：--%>
				<%--<select id="cardsCombobox" name="cardsId" style="width: 150px"> </select>--%>
			<%--</div>	--%>
			<%--<div style="margin-top: 10px;margin-left: 35px" >--%>
	 			<%--卡片图片：--%>
	 			<%--<input class="easyui-filebox" name="img" style="width: 150px" >--%>
			<%--</div>--%>
			</form>
		</div>
	<!-- 录入窗口按钮 -->
	<div id="card_bs">
		<a class="easyui-linkbutton" iconCls="icon-save" data-cmd="card_save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="card_cancel">取消</a>
	</div>

</body>
</html>