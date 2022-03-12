package com.java.qianServlet;

import com.java.dao.UserDao;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.ResponseUtil;
import com.sun.glass.ui.Window;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "UserSelfModifyServlet", value = "/UserSelfModifyServlet")
public class UserSelfModifyServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String chepai = request.getParameter("age");
        String userId = request.getParameter("id");
        System.out.println(email+"--"+"--"+userId);
        User user = new User(username,password,chepai,email);
        Connection con = null;
        user.setUserId(Integer.parseInt(userId));
        try {
            con = dbUtil.getCon();
            userDao.userModify(con,user);
//            response.sendRedirect("userAction.jsp");
            request.getRequestDispatcher("userIndex.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}
