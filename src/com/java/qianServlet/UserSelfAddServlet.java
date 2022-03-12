package com.java.qianServlet;

import com.java.dao.UserDao;
import com.java.model.User;
import com.java.util.DbUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "UserSelfAddServlet", value = "/UserSelfAddServlet")
public class UserSelfAddServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        String id = request.getParameter("id");
        String mon = request.getParameter("addjine");
        System.out.println(id+"--"+mon);
        user.setUserId(Integer.parseInt(id));
        Connection con = null;
        try {
            con = dbUtil.getCon();
           userDao.addUserMoney(con,user,mon);
            request.getRequestDispatcher("userAction.jsp").forward(request, response);
        }catch (Exception e){
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
