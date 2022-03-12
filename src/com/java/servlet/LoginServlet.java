package com.java.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import com.java.model.Admin;
import com.java.util.DbUtil;
import com.java.dao.AdminDao;
import com.java.util.StringUtil;


@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    AdminDao adminDao = new AdminDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)){
            request.setAttribute("error","用户名或密码为空!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        Admin admin = new Admin(username,password);
        Connection con = null;
        try {
            con=dbUtil.getCon();
            Admin resultAdmin = adminDao.login(con, admin);
      /*      System.out.println(resultAdmin.toString());*/
            if(resultAdmin==null){
                request.setAttribute("error", "用户名或密码错误！");

                // 服务器跳转
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }else{
                // 获取Session
                HttpSession session=request.getSession();
                session.setAttribute("currentUser", resultAdmin);
                // 客户端跳转
                response.sendRedirect("main.jsp");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
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
