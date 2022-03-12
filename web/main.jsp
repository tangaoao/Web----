<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 汤澳
  Date: 2021/12/6
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>停车场信息管理系统主界面</title>
    <%
        // 权限验证
        if(session.getAttribute("currentUser")==null){
            response.sendRedirect("index.jsp");
            return;
        }
//        String name = session.getAttribute("currentUser").equals("username");
    %>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function(){
            // 数据
            var treeData;
            treeData = [{
                text: "停车场管理系统",
                children: [{
                    text: "管理员信息管理",
                    attributes: {
                        url: "test.jsp"
                    }
                }, {
                    text: "用户信息管理",
                    attributes: {
                        url: "userInfoManage.jsp"
                    }
                }, {
                    text: "车位信息管理",
                    attributes: {
                        url: "parkingSpaceInfoManage.jsp"
                    }
                }, {
                    text: "车费信息管理",
                    children: [{
                        text: "收费标准设置",
                        attributes: {
                            url: "chargeStandardManage.jsp"
                        }
                    }, {
                        text: "收费信息查看",
                        attributes: {
                            url: "parkingFeeInfoManage.jsp"
                        }
                    }]
                }]
            }];

            // 实例化树菜单
            $("#tree").tree({
                data:treeData,
                lines:true,
                onClick:function(node){
                    if(node.attributes){
                        openTab(node.text,node.attributes.url);
                    }
                }
            });

            // 新增Tab
            function openTab(text,url){
                if($("#tabs").tabs('exists',text)){
                    $("#tabs").tabs('select',text);
                }else{
                    var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"></iframe>";
                    $("#tabs").tabs('add',{
                        title:text,
                        closable:true,
                        content:content
                    });
                }
            }
        });
    </script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 80px;background-color: #E0EDFF">
    <div align="left" style="width: 80%;float: left">
        <h1 style="color: #0aa699;position: absolute;margin-left:1000px;font-size: 40px;width: 50%">停车场管理系统</h1>
    </div>
    <div  style="left: 20px;top: 20px;position: absolute;margin-top: 40px">当前用户：&nbsp;<font color="red" >${currentUser.adminUserName}</font></div>
</div>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" >
            <div align="center" style="padding-top: 250px;"><font color="#3cb371" size="10">停车宝官方后台管理系统</font></div>
        </div>
    </div>
</div>
<div region="west" style="width: 150px;" title="导航菜单" split="true">
    <ul id="tree"></ul>
</div>
<div region="south" style="height: 25px;" align="center">版权所有 @TingCheBao 2021/12/14</div>
</body>
</html>