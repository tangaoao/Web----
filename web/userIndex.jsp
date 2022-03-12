<%--
  Created by IntelliJ IDEA.
  User: 汤澳
  Date: 2021/12/7
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>停车就来停车宝！</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="title" align="center">
    <span class="main-title">停车宝</span>
    <h4 class="other-title" style="color: #09785a;font-size: 30px">使用停车宝，停车没烦恼！</h4>
</div>
<div class="content" >
    <form action="UserLoginServlet" method="post">
    <div class="form sign-in" >
        <h2>欢迎回来</h2>
        <label>
            <span>邮箱</span>
            <input type="email" name="username"/>
        </label>
        <label>
            <span>密码</span>
            <input type="password" name="password"/>
        </label>
        <p class="forgot-pass"><a href="javascript:">忘记密码？</a></p>
        <button type="submit" class="submit" style="background-color: #0aa699">登 录</button>
        <div style="position: absolute;margin-left: 200px">
            默认账号：3237985952@qq.com<br>
            密码：123
        </div>
    </div>
    </form>
    <div class="sub-cont" >
        <div class="img">
            <div class="img__text m--up">
                <h2>还未注册？</h2>
                <p>立即注册，发现大量机会！</p>
            </div>
            <div class="img__text m--in">
                <h2>已有帐号？</h2>
                <p>有帐号就登录吧，好久不见了！</p>
            </div>
            <div class="img__btn" >
                <span class="m--up" >注 册</span>
                <span class="m--in">登 录</span>
            </div>
        </div>
        <form action="addUserServlet" method="post">
        <div class="form sign-up" >
            <h2>立即注册</h2>
            <label>
                <span>用户名</span>
                <input type="text" name="username" />
            </label>
            <label>
                <span>邮箱号</span>
                <input type="email" name="email"/>
            </label>
            <label>
                <span>车牌号</span>
                <input type="text" name="age"/>
            </label>
            <label>
                <span>密码</span>
                <input type="password" name="pass"/>
            </label>
            <button type="submit" class="submit" style="background-color: #0aa699">注 册</button>
        </div>
        </form>
    </div>
</div>
<div class="end" style="background-color: #0aa699">
    <div class="end-text">
        <a href="index.jsp">后台管理</a>
    </div>
</div>
<script src="js/script.js"></script>

</body>

</html>