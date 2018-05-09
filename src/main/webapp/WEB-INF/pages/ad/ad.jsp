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
	var adDataGrid = $("#adDataGrid");//数据表格
	var adDialog = $("#adDialog");//录入窗口
	var adForm = $("#adForm");//录入表单
	var adQuery = $("#adQuery");//录入表单
	var redpacketCombobox = $("#redpacketCombobox");//红包下拉框
	   
	//初始化组件
	adDataGrid.datagrid({//初始化数据表格
		fit:true,
		nowrap:false,
		border:false,
		url:'ad/query',
		singleSelect:true,
		fitColumns:true,
		striped:true,
		rownumbers:true,
		pagination:true,
		pageSize:10,
		pageList:[5,10,20,50],
		toolbar:'#ad_toolbar'
	});
	
	redpacketCombobox.combobox({ 
		   url:'redpacket/list',
	       valueField:'id',
	       textField:'name',
	       prompt:'红包',
		   editable:true,  
		   hasDownArrow:true,  
           filter: function(q, row){  
            var opts = $(this).combobox('options');  
            return row[opts.textField].indexOf(q) == 0;  
           }  
	    });
	
	
	
	//创建cmdType管理所有操作函数
	var cmdAd={
		//新增
		ad_create:function(){
			// 1.清空对话框数据
			adForm.form("clear");
			// 2.打开添加对话框
			adDialog.dialog("open");
		},
		
		//编辑
		ad_edit:function(){
			// 获取选中行信息
			var rowData = adDataGrid.datagrid("getSelected");
			// 判断
			if(!rowData){
				$.messager.alert("温馨提示","你没有选中任何数据","info");
				return;
			}
			//清空对话框数据
			adForm.form("clear");
			//打开添加对话框
			adDialog.dialog("open");
			// 特殊数据处理
			if(rowData.redpacket){
				rowData['redpacketId']=rowData.redpacket.id;
			}
			// 把数据加载到对话框中,回显数据
			adForm.form("load",rowData);
		},

		//保存
		ad_save:function(){
			// 把表单的参数，通过AJAX方式提交到后台
			adForm.form("submit",{
				url:"ad/save",
				success:function(data){ 
					data = $.parseJSON(data);//转为json
					if(data.success){
						adDialog.dialog("close");
						$.messager.alert("温馨提示",data.message,"info",function(){
							// 刷新表格
							adDataGrid.datagrid("reload");
						});
					}else{
						$.messager.alert("温馨提示",data.message,"info");
					}
				}
			});
		},
		//取消
		ad_cancel:function(){
			adDialog.dialog("close");
		},

		//删除
		ad_del:function(){
			// 1.获取选中行信息
			var rowData = adDataGrid.datagrid("getSelected");
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
					$.get("ad/delete",{id:id},function(data){
						if(data.success){
							$.messager.alert("温馨提示",data.message,"info",function(){
								adDataGrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.message,"error");
						}
					},"json");
				}
			});
			
		}, 

		//刷新
		ad_refresh:function(){
			adDataGrid.datagrid("reload");
		},
		//高级查询
		ad_search:function(){
			//把参数封装成一个json对象传到前台
			 adDataGrid.datagrid('load',adQuery.serializeJson()); 
		}

	};
	//对页面上所有按钮做一次统一的监听
	$("a[data-cmd]").on("click",function(){
		//获取data-cmd属性的值
		var cmd = $(this).data("cmd");
		console.debug(cmd);
		if (cmd) {
			cmdAd[cmd]();//执行对应的方法
		}
	});
});
</script>
	<!-- 数据表格 -->
	<table id="adDataGrid" >
		<!-- 定义表格的表头  -->
		<thead>
			<tr>
				<!-- field：指定需要显示列对应JSON数据属性 -->
				<th field="homepages" width="20" align="center" data-options="formatter:formatHomepagesTitle">已选择首页</th>
				<th field="title" width="10" align="center">广告主标题</th>
				<th field="content" width="20" align="center">内容</th>
				<th field="smallImg" width="10" align="center" data-options="formatter:formatImg">微缩图</th>
				<th field="sort" width="10" align="center">显示顺序</th>
				<th field="clickableNum" width="10" align="center">预定点击数</th>
				<th field="redpacket" width="10" align="center" data-options="formatter:formatName">红包</th>
				<th field="periodDay" width="10" align="center" data-options="formatter:formatPeriodDay">限制周期</th>
				<th field="info" width="20" align="center">介绍</th>
				<th field="image" width="10" align="center" data-options="formatter:formatImg">图片</th>
				<th field="url" width="20" align="center">链接</th>
			</tr>
		</thead>
	</table>
	<!-- 工具条 -->
	<div id="ad_toolbar">
	 	<div>
		 	<form method="post" id="adQuery">
				<a class="easyui-linkbutton" iconCls="icon-add" data-cmd="ad_create">添加</a>
				<a class="easyui-linkbutton" iconCls="icon-edit" data-cmd="ad_edit">编辑</a>
				<a class="easyui-linkbutton" iconCls="icon-remove" data-cmd="ad_del">删除</a>
				广告主标题:<input class="easyui-textbox" name="title" style="width: 150px">
				<a class="easyui-linkbutton" iconCls="icon-search"
					data-cmd="ad_search">搜索</a>
		 	</form>
		 </div> 
	</div>


	<!-- 录入窗口 -->
	<div id="adDialog" class="easyui-dialog" style="width: 520px;height: 430px;"
	data-options="title:'编辑广告',modal:true,iconCls:'icon-edit',buttons:'#ad_bs',closed:true">
			<form id="adForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id"></input>
			<div style="margin-top: 20px;margin-left: 35px" >
	 			广告标题：
	 			<input  name="title"  class="easyui-textbox" data-options="required:true,validType:'length[1,6]'" style="width: 150px">
	 			广告排序：
	 			<input  name="sort"  class="easyui-numberbox" data-options="required:true" style="width: 150px">
			</div>	
			<div style="margin-top: 20px;margin-left: 35px" >
	 			点击次数：
	 			<input  name="clickableNum"  class="easyui-numberbox" data-options="required:true" style="width: 150px">
	 			限制周期：
	 			<select name="periodDay" class='easyui-combobox'  data-options="required:true" style="width: 150px">
				    <option value="1">1天</option>
				    <option value="3">3天</option>
				    <option value="7">7天</option>
				    <option value="30">1个月</option>
				    <option value="90">3个月</option>
				    <option value="180">半年</option>
				    <option value="365">1年</option>
				    <option value="-1">不限制</option>
				</select> 
			</div>	
			<div style="margin-top: 20px;margin-left: 35px" >
	 			广告内容：
	 			<input  name="content"  class="easyui-textbox" data-options="multiline:true" style="width: 370px;height:70px">
			</div>
			<div style="margin-top: 20px;margin-left: 35px" >
	 			微缩图片：
	 			<input class="easyui-filebox" name="small" style="width: 150px" >
	 			广告链接：
	 			<input  name="url"  class="easyui-textbox" data-options="required:true" style="width: 150px">
			</div>
			<div style="margin-top: 10px;margin-left: 35px" >
	 			宣传图片：
	 			<input class="easyui-filebox" name="img" style="width: 150px" >
				选择红包：
				<select id="redpacketCombobox" name="redpacketId" style="width: 150px"> </select>
			</div>	
			<div style="margin-top: 20px;margin-left: 35px" >
	 			广告介绍：
	 			<input  name="info"  class="easyui-textbox" data-options="multiline:true" style="width: 370px;height:70px">
			</div>	
			</form>
		</div>
	<!-- 录入窗口按钮 -->
	<div id="ad_bs">
		<a class="easyui-linkbutton" iconCls="icon-save" data-cmd="ad_save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="ad_cancel">取消</a>
	</div>

</body>
</html>