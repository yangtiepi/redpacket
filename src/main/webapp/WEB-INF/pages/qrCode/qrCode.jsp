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
	var qrCodeDataGrid = $("#qrCodeDataGrid");//数据表格
	var qrCodeDialog = $("#qrCodeDialog");//录入窗口
	var qrCodeForm = $("#qrCodeForm");//录入表单

	//初始化组件
	qrCodeDataGrid.datagrid({//初始化数据表格
		fit:true,
		nowrap:false,
		border:false,
		url:'qrCode/query',
		singleSelect:false,
		fitColumns:true,
		striped:true,
		rownumbers:true,
		pagination:true,
		pageSize:10,
		pageList:[5,10,20,50],
		toolbar:'#qrCode_toolbar'
	});

	//创建cmdType管理所有操作函数
	var cmdQrCode={
		//新增
		qrCode_create:function(){
			// 1.清空对话框数据
			qrCodeForm.form("clear");
			// 2.打开添加对话框
			qrCodeDialog.dialog("open");
		},
		//保存
		qrCode_save:function(){
			// 把表单的参数，通过AJAX方式提交到后台
			qrCodeForm.form("submit",{
				url:"qrCode/save",
				success:function(data){ 
					data = $.parseJSON(data);//转为json
					if(data.success){
						qrCodeDialog.dialog("close");
						$.messager.alert("温馨提示",data.message,"info",function(){
							// 刷新表格
							qrCodeDataGrid.datagrid("reload");
						});
					}else{
						$.messager.alert("温馨提示",data.message,"info");
					}
				}
			});
		},
		//取消
		qrCode_cancel:function(){
			qrCodeDialog.dialog("close");
		},

		//删除
		qrCode_del:function(){
			// 1.获取选中行信息
			var rowData = qrCodeDataGrid.datagrid("getSelected");
			console.debug(rowData);
			// 2.判断
			if(!rowData){
				$.messager.alert("温馨提示","你没有选中任何数据","info");
				return;
			}
			//提示用户是否删除
			$.messager.confirm("温馨提示","是否确认删除?",function(yes){
				if(yes){
					// 获取数据的唯一标示
                    var checkedItems = qrCodeDataGrid.datagrid('getChecked');
                    var  ids = [];
                    $.each(checkedItems, function(index, item){
                        ids.push(item.id);
                    });
					// 发送AJAX请求，修改ID对应数据状态
					$.get("/qrCode/delete",{ids:ids},function(data){
						if(data.success){
							$.messager.alert("温馨提示",data.message,"info",function(){
								qrCodeDataGrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.message,"error");
						}
					},"json");
				}
			});
			
		}, 

		//下载图片
        qrCode_download:function(){
            var checkedItems = qrCodeDataGrid.datagrid('getChecked');
            var  ids = [];
            $.each(checkedItems, function(index, item){
                ids.push(item.id);
            });
            // 发送AJAX请求，修改ID对应数据状态
			window.location.href = "/qrCode/download?ids="+ids.join(",");
		}

	};
	//对页面上所有按钮做一次统一的监听
	$("a[data-cmd]").on("click",function(){
		//获取data-cmd属性的值
		var cmd = $(this).data("cmd");
		console.debug(cmd);
		if (cmd) {
			cmdQrCode[cmd]();//执行对应的方法·
		}
	});
});
</script>
	<!-- 数据表格 -->
	<table id="qrCodeDataGrid" >
		<!-- 定义表格的表头  -->
		<thead>
			<tr>
				<!-- field：指定需要显示列对应JSON数据属性 -->
				<th field="" width="20" align="center" data-options="checkbox:true">编码</th>
				<th field="code" width="20" align="center">编码</th>
				<th field="url" width="10" align="center" data-options="formatter:formatImg">二维码</th>
				<th field="isUsed" width="10" align="center" data-options="formatter:formatQrCodeStatus">状态</th>
				<th field="usedTime" width="10" align="center" data-options="formatter:formatDateTime">抽奖时间</th>
			</tr>
		</thead>
	</table>
	<!-- 工具条 -->
	<div id="qrCode_toolbar">
	 	<div>
		 	<form method="post" id="qrCode_searchForm">
				<a class="easyui-linkbutton" iconCls="icon-add" data-cmd="qrCode_create">添加</a>
				<a class="easyui-linkbutton" iconCls="icon-remove" data-cmd="qrCode_del">删除</a>
				<a class="easyui-linkbutton" iconCls="icon-reload"  data-cmd="qrCode_download">下载图片</a>
		 	</form>
		 </div> 
	</div>


	<!-- 录入窗口 -->
	<div id="qrCodeDialog" class="easyui-dialog" style="width: 400px;height: 140px;"
	data-options="title:'生成二维码',modal:true,iconCls:'icon-edit',buttons:'#qrCode_bs',closed:true">
			<form id="qrCodeForm" method="post" enctype="multipart/form-data">
				<div style="margin-top: 20px;margin-left: 35px" >
	 				数量：
	 				<input  name="num"  class="easyui-numberbox" data-options="required:true,min:1,max:100,precision:0" style="width: 250px;">
				</div>
			</form>
		</div>
	<!-- 录入窗口按钮 -->
	<div id="qrCode_bs">
		<a class="easyui-linkbutton" iconCls="icon-save" data-cmd="qrCode_save">确定</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="qrCode_cancel">取消</a>
	</div>

</body>
</html>