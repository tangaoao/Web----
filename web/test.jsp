<%@ page import="java.sql.Connection" %><%--
  Created by IntelliJ IDEA.
  User: 汤澳
  Date: 2021/12/8
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>管理员信息管理</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        var url;
        //删除管理员信息
        function deleteStudent(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length===0){
                $.messager.alert("系统提示","请选择要删除的数据！");
                return;
            }
            var strIds=[];
            for(var i=0;i<selectedRows.length;i++){
                strIds.push(selectedRows[i].admin_id);
            }
            var ids=strIds.join(",");
            $.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
                if(r){
                    $.post("AdminDeleteServlet",{delIds:ids},function(result){
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
        //查找管理员信息
        function searchAdmin(){
            $('#dg').datagrid('load',{
                admin_id:$('#a_id').val(),
                admin_username:$('#a_username').val(),
                admin_pass:$('#a_pass').val(),
                admin_name:$('#a_name').val()
            });
        }

        //打开添加管理员
        function openAdminAddDialog(){
            $("#dlg").dialog("open").dialog("setTitle","添加管理员信息");
            url="AdminSaveServlet";
        }

        function saveAdmin(){
            $("#fm").form("submit",{
                url:url,
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

        function resetValue(){
            $("#admin_id").val("");
            $("#admin_name").val("");
            $("#admin_pass").val("");
            $("#admin_username").val("");
        }

        function closeAdminDialog(){
            $("#dlg").dialog("close");
            resetValue();
        }

        function openStudentModifyDialog(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length!=1){
                $.messager.alert("系统提示","请选择一条要编辑的数据！");
                return;
            }
            var row=selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle","编辑管理员信息");
            $("#fm").form("load",row);
            url="AdminSaveServlet?adminId="+row.admin_id;
        }
    </script>
</head>
<body style="margin: 5px;">
<table id="dg" title="管理员信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="adminListServlet" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="admin_id" width="50" align="center">编号</th>
        <th field="admin_username" width="100" align="center">用户名</th>
        <th field="admin_pass" width="100" align="center">密码</th>
        <th field="admin_name" width="150" align="center">真实姓名</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        <a href="javascript:openAdminAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openStudentModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteStudent()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div><%--&nbsp;用户员编号：&nbsp;<input type="text" name="a_id" id="a_id" size="10"/>--%>
        &nbsp;用户名：&nbsp;<input type="text" name="a_username" id="a_username" size="10"/>
        &nbsp;密码：&nbsp;<input type="text" name="a_pass" id="a_pass" size="10"/>
        &nbsp;真实姓名：&nbsp;<input type="text" id="a_name" name="a_name" size="10"/>

        <a href="javascript:searchAdmin()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="5px;">
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="admin_username" id="admin_username" class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>真实姓名：</td>
                <td><input type="text" name="admin_name" id="admin_name" class="easyui-validatebox" required="true" /></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>密码：</td>
                <td><input type="text" name="admin_pass" id="admin_pass" class="easyui-validatebox" required="true" validType="password"/></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveAdmin()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeAdminDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>