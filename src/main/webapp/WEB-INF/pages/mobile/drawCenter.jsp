<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mobile/css/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mobile/css/main.css">
    <title>抽奖记录</title>
</head>
<body>
<div class="header">
    <a href="#" onclick="history.back();">
        <img src="${pageContext.request.contextPath}/mobile/img/back.png" width="24">
    </a>
    <h1 class="title">抽奖中心</h1>
</div>
<div class="body">
    <div class="top">
        <div class="row">
            <div class="col start">
                <img class="img record" src="${pageContext.request.contextPath}/mobile/img/icon_z.png">
            </div>
            <div class="col end">
                <img class="img logo" src="${pageContext.request.contextPath}/mobile/img/logo.png">
            </div>
        </div>
    </div>
    <div class="bonus">
        <input type="hidden" id="redpacketId" value="${redpacketId}">
        <div id="luck"><!-- luck -->
            <table>
                <tr>
                    <td class="luck-unit luck-unit-0">
                        <span>${card0.name}</span>
                        <input type="hidden" id="${card0.id}" value="0">
                    </td>
                    <td class="luck-unit luck-unit-1">
                        <span>${card1.name}</span>
                        <input type="hidden" id="${card1.id}" value="1">
                    </td>
                    <td class="luck-unit luck-unit-special luck-unit-2">
                        <span>红包<br/>${amount}</span>
                        <input type="hidden" id="-1" value="2">
                    </td>
                </tr>
                <tr>
                    <td class="luck-unit  luck-unit-7">
                        <span>${card2.name}</span>
                        <input type="hidden" id="${card2.id}" value="7">
                    </td>
                    <td class="cjBtn" id="btn">
                        <span>
                          <img src="${pageContext.request.contextPath}/mobile/img/qtbtn_one.png" alt="">
                        </span>
                    </td>
                    <td class="luck-unit luck-unit-3">
                        <span>${card3.name}</span>
                        <input type="hidden" id="${card3.id}" value="3">
                    </td>
                </tr>
                <tr>
                    <td class="luck-unit luck-unit-6">
                        <span>${card4.name}</span>
                        <input type="hidden" id="${card4.id}" value="6">
                    </td>
                    <td class="luck-unit luck-unit-5">
                        <span>${card5.name}</span>
                        <input type="hidden" id="${card5.id}" value="5">
                    </td>
                    <td class="luck-unit luck-unit-4">
                        <span>${card6.name}</span>
                        <input type="hidden" id="${card6.id}" value="4">
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="slide">
        <div id="scrollDiv">
            <ul class="scroll-group">
                <c:forEach var="drawLog" items="${drawLogs}">
                    <li class="scroll-list">
                        <div class="name">${drawLog.userName}</div>
                        <div class="content">
                            <p>抽到<span>"${drawLog.remark}"</span></p>
                        </div>
                        <div class="time">
                            <fmt:formatDate value="${drawLog.receiveTime}" var="receiveTime" pattern="HH:mm:ss"/>
                                ${receiveTime}
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<div class="footer">
    <ul>
        <li class="active">
            <a href="#" class="first">
                <span>抽奖中心</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/mobile/userCards" class="second">
                <span>集字兑换</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/mobile/userInfo" class="three">
                <span>个人中心</span>
            </a>
        </li>
    </ul>
</div>
<script rel="script" type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/mobile/js/jq_scroll.js"></script>
<script>
    $(function () {
        // footer 切换
        $('.footer li').on('click', function () {
            $(this).addClass('active').siblings('li').removeClass('active')
        })
        $("#scrollDiv").Scroll({line: 1, speed: 500, timer: 2000});
        var luck = {
            index: 0,	//当前转动到哪个位置，起点位置
            count: 0,	//总共有多少个位置
            timer: 0,	//setTimeout的ID，用clearTimeout清除
            speed: 20,	//初始转动速度
            times: 0,	//转动次数
            cycle: 50,	//转动基本次数：即至少需要转动多少次再进入抽奖环节
            prize: -1,	//中奖位置
            init: function (id) {
                if ($("#" + id).find(".luck-unit").length > 0) {
                    $luck = $("#" + id);
                    $units = $luck.find(".luck-unit");
                    this.obj = $luck;
                    this.count = $units.length;
                    $luck.find(".luck-unit-" + this.index).addClass("active");
                }
                ;
            },
            roll: function () {
                var index = this.index;
                var count = this.count;
                var luck = this.obj;
                $(luck).find(".luck-unit-" + index).removeClass("active");
                index += 1;
                if (index > count - 1) {
                    index = 0;
                }
                ;
                $(luck).find(".luck-unit-" + index).addClass("active");
                this.index = index;
                return false;
            },
            stop: function (index) {
                this.prize = index;
                return false;
            }
        };

        function roll() {
            luck.times += 1;
            luck.roll();
            if (luck.times > luck.cycle + 10 && luck.prize == luck.index) {
                clearTimeout(luck.timer);
                luck.prize = -1;
                luck.times = 0;
                click = false;
            } else {
                if (luck.times < luck.cycle) {
                    luck.speed -= 10;
                } else if (luck.times == luck.cycle) {
                    var winner = "${winner}";
                    var index = $("#"+winner).val();
                    luck.prize = index;//最终中奖位置
                    setTimeout(drawResult, 3000);
                } else {
                    if (luck.times > luck.cycle + 10 && ((luck.prize == 0 && luck.index == 7) || luck.prize == luck.index + 1)) {
                        luck.speed += 110;
                    } else {
                        luck.speed += 20;
                    }
                }
                if (luck.speed < 40) {
                    luck.speed = 40;
                }
                ;

                luck.timer = setTimeout(roll, luck.speed);
            }
            return false;
        }

        function drawResult(){
            var redpacketId = $("#redpacketId").val();
            $.ajax({
                type: "POST",
                async: false,
                traditional:true,
                data: {
                    "redpacketId":redpacketId
                },
                cache:false,
                dataType: "json",
                url: "/mobile/draw",
                success: function(data){
                    if(data.success){
                        window.location.href="/mobile/drawResult?redpacketId="+redpacketId;
                    }else{
                        alert("抽奖失败");
                    }
                }
            });
        }

        var click = false;
        window.onload = function () {
            luck.init('luck');
            $("#btn").click(function () {
                if (click) {
                    return false;
                }else {
                    luck.speed = 100;
                    roll();
                    click = true;
                    return false;
                }
            });
        };
    })
</script>
</body>
</html>