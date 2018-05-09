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
	var homepageDataGrid = $("#homepageDataGrid");//数据表格
	var homepageDialog = $("#homepageDialog");//录入窗口
	var homepageForm = $("#homepageForm");//录入表单
	var adCombobox = $("#adCombobox");//卡集
	   
	//初始化组件
	homepageDataGrid.datagrid({//初始化数据表格
		fit:true,
		border:false,
		url:'homepage/query',
		singleSelect:true,
		fitColumns:true,
		striped:true,
		rownumbers:true,
		pagination:true,
		pageSize:10,
		pageList:[5,10,20,50],
		toolbar:'#homepage_toolbar'
	});
	
	
	//创建cmdType管理所有操作函数
	var cmdHomepage={
		//新增
		homepage_create:function(){
			// 1.清空对话框数据
			homepageForm.form("clear");
			
			adCombobox.combobox({
				   url:'ad/list',
			       valueField:'id',
			       textField:'title',
			       prompt:'广告主',
			       multiple:true,
				   editable:true,  
				   hasDownArrow:true,  
				   filter: function(q, row){  
					var opts = $(this).combobox('options');  
					return row[opts.textField].indexOf(q) == 0;  
				   }  
			    });
			
			// 2.打开添加对话框
			homepageDialog.dialog("open");
		},
		//编辑
		homepage_edit:function(){
			// 获取选中行信息
			var rowData = homepageDataGrid.datagrid("getSelected");
			// 判断
			if(!rowData){
				$.messager.alert("温馨提示","你没有选中任何数据","info");
				return;
			}
			//清空对话框数据
			homepageForm.form("clear");
			
			adCombobox.combobox({
				   url:'ad/list',
			       valueField:'id',
			       textField:'title',
			       prompt:'广告主',
			       multiple:true,
				   editable:true,  
				   hasDownArrow:true,  
				   filter: function(q, row){  
					var opts = $(this).combobox('options');  
					return row[opts.textField].indexOf(q) == 0;  
				   }  
			    });
			
			//打开添加对话框
			homepageDialog.dialog("open");
			// 特殊数据处理
			var adArr=[];
			$.each(rowData.ads,function(i,ad){
				adArr.push(ad.id);
			});
			rowData['adArr']=adArr;
			console.log(rowData);
			// 把数据加载到对话框中,回显数据
			homepageForm.form("load",rowData);
		},

		//保存
		homepage_save:function(){
			// 把表单的参数，通过AJAX方式提交到后台
			homepageForm.form("submit",{
				url:"homepage/save",
				onSubmit:function(params){
					// 1、 获得ad多选框的内容
					var adArr = adCombobox.combobox("getValues");
					// 2、 按照内容，拼装需要的结构
					$.each(adArr,function(i,adId){
						params['ads['+i+'].id'] = adId;
					});
				},
				success:function(data){//data只是json字符串，需要手动转 
					data = $.parseJSON(data);
					if(data.success){
						homepageDialog.dialog("close");
						$.messager.alert("温馨提示",data.message,"info",function(){
							// 刷新表格
							homepageDataGrid.datagrid("load");
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
		homepage_cancel:function(){
			homepageDialog.dialog("close");
		},

		//删除
		homepage_del:function(){
			// 1.获取选中行信息
			var rowData = homepageDataGrid.datagrid("getSelected");
			console.debug(rowData);
			// 2.判断
			if(!rowData){
				$.messager.alert("温馨提示","你没有选中任何数据","info");
				return;
			}
			//提示用户是否删除
			$.messager.confirm("温馨提示","是否确认删除【"+rowData.title+"】?",function(yes){
				if(yes){
					// 获取数据的唯一标示
					var id = rowData.id;
					// 发送AJAX请求，修改ID对应数据状态
					$.get("/homepage/delete",{id:id},function(data){
						if(data.success){
							$.messager.alert("温馨提示",data.message,"info",function(){
								homepageDataGrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.message,"error");
						}
					},"json");
				}
			});
			
		}, 

		//刷新
		homepage_refresh:function(){
			homepageDataGrid.datagrid("reload");
		}

	};
	//对页面上所有按钮做一次统一的监听
	$("a[data-cmd]").on("click",function(){
		//获取data-cmd属性的值
		var cmd = $(this).data("cmd");
		console.debug(cmd);
		if (cmd) {
			cmdHomepage[cmd]();//执行对应的方法
		}
	});
});
function disableHomepage(id){
	$.messager.confirm("温馨提示","确认停用吗?",function(yes){
		if(yes){
			// 发送AJAX请求，修改ID对应数据状态
			$.get("homepage/disable",{id:id},function(data){
				console.log(data);
				if(data.success){
					$.messager.alert("温馨提示",data.message,"info",function(){
						$("#homepageDataGrid").datagrid("reload");
					});
				}
			},"json");
		}
	});
}
function enableHomepage(id){
	$.messager.confirm("温馨提示","确认启用吗?",function(yes){
		if(yes){
			// 发送AJAX请求，修改ID对应数据状态
			$.get("homepage/enable",{id:id},function(data){
				console.log(data);
				if(data.success){
					$.messager.alert("温馨提示",data.message,"info",function(){
						$("#homepageDataGrid").datagrid("reload");
					});
				}
			},"json");
		}
	});
}
</script>
	<!-- 数据表格 -->
	<table id="homepageDataGrid" >
		<!-- 定义表格的表头  -->
		<thead>
			<tr>
				<!-- field：指定需要显示列对应JSON数据属性 -->
				<th field="title" width="10" align="center">名称</th>
				<th field="info" width="40" align="center">说明</th>
				<th field="adMaxNum" width="20" align="center">广告主最多点击次数</th>
				<th field="image" width="10" align="center" data-options="formatter:formatImg">图片</th>
				<th field="qrcode" width="10" align="center" data-options="formatter:formatImg">二维码</th>
				<th field="status" width="10" align="center" data-options="formatter:formatHomepageStatus">状态</th>
			</tr>
		</thead>
	</table>
	<!-- 工具条 -->
	<div id="homepage_toolbar">
	 	<div>
		 	<form method="post" id="homepage_searchForm">
				<a class="easyui-linkbutton" iconCls="icon-add" data-cmd="homepage_create">添加</a>
				<a class="easyui-linkbutton" iconCls="icon-edit" data-cmd="homepage_edit">编辑</a>
				<a class="easyui-linkbutton" iconCls="icon-remove" data-cmd="homepage_del">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload"  data-cmd="homepage_refresh">刷新</a>
		 	</form>
		 </div> 
	</div>


	<!-- 录入窗口 -->
	<div id="homepageDialog" class="easyui-dialog" style="width: 400px;height: 340px;"
	data-options="title:'编辑红包',modal:true,iconCls:'icon-edit',buttons:'#homepage_bs',closed:true">
			<form id="homepageForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id"></input>
				<div style="margin-top: 20px;margin-left: 35px" >
	 				首页名字：
	 				<input  name="title"  class="easyui-textbox" data-options="required:true" style="width: 250px">
				</div>		
				<div style="margin-top: 20px;margin-left: 35px" >
	 				广告主最多点击次数：
	 				<input  name="adMaxNum"  class="easyui-numberbox" data-options="required:true" style="width: 190px;">
				</div>	
				<div style="margin-top: 15px;margin-left: 35px" >
					选择广告主：
					<select id="adCombobox" name="adArr" style="width: 238px"> </select>
				</div>	
				<div style="margin-top: 20px;margin-left: 35px" >
	 				首页说明：
	 				<input  name="info"  class="easyui-textbox" data-options="required:true,multiline:true" style="width: 250px;height:50px">
				</div>	
				<div style="margin-top: 10px;margin-left: 35px" >
		 			首页图片：
		 			<input class="easyui-filebox" name="img" style="width: 150px" >
				</div>
			</form>
		</div>
	<!-- 录入窗口按钮 -->
	<div id="homepage_bs">
		<a class="easyui-linkbutton" iconCls="icon-save" data-cmd="homepage_save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="homepage_cancel">取消</a>
	</div>

</body>
</html>