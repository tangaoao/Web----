package com.java.servlet;

import com.java.dao.UserDao;
import com.java.model.PageBean;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.JsonUtil;
import com.java.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "UserListServlet", value = "/UserListServlet")
public class UserListServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    DbUtil dbUtil = new DbUtil();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String userId = request.getParameter("id");
        String userName = request.getParameter("name");
        String userPass = request.getParameter("pwd");
        String userEmail = request.getParameter("email");
        String userAge = request.getParameter("age");
        String userJine = request.getParameter("jine");

        //将用户信息存放到User对象中
        user.setUserPass(userPass);
        user.setUserEmail(userEmail);
        user.setUserAge(userId);
        user.setUserMoney(userJine);
        user.setUserName(userName);
        user.setUserAge(userAge);

        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Connection con = null;
        try{
            con = dbUtil.getCon();
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JsonUtil.formatRsToJsonArray(userDao.userList(con,pageBean,user));
            int total = userDao.userCount(con,user);
            result.put("rows",jsonArray);
            result.put("total",total);
            ResponseUtil.write(response,result);
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
