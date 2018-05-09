//格式化函数，返回值就是要显示的内容
/**
 * 时间格式化
 */
var format = function(time, format) {
	var t = new Date(time);
	var tf = function(i) {
		return (i < 10 ? '0' : '') + i;
	};
	return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a) {
		switch (a) {
		case 'yyyy':
			return tf(t.getFullYear());
			break;
		case 'MM':
			return tf(t.getMonth() + 1);
			break;
		case 'dd':
			return tf(t.getDate());
			break;
		case 'HH':
			return tf(t.getHours());
			break;
		case 'mm':
			return tf(t.getMinutes());
			break;
		case 'ss':
			return tf(t.getSeconds());
			break;
		}
		;
	});
};
/**
 * 时间
 * @param val
 * @param row
 * @returns
 */
function formatTime(val, row) {
	return format(val, 'yyyy-MM-dd');

}
/**
 * 准确时间
 * @param val
 * @param row
 * @returns
 */
function formatDateTime(val, row) {
	return format(val, 'yyyy-MM-dd HH:mm:ss');

}
/**
 * 任意对象的name属性
 * @param val
 * @param row
 * @param index
 * @returns
 */
function formatName(val,row,index){
	return  val ? val.name : "<font color='red'>未知</font>";
}
/**
 * 任意对象的username属性
 * @param val
 * @param row
 * @param index
 * @returns
 */
function formatUsername(val,row,index){
	return  val ? val.username : "<font color='gray'>无</font>";
}
/**
 * 任意对象的nickname属性
 * @param val
 * @param row
 * @param index
 * @returns
 */
function formatNickname(val,row,index){
	return  val ? val.nickname : "<font color='gray'>无</font>";
}
/**
 * 账户状态
 * @param v
 * @param r
 * @param i
 * @returns
 */
function formatEmployeeStatus(v,r,i){
	return v ? "<font color='green'>正常</font>" : "<font color='red'>停用</font>";
}
/**
 * 数据字典状态
 * @param v
 * @param r
 * @param i
 * @returns
 */
function formatSystemDictionaryStatus(v,r,i){
	return v ? "<font color='green'>正常</font>" : "<font color='red'>停用</font>";
}
/**
 * 集卡状态
 * @param v
 * @param r
 * @param i
 * @returns
 */
function formatCardsStatus(v,r,i){
	return v ? '<a href="#" onclick=disableCards('+r.id+')>使用中</a>' : '<a href="#" onclick=enableCards('+r.id+')>已停用</a>';
}
/**
 * 红包状态
 * @param v
 * @param r
 * @param i
 * @returns
 */
function formatRedpacketStatus(v,r,i){
	return v ? '<a href="#" onclick=disableRedpacket('+r.id+')>使用中</a>' : '<a href="#" onclick=enableRedpacket('+r.id+')>已停用</a>';
}
/**
 * 首页状态
 * @param v
 * @param r
 * @param i
 * @returns
 */
function formatHomepageStatus(v,r,i){
	return v ? '<a href="#" onclick=disableHomepage('+r.id+')>使用中</a>' : '<a href="#" onclick=enableHomepage('+r.id+')>已停用</a>';
}
/**
 * 集卡兑换状态
 * @param v
 * @param r
 * @param i
 * @returns
 */
function formatCardsCompleteStatus(v,r,i){
	return v ? "<font color='green'>已领取</font>" : '<a href="#" onclick=receiveCards('+r.id+')>领取</a>';
}
/**
 * 账号角色
 * @param v
 * @param r
 * @param i
 * @returns
 */
function formatEmployeeRole(v,r,i){
	var role = "";
	for (var i = 0; i < v.length; i++) {
		role += v[i].name;
		if (i < v.length - 1) {
			role += " ,";
		}
	}
	return role;
}
/**
 * 将图片地址格式化为图片
 * @param v
 * @param r
 * @param i
 * @returns {String}
 */
function formatImg(v,r,i){
	return '<img src="'+v+'" height="60px"/>';
}
/**
 * 将图片地址格式化为图片(100px)
 * @param v
 * @param r
 * @param i
 * @returns {String}
 */
function formatBigImg(v,r,i){
	return '<img src="'+v+'" height="120px"/>';
}
/**
 * 布尔值
 * @param v
 * @param r
 * @param i
 * @returns {String}
 */
function formatBoolean(v,r,i){
	return v ? "<font color='green'>是</font>" : "<font color='red'>否</font>";
}
/**
 * 给表格的行添加标题(鼠标悬停显示效果)
 * @param v
 * @param r
 * @param i
 * @returns {String}
 */
function formatTitle(v,r,i){
	return '<span title='+v+'>'+v+'</span>'  
}

/**
 * 查看异常
 * @param v
 * @param r
 * @param i
 * @returns {String}
 */
function formatException(v,r,i){
	return '<a href="#" onclick=showException('+v+')>查看异常</a>';
}
/**
 * 限制周期
 * @param v
 * @param r
 * @param i
 * @returns {String}
 */
function formatPeriodDay(v,r,i){
	    switch (v) {
		case 1:
			return "1天";
		case 3:
			return "3天";
		case 7:
			return "7天";
		case 30:
			return "1个月";
		case 90:
			return "3个月";
		case 180:
			return "半年";
		case 365:
			return "一年";
		case -1:
			return "不限制";
		default:
			return "不限制";
		}
}
/**
 * 查看广告点击详情
 * @param v
 * @param r
 * @param i
 * @returns {String}
 */
function formatAdClickLogDetail(v,r,i){
	return '<a href="#" onclick=showAdClickLog('+v+')>查看详情</a>';
}
/**
 * 查看红包领取详情
 * @param v
 * @param r
 * @param i
 * @returns {String}
 */
function formatRedpacketLogDetail(v,r,i){
	return '<a href="#" onclick=showRedpacketLog('+v+')>查看详情</a>';
}
/**
 * 查看红包领取详情
 * @param v
 * @param r
 * @param i
 * @returns {String}
 */
function formatRedpacketUrl(v,r,i){
	return window.document.location.href.replace('main','') + 'mobile/getRedpacket?redpacketId='+v;
}
/**
 * 首页名字
 * @param v
 * @param r
 * @param i
 * @returns {String}
 */
function formatHomepagesTitle(v,r,i){
	var homepage = "";
	if(v){
		for(var i=0;i < v.length;i++){
			homepage += v[i].title;
			homepage += ",";
		}
	}
	if(homepage){
		return homepage.substring(0, homepage.length - 1);
	}
	return homepage;
}

