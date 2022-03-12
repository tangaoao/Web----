package com.java.servlet;

import com.java.dao.AdminDao;
import com.java.model.Admin;
import com.java.util.DbUtil;
import com.java.util.ResponseUtil;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import com.java.util.StringUtil;

@WebServlet(name = "AdminSaveServlet", value = "/AdminSaveServlet")
public class AdminSaveServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    AdminDao adminDao = new AdminDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Admin resultAdmin = null;
        String admin_username = request.getParameter("admin_username");
        String admin_pass = request.getParameter("admin_pass");
        String admin_name = request.getParameter("admin_name");
        String admin_id = request.getParameter("adminId");
        request.setAttribute("admin_username", admin_username);
        request.setAttribute("admin_pass", admin_pass);
        request.setAttribute("admin_name", admin_name);


        System.out.println(";用户名：" + admin_username + ";密码：" + admin_pass + ";真实姓名：" + admin_name);
        Admin admin = new Admin(admin_username, admin_pass, admin_name);


        if (StringUtil.isNotEmpty(admin_id)) {
            admin.setAdminId(Integer.parseInt(admin_id));
            System.out.println(admin_id+" ---");
        }else {
            System.out.println("序列号为空");
        }
        Connection con = null;
        try {
            con = dbUtil.getCon();
            int saveNums = 0;
            JSONObject result = new JSONObject();
            if (StringUtil.isNotEmpty(admin_id)) {
                saveNums = adminDao.adminModify(con, admin);
            } else {
                saveNums = adminDao.addAdmin(con, admin);
            }
            if (saveNums > 0) {
                result.put("success", "true");
            } else {
                result.put("success", "true");
                result.put("errorMsg", "保存失败");
            }
            ResponseUtil.write(response, result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
