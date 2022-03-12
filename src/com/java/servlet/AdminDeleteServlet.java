package com.java.servlet;

import com.java.dao.AdminDao;
import com.java.util.DbUtil;
import com.java.util.ResponseUtil;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

@WebServlet(name = "AdminDeleteServlet", value = "/AdminDeleteServlet")
public class AdminDeleteServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    AdminDao adminDao = new AdminDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delIds=request.getParameter("delIds");
        Connection con = null;
        DbUtil dbUtil = new DbUtil();
        System.out.println("序列号为"+delIds);
        try {
            JSONObject result=new JSONObject();
            con = dbUtil.getCon();
            adminDao.adminDelete(con, delIds);
            ResponseUtil.write(response, result);
        }catch (Exception e){
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
