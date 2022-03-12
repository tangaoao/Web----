package com.java.servlet;

import com.java.dao.UserDao;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.ResponseUtil;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "UserMoneyAddServlet", value = "/UserMoneyAddServlet")
public class UserMoneyAddServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    DbUtil dbUtil = new DbUtil();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String addMoney = request.getParameter("addjine");
        String money = request.getParameter("jine");
        String userId = request.getParameter("delId");

        User user = new User();

        user.setUserId(Integer.parseInt(userId));
        Connection con = null;
        try{
            con = dbUtil.getCon();
            JSONObject result = new JSONObject();
            int saveNum = userDao.addUserMoney(con,user,addMoney);
            if(saveNum>0){
                result.put("success","true");
            }else {
                result.put("success", "true");
                result.put("errorMsg", "保存失败");
            }
            ResponseUtil.write(response, result);
            System.out.println("用户ID:"+userId+"--");
            System.out.println("增添金额为："+addMoney+"原本金额为："+money);
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
