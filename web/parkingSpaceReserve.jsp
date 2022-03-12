<%--
  Created by IntelliJ IDEA.
  User: 汤澳
  Date: 2021/12/15
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        // 权限验证
        if(session.getAttribute("currentUser")==null){
            response.sendRedirect("userIndex.jsp");
            return;
        }
    %>
    <title>停车宝——车位展示</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        function opened(){
            var selectedRows=$("#dg").datagrid('getSelections');

            if(selectedRows.length!=1){
                alert("请选择一个需要预约的车位！");
                return;
            }else {
                var r=confirm("是否预约该车位？");
                var row=selectedRows[0];
                if (r==true)
                {
                    var ids = row.cheweiId;
                    var bian = row.bianHao;
                    var chepaihao = "${currentUser.userAge}";
                    $.post("ParkingSpaceReserveServlet",{bianHao:bian,cheweiId:ids,chepai:chepaihao},function(result){
                        if(result.success){
                            $.messager.alert("系统提示","您已成功预约<font color=red>"+bian+"</font>号车位，请在预定时间内到达！");
                            $("#dg").datagrid("reload");
                        }else{
                            $.messager.alert('系统提示',"车辆已存在车库中。");
                        }
                    },"json");
                }
                else
                {
                    alert("预约失败。")
                }
            }
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
    <div align="center">预约车位</div>
</div>
<div style="position: absolute; margin-top: 150px;margin-left: 1200px;font-size: 50px;color: white;font-family: 华文新魏">
<input type="button" onclick="opened();" value="预约" style="background-color: #24a7db;border: 5px;font-size: 20px;height: 50px;margin-top: 0px;border-radius: 5px">
</div>
    <section class="featured" style="height: 700px">
    <table id="dg" title="空闲车位信息" class="easyui-datagrid" fitColumns="true"
           pagination="true" rownumbers="true" url="ParkingSpaceIdleListServlet" fit="true" toolbar="#tb">
        <thead>
        <tr>
            <th field="cb" checkbox="true"></th>
            <th field="cheweiId" width="50" align="center">车位ID</th>
            <th field="bianHao" width="100" align="center">车位编号</th>
            <th field="leiBie" width="100" align="center">车位类别</th>
            <th field="quYu" width="150" align="center">所属区域</th>
            <th field="chePai" width="150" align="center">所停车辆</th>
            <th field="wDate" width="150" align="center">停车日期</th>
        </tr>
        </thead>
    </table>
</section>

<section id="footer" class="section footer" style="height: 40px;margin-bottom: 0px" >
    <div class="container" style="position: absolute;height: 50px;margin-bottom: 100px;margin-left: 50px">
        <div class="row align-center copyright" >
            <div class="col-sm-12" style="align-self: center" ><p style="align-items: center;position: absolute;margin-left: 600px">版权所有 &copy; 2021 设计 - by 汤澳</p></div>
        </div>
    </div>
</section>
</body>
</html>
