<%--
  Created by IntelliJ IDEA.
  User: 汤澳
  Date: 2021/12/12
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>订单信息管理</title>
  <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
  <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
  <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
  <script type="text/javascript">
    var url;
    //删除管理员信息
    function deleteBill(){
      var selectedRows=$("#dg").datagrid('getSelections');
      if(selectedRows.length===0){
        $.messager.alert("系统提示","请选择要删除的数据！");
        return;
      }
      var strIds=[];
      for(var i=0;i<selectedRows.length;i++){
        strIds.push(selectedRows[i].id);
      }
      var ids=strIds.join(",");
      $.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
        if(r){
          $.post("CostDeleteServlet",{delIds:ids},function(result){
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

    //查找订单信息
    function searchBill(){
      $('#dg').datagrid('load',{
        hao:$('#b_chepai').val()
      });
    }

    //打开添加管理员
    function openAdminAddDialog(){
      $("#dlg").dialog("open").dialog("setTitle","添加订单信息");
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
      $("#dlg").dialog("open").dialog("setTitle","编辑订单信息");
      $("#fm").form("load",row);
      url="AdminSaveServlet?adminId="+row.admin_id;
    }
  </script>
</head>
<body style="margin: 5px;">
<table id="dg" title="订单信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="ParkingFeeListServlet" fit="true" toolbar="#tb">
  <thead>
  <tr>
    <th field="cb" checkbox="true"></th>
    <th field="id" width="50" align="center">订单编号</th>
    <th field="hao" width="100" align="center">车牌号</th>
    <th field="jdate" width="100" align="center">进入日期</th>
    <th field="ldate" width="150" align="center">离开日期</th>
    <th field="jine" width="150" align="center">收费金额</th>
    <th field="shijian" width="150" align="center">停留时间</th>
  </tr>


  </thead>
</table>

<div id="tb">
  <div>
    <a href="javascript:deleteBill()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
  </div>
  <div>
    &nbsp;车牌号：&nbsp;<input type="text" name="b_chepai" id="b_chepai" size="10"/>
    <a href="javascript:searchBill()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
  </div>
</div>

<div id="dlg-buttons">
  <a href="javascript:saveAdmin()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
  <a href="javascript:closeAdminDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>
