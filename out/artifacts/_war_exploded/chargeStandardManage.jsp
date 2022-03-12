<%@ page import="com.java.util.DbUtil" %>
<%@ page import="java.sql.Connection" %><%--
  Created by IntelliJ IDEA.
  User: 汤澳
  Date: 2021/12/14
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收费标准</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        var url;

        //查找收费信息
        function searchParkingSpace(){
            $('#dg').datagrid('load',{
                price:$('#c_price').val()
            });
        }


        function saveChargeStandard(){
            var selectedRows = $("#dg").datagrid('getSelections');
            var row=selectedRows[0];
            var newCost = $('#c_price').val();
            var id = row.id;
            $.post("CostModifyServlet",{Id:id,newCosts:newCost});
            $.messager.alert("系统提示","保存成功");
            resetValue();
            $("#dlg").dialog("close");
            $("#dg").datagrid("reload");
        }

        function resetValue(){
            $("#c_price").val("");
        }

        function closeCostDialog(){
            $("#dlg").dialog("close");
            resetValue();
        }

        function openCostModifyDialog(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length!=1){
                $.messager.alert("系统提示","请选择一条要编辑的数据！");
                return;
            }else {
                $("#dlg").dialog("open").dialog("setTitle","修改车费标准");
                var row=selectedRows[0];
                var id = row.id;
            }
        }
    </script>
</head>
<body style="margin: 5px;">
<table id="dg" title="收费标准" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="ChargeStandardListServlet" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="time" width="50" align="center">时间段</th>
        <th field="price" width="50" align="center">收费标准（元/小时）</th>
    </tr>
    </thead>
</table>

<%--工具栏--%>
<div id="tb" align="right">
    <div>
        <a href="javascript:openCostModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改收费标准</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px;height: 200px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
        <table cellspacing="5px;">
            <tr>
                <td>收费标准（元/小时）：</td>
                <td><input type="text" name="c_price" id="c_price" class="easyui-validatebox" required="true" /></td>
            </tr>
        </table>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveChargeStandard()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeCostDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>
