package com.java.servlet;

import com.java.dao.AdminDao;
import com.java.dao.UserDao;
import com.java.model.Admin;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "UserLoginServlet", value = "/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    UserDao userDao = new UserDao();

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
            response.setContentType("text/html;charset=gb2312");
            PrintWriter out = response.getWriter();
            out.print("<script language='javascript'>alert('账号密码不能为空。');window.location.href='userIndex.jsp';</script>");
            out.flush();
            out.close();
            return;
        }

        User user = new User(username,password);
        Connection con = null;
        try {
            con=dbUtil.getCon();
            User resultUser=userDao.login(con, user);
            /*System.out.println(resultUser.toString());*/
            if(resultUser==null){
                response.setContentType("text/html;charset=gb2312");
                PrintWriter out = response.getWriter();
                out.print("<script language='javascript'>alert('账号密码输入错误，请重新输入。');window.location.href='userIndex.jsp';</script>");
                out.flush();
                out.close();
            }else{
                // 获取Session
                HttpSession session=request.getSession();
                session.setAttribute("currentUser", resultUser);
                // 客户端跳转
                response.sendRedirect("userAction.jsp");
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
