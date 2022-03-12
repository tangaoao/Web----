package com.java.servlet;

import com.java.dao.AdminDao;
import com.java.model.Admin;
import com.java.model.PageBean;
import com.java.util.DbUtil;
import com.java.util.JsonUtil;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "adminListServlet", value = "/adminListServlet")
public class adminListServlet extends HttpServlet {
    AdminDao adminDao = new AdminDao();
    DbUtil dbUtil = new DbUtil();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = new Admin();
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");

        String adminName = request.getParameter("admin_name");
        String adminUsername = request.getParameter("admin_username");
        String adminPass = request.getParameter("admin_pass");
        String adminId = request.getParameter("admin_id");

        admin.setAdminUserName(adminUsername);
        admin.setAdminName(adminName);
        admin.setAdminPass(adminPass);
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Connection con=null;
        try{
              con=dbUtil.getCon();
              JSONObject result=new JSONObject();
              JSONArray jsonArray=JsonUtil.formatRsToJsonArray(adminDao.adminList(con, pageBean,admin));
              int total=adminDao.adminCount(con,admin);
              result.put("rows", jsonArray);
              result.put("total", total);
              ResponseUtil.write(response, result);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
