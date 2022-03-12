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
        //删除车位信息
        function deleteParkingSpace(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length===0){
                $.messager.alert("系统提示","请选择要删除的数据！");
                return;
            }
            var strIds=[];
            for(var i=0;i<selectedRows.length;i++){
                strIds.push(selectedRows[i].cheweiId);
            }
            var ids=strIds.join(",");
            $.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
                if(r){
                    $.post("ParkingSpaceDeleteServlet",{delIds:ids},function(result){
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
        function searchParkingSpace(){
            $('#dg').datagrid('load',{
                bianHao:$('#p_bianHao').val(),
                leiBie:$('#p_leiBie').val(),
                quYu:$('#p_quYu').val()
            });
        }

        //打开添加车位信息
        function openParkingSpaceAddDialog(){
            $("#dlg").dialog("open").dialog("setTitle","添加车位信息");
            url="ParkingSpaceSaveServlet";
        }

        function saveParkingSpace(){
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
            $("#bianHao").val("");
            $("#leiBie").val("");
            $("#quYu").val("");
        }

        function closeAdminDialog(){
            $("#dlg").dialog("close");
            resetValue();
        }

        function openParkingSpaceModifyDialog(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length!=1){
                $.messager.alert("系统提示","请选择一条要编辑的数据！");
                return;
            }
            var row=selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle","编辑车位信息");
            $("#fm").form("load",row);
            url="ParkingSpaceSaveServlet?cheweiId="+row.cheweiId;
        }
    </script>
</head>
<body style="margin: 5px;">
<table id="dg" title="车位信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="ParkingSpaceListServlet" fit="true" toolbar="#tb">
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

<div id="tb">
    <div>
        <a href="javascript:openParkingSpaceAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openParkingSpaceModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteParkingSpace()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div><%--&nbsp;用户员编号：&nbsp;<input type="text" name="a_id" id="a_id" size="10"/>--%>
        &nbsp;车位编号：&nbsp;<input type="text" name="p_bianHao" id="p_bianHao" size="10"/>
        &nbsp;车位类别：&nbsp;<input type="text" name="p_leiBie" id="p_leiBie" size="10"/>
        &nbsp;所属区域：&nbsp;<input type="text" id="p_quYu" name="p_quYu" size="10"/>
        <a href="javascript:searchParkingSpace()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 200px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="5px;">
            <tr>
                <td>车位编号：</td>
                <td><input type="text" name="bianHao" id="bianHao" class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>车位类别：</td>
                <td><input type="text" name="leiBie" id="leiBie" class="easyui-validatebox" required="true" /></td>
            </tr>
            <tr>
                <td>所属区域：</td>
                <td><input type="text" name="quYu" id="quYu" class="easyui-validatebox" required="true"/></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveParkingSpace()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeAdminDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>