<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript">
<!-- 换肤........................................ -->
function onChangeTheme(theme){
	var link = $('#the');
	link.attr('href', '/easyui/themes/'+theme+'/easyui.css');
	//alert('./easyui/themes/'+theme+'/easyui.css');
	
}
</script>

<!-- easyui依赖jquery库 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
<!-- easyui核心文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<!-- 表格扩展显示 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/datagrid-detailview.js"></script>
<!-- validate文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-validate/jquery.validate.js"></script>
<!-- easyui汉化文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<!-- 公共的js文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/redpacket/common.js"></script>
<!-- 公共的格式化js文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/redpacket/format.js"></script>
<!-- easyui主题/皮肤,并且使用的是默认 -->
<link id="the" rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
<!-- easyui图标样式 -->
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">

