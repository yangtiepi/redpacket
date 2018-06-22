//格式化函数，返回值就是要显示的内容
/**
 * 时间格式化
 */
var format = function(time, format) {
	if(!time){
		return '';
	}
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
 * 二维码状态
 * @param v
 * @param r
 * @param i
 * @returns
 */
function formatQrCodeStatus(v,r,i){
    return v ? "<font color='green'>已使用</font>" : "<font color='red'>未使用</font>";
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

