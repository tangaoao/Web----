package com.java.servlet;

import com.java.dao.UserDao;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "addUserServlet", value = "/addUserServlet")
public class addUserServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String password = request.getParameter("pass");
        request.setAttribute("username",username);
        request.setAttribute("email",email);
        request.setAttribute("age",age);
        request.setAttribute("password",password);
        User user = new User(username,password,age,email);
        Connection con = null;
        try{
            con = dbUtil.getCon();

            boolean b = userDao.selsetUser(con,user);
            if(b){
                response.setContentType("text/html;charset=gb2312");
                PrintWriter out = response.getWriter();
                out.print("<script language='javascript'>alert('该用户已存在。');window.location.href='userIndex.jsp';</script>");
                out.flush();
                out.close();
            }
            if(StringUtil.isEmpty(username)||StringUtil.isEmpty(email)||StringUtil.isEmpty(password)||StringUtil.isEmpty(age)){
                response.setContentType("text/html;charset=gb2312");
                PrintWriter out = response.getWriter();
                out.print("<script language='javascript'>alert('请完善注册信息后进行注册。');window.location.href='userIndex.jsp';</script>");
                out.flush();
                out.close();
            }
            else {
                User resultUser = userDao.addUser(con,user);
                response.setContentType("text/html;charset=gb2312");
                PrintWriter out = response.getWriter();
                out.print("<script language='javascript'>alert('注册成功。');window.location.href='userIndex.jsp';</script>");
                out.flush();
                out.close();
                // 获取Session
                HttpSession session=request.getSession();
                session.setAttribute("currentUser", resultUser);
                // 客户端跳转
//                response.sendRedirect("userIndex.jsp");
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
