package com.java.servlet;

import com.java.dao.UserDao;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "UserSaveServlet", value = "/UserSaveServlet")
public class UserSaveServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User resultUser = null;

        String userName = request.getParameter("name");
        String userPass = request.getParameter("pwd");
        String userEmail = request.getParameter("email");
        String userAge = request.getParameter("age");
        String userId = request.getParameter("delId");
        System.out.println("用户名："+userName+"用户密码："+userPass+"用户Email:"+userEmail);
        request.setAttribute("userName",userName);
        request.setAttribute("userPass",userPass);
        request.setAttribute("userEmail",userEmail);
        request.setAttribute("userAge",userAge);

        User user = new User(userName,userPass,userAge,userEmail);
        System.out.println("用户ID:"+userId);
        if(StringUtil.isNotEmpty(userId)){
            user.setUserId(Integer.parseInt(userId));
        }else {
            System.out.println("查不到此用户");
        }
        Connection con = null;
        try{
            con = dbUtil.getCon();
            int saveNum = 0;
            JSONObject result = new JSONObject();
            if(StringUtil.isNotEmpty(userId)){
                System.out.println("修改用户信息:"+userId);
                saveNum = userDao.userModify(con,user);
            }else{
                System.out.println("添加用户信息，用户ID:"+userId);
                saveNum = userDao.addUsers(con,user);
            }
            if(saveNum>0){
                result.put("success","true");
            }else {
                result.put("success", "true");
                result.put("errorMsg", "保存失败");
            }
            ResponseUtil.write(response, result);
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
