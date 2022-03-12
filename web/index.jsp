<%--
  Created by IntelliJ IDEA.
  User: 汤澳
  Date: 2021/12/6
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>停车场管理系统 </title>
  <link rel="stylesheet" type="text/css" href="css/zui.css" media="all">
  <link rel="stylesheet" type="text/css" href="css/login.css" media="all">
  <link href="css/animate.min.css" rel="stylesheet">
  <link href="css/font-awesome.min.css" rel="stylesheet">
  <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
  <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
  <style>
    body{
      background-image: url(/image/4.jpg);
    }
    ion-icon {
      font-size: 25px;
    }
  </style>
</head>
<body>
<div id="main-box"></div>
<div id="main-content">
  <div class="login-body  animated fadeInLeft">
    <div class="login-main pr">
      <form action="LoginServlet" method="post" class="login-form">
        <h3> 停车场管理系统 </h3>
        <h5 style="padding-bottom: 10px"> System Management Center </h5>
        <!-- 账号登陆 -->
        <div id="MobileBox" class="item-box"  >
          <div class="input-group user-name"> <span class="input-group-addon"><ion-icon name="person-outline"></ion-icon>
</span>
            <input type="text" name="username" class="form-control" placeholder="用户名/手机号">
          </div>
          <div class="input-group password"> <span class="input-group-addon"><ion-icon name="lock-closed-outline"></ion-icon>
</span>
            <input type="password" name="password" class="form-control" placeholder="密码">
          </div>
            <div class="kongbai" style="color: #08897e">
                默认账号：admin<br>
                密码：admin
            </div>
          <div class="login_btn_panel">
            <button class=" btn btn-primary btn-block btn-lg" data-ajax="post" type="submit" data-callback="success">登录</button>
            <div class="check-tips"></div>
          </div>
            <tr height="10">
                <td width="40%"></td>
                <td colspan="3">
                    <font color="#ff5e48">${error }</font>
                </td>
            </tr>
            <tr >
                <td></td>
            </tr>
      </form>
    </div>
  </div>
</div>
</body>
</html>

