<%--
  Created by IntelliJ IDEA.
  User: 汤澳
  Date: 2021/12/12
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户信息管理</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        var url;
        //删除用户信息
        function deleteUser(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length===0){
                $.messager.alert("系统提示","请选择要删除的数据！");
                return;
            }
            var strIds=[];
            for(var i=0;i<selectedRows.length;i++){
                strIds.push(selectedRows[i].userId);
            }
            var ids=strIds.join(",");
            $.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
                if(r){
                    $.post("UserDeleteServlet",{delIds:ids},function(result){
                        if(result.success){
                            $.messager.alert('系统提示',"删除失败"/*result.errorMsg*/);
                            $("#dg").datagrid("reload");
                        }else{
                            $.messager.alert("系统提示","您已成功删除<font color=red>"+selectedRows.length+"</font>条数据！");
                            $("#dg").datagrid("reload");
                        }
                    },"json");
                }
            });
        }
        //查找用户信息
        function searchUser(){
            $('#dg').datagrid('load',{
                name:$('#u_username').val(),
                pwd:$('#u_pass').val(),
                age:$('#u_age').val(),
                email:$('#u_Email').val()
            });
        }

        //添加账户余额
        function addUserMoney(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length!=1){
                $.messager.alert("系统提示","请选择一条需要充值的账户信息！");
            }else {
                var row=selectedRows[0];
                $("#dcz").dialog("open").dialog("setTitle","充值");
                var ids = row.userId;
                /*$.post("UserMoneyAddServlet",{delId:ids});*/
            }
        }

        //打开添加用户
        function openUserAddDialog(){
            $("#dlg").dialog("open").dialog("setTitle","添加用户信息");
            url="UserAddServlet";
        }

        //保存用户信息
        function saveUser(){
            $("#fm").form("submit",{
                url:"UserAddServlet",
                onSubmit:function(){
                    return $(this).form("validate");
                },
                success:function(result){
                    if(result.errorMsg){
                        $.messager.alert("系统提示",result.errorMsg);
                        return;
                    }else{
                        $.messager.alert("系统提示","保存成功");
                        resetValue();
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                    }
                }
            });
        }

        //保存修改后的用户余额信息
        function saveAddUserMoney(){

            var addjine = $('#addjine').val();
            var selectedRows = $("#dg").datagrid('getSelections');
            var row=selectedRows[0];
            var jine = row.jine;
            var ids = row.userId;

            $.post("UserMoneyAddServlet",{delId:ids,jine:jine,addjine:addjine});

            $.messager.alert("系统提示","保存成功");

            resetValue();
            $("#dcz").dialog("close");
            $("#dg").datagrid("reload");
        }

        //重置表单
        function resetValue(){
            $("#name").val("");
            $("#pwd").val("");
            $("#age").val("");
            $("#email").val("");
            $("#addjine").val("");
        }
        function resetUserValue(){
            $("#addjine").val("");
        }


        //关闭表单
        function closeUserDialog(){
            $("#dlg").dialog("close");
            resetValue();
        }
        function closeDialog(){
            $("#dcz").dialog("close");
            resetValue();
        }
        //打开修改用户信息
        function openStudentModifyDialog(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length!=1){
                $.messager.alert("系统提示","请选择一条要编辑的数据！");
                return;
            }
            var row=selectedRows[0];
            $("#dxg").dialog("open").dialog("setTitle","编辑用户信息");
            $("#fm").form("load",row);
            var ids = row.userId;
     /*       url="UserSaveServlet?delId="+ids;*/

            $.post("UserModifyServlet",{delId:ids});

        }
    </script>
</head>
<body style="margin: 5px;">

<table id="dg" title="用户信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="UserListServlet" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="userId" width="50" align="center">编号</th>
        <th field="name" width="100" align="center">用户名</th>
        <th field="pwd" width="100" align="center">密码</th>
        <th field="age" width="100" align="center">车牌号</th>
        <th field="email" width="150" align="center">Email</th>
        <th field="jine" width="100" align="center">余额</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        <a href="javascript:openUserAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        <img src="../image/addmoney.png" style="width: 18px">
        <a href="javascript:addUserMoney()" class="easyui-linkbutton"  plain="true" >充值</a>

    </div>
    <div>
        &nbsp;用户名：&nbsp;<input type="text" name="u_username" id="u_username" size="10"/>
        &nbsp;密码：&nbsp;<input type="text" name="u_pass" id="u_pass" size="10"/>
        &nbsp;车牌号：&nbsp;<input type="text" id="u_age" name="u_age" size="10"/>
        &nbsp;Email：&nbsp;<input type="text" id="u_Email" name="u_Email" size="10"/>

        <a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="5px;">
            <tr>
                <td>用户名：</td>
                    <td><input type="text" name="name" id="name" class="easyui-validatebox" required="true"/></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>车牌号：</td>
                    <td><input type="text" name="age" id="age" class="easyui-validatebox" required="true" /></td>
            </tr>
            <tr>
                <td>邮箱：</td>
                <td><input type="text" name="email" id="email" class="easyui-validatebox" required="true" /></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>密码：</td>
                <td><input type="text" name="pwd" id="pwd" class="easyui-validatebox" required="true" validType="password"/></td>
            </tr>
        </table>
    </form>
</div>


<div id="dcz" class="easyui-dialog" style="width: 370px;height: 130px;padding: 10px 20px"
     closed="true" >
        <table cellspacing="5px;">
            <tr>
                <td>充值金额：</td>
                <td><input type="text" name="addjine" id="addjine" class="easyui-validatebox" required="true"/></td>
            </tr>
        </table>
        <div align="center">
            <a href="javascript:saveAddUserMoney()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
            <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
        </div>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeUserDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>
