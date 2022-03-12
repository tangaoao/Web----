<%--
  Created by IntelliJ IDEA.
  User: 汤澳
  Date: 2021/12/13
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>停车宝</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/styles.css">
    <%
        // 权限验证
        if(session.getAttribute("currentUser")==null){
            response.sendRedirect("userIndex.jsp");
            return;
        }
    %>
</head>
<body>
<section id="header" class="appear"></section>
<div class="navbar navbar-fixed-top" role="navigation" data-0="line-height:100px; height:100px; background-color:rgba(0,0,0,0.3);" data-300="line-height:60px; height:60px; background-color:rgba(0,0,0,1);">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="fa fa-bars color-white"></span>
            </button>
            <h1><a class="navbar-brand" href="userIndex.jsp" data-0="line-height:90px;" data-300="line-height:50px;">停车宝—TCB</a></h1>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav" data-0="margin-top:20px;" data-300="margin-top:5px;" style="font-size: 50px">
                <li><a href="parkingSpaceShowManage.jsp">可用停车位</a></li>
                <li><a href="parkingPriceShowManage.jsp">停车场收费</a></li>
                <li><a href="parkingSpaceReserve.jsp">预约停车位</a></li>
                <li><a href="userShowManage.jsp">当前用户：${currentUser.userEmail}</a></li>
            </ul>
        </div>
    </div>
</div>

<section class="featured">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-6 col-md-offset-3">
                <div class="align-center">
                    <i class="fa fa-flask fa-5x mar-bot20"></i>
                    <h2 class="slogan">欢迎使用停车宝</h2>
                    <p>
                        停车宝所采用的现金停车技术，超出你的想象，依托现在最先进的北斗卫星导航技术，可以实现对车辆的毫米级别定位。快来体验吧！
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>


<section id="section-services" class="section pad-bot30 bg-white">
    <div class="container">
        <div class="row mar-bot40">
            <div class="col-lg-4" >
                <div class="align-center">
                    <i class="fa fa-code fa-5x mar-bot20"></i>
                    <h2 class="text-bold">方便</h2>
                    <p>进出口自动识别放行：无需人工取卡登记，车位可直接通过手机预约，进入停车场时，
                        自动扫描手机预约的停车许可证，自动识别身份放行。离开停车场时，本系统自动结算费用，付费成功即可离开。
                    </p>
                </div>
            </div>

            <div class="col-lg-4" >
                <div class="align-center">
                    <i class="fa fa-terminal fa-5x mar-bot20"></i>
                    <h2 class="text-bold">省时间</h2>
                    <p>停车场的出入识别系统都连接到同一个系统后台，可以通过该网页登录到这个系统查看所有停车场信息，系统接收用户的预约申请，
                        将预约申请指令下发至所要预约的车位，并即时将操作结果提示用户是否有车位，能否预定等情况
                    </p>
                </div>
            </div>

            <div class="col-lg-4" >
                <div class="align-center">
                    <i class="fa fa-bolt fa-5x mar-bot20"></i>
                    <h2 class="text-bold">透明</h2>
                    <p>智慧停车预约停车位、停车时长、停车付费均能在线完成，这样车主能够在线上直接查询到相关停车信息，从而避免停车场“任性”收费等乱象。
                    </p>
                </div>
            </div>

        </div>

    </div>
</section>
<section id="footer" class="section footer" style="height: 100px;margin-bottom: 0px" >
    <div class="container" style="position: absolute;height: 50px;margin-bottom: 100px;margin-left: 50px">
        <div class="row align-center copyright" >
            <div class="col-sm-12" style="align-items: center"><p style="align-items: center;position: absolute;margin-left: 600px">版权所有 &copy; 2021 设计 - by 汤澳</p></div>
        </div>
    </div>
</section>
</body>
</html>
