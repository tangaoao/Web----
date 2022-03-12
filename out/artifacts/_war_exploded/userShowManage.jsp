<%--
  Created by IntelliJ IDEA.
  User: 汤澳
  Date: 2021/12/15
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>停车宝——用户个人信息</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <%
        // 权限验证
        if(session.getAttribute("currentUser")==null){
            response.sendRedirect("userIndex.jsp");
            return;
        }
    %>
    <script type="text/javascript">
        function tijiao(){
            var f = document.getElementsByTagName("form")[0];
            f.action=f.action+"id="+document.getElementById("input").value;
        }
        function dakai(){
            $("#dcz").dialog("open").dialog("setTitle","充值");
        }
        function save(){
            var addjine = $('#addjine').val();
            var id = document.getElementById("input").value;
            $.post("UserSelfAddServlet",{addjine:addjine,id:id});
            $("#dcz").dialog("close");
        }
        function close(){
            $("#dcz").dialog("close");
            resetValue();
        }
    </script>
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
                <li><a href="userAction.jsp">首页</a></li>
                <li><a href="parkingSpaceShowManage.jsp">可用停车位</a></li>
                <li><a href="parkingPriceShowManage.jsp">停车场收费</a></li>
                <li><a href="parkingSpaceReserve.jsp">预约停车位</a></li>
                <li><a href="userShowManage.jsp">当前用户：${currentUser.userEmail}</a></li>
            </ul>
        </div>
    </div>
</div>
<div style="position: absolute; margin-top: 150px;margin-left: 650px;font-size: 50px;color: white;font-family: 华文新魏">
    <div align="center">个人信息</div>
</div>
<section class="featured" style="height: 700px">

    <div class="container">
        <div class="row mar-bot40">
            <div class="col-md-6 col-md-offset-3">
                <div class="align-center" style="font-size: 30px;line-height: 50px">
                    <form action="UserSelfModifyServlet?" method="post">
                        用&ensp;户&ensp;ID：<input type="text" id="input" name="userId"  disabled="disabled" value="${currentUser.userId}" style="background-color: white;color: black;border:0.5px solid #378888;border-radius: 5px" ><br>
                        邮&ensp;箱&ensp;号：<input type="text"  name="email" value="${currentUser.userEmail}" style="background-color: white;color: black;border:0.5px solid #378888;border-radius: 5px" ><br>
                        密&ensp;&ensp;&ensp;&ensp;码：<input type="text"  name="password" value="${currentUser.userPass}" style="background-color: white;color: black;border:0.5px solid #378888;border-radius: 5px" ><br>
                        真实姓名：<input type="text"  name="username" value="${currentUser.userName}" style="background-color: white;color: black;border:0.5px solid #378888;border-radius: 5px" ><br>
                        车&ensp;牌&ensp;号：<input type="text"  name="age" value="${currentUser.userAge}"  style="background-color: white;color: black;border:0.5px solid #378888;border-radius: 5px " ><br>
                        账户余额：<input type="text" value="${currentUser.userMoney}" disabled="disabled" style="background-color: white;color: black;border:0.5px solid #378888; border-radius: 5px"><br><a>账户余额下次登陆后自动刷新</a><br>
                        <input type="button" onclick="dakai()" <%--onclick="javascript:window.location.href='UserSelfAddServlet?id=${currentUser.userId}'"--%> value="充值" style="background-color: #24a7db;border: 5px;font-size: 20px;height: 50px;margin-top: 20px;border-radius: 5px">
                        <input type="submit" onclick="tijiao()" value="修改用户信息" style="background-color: #24a7db;border: 5px;font-size: 20px;height: 50px;margin-top: 20px;border-radius: 5px">
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="footer" class="section footer" style="height: 40px;margin-bottom: 0px" >
    <div class="container" style="position: absolute;height: 50px;margin-bottom: 100px;margin-left: 50px">
        <div class="row align-center copyright" >
            <div class="col-sm-12"><p style="align-items: center;position: absolute;margin-left: 600px">版权所有 &copy; 2021 设计 - by 汤澳</p></div>
        </div>
    </div>
</section>

<div id="dcz" class="easyui-dialog" style="width: 370px;height: 200px;padding: 10px 20px"
     closed="true" >
    <table cellspacing="5px;">
        <tr>
            <td>充值金额：</td>
            <td><input type="text" name="addjine" id="addjine" class="easyui-validatebox" required="true"/></td>
        </tr>
    </table>
    <div align="center" style="margin-left: 50px;margin-top: 100px">
        <a href="javascript:save()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
        <a href="javascript:close()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
    </div>
</div>
</body>
</html>
