package com.java.servlet;

import com.java.dao.CostDao;
import com.java.model.Cost;
import com.java.util.DbUtil;
import com.java.util.ResponseUtil;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "CostModifyServlet", value = "/CostModifyServlet")
public class CostModifyServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    CostDao costDao = new CostDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cost cost = new Cost();
        String id=request.getParameter("Id");
        String newCost = request.getParameter("newCosts");
        Connection con = null;
        System.out.println(id+"--"+newCost+"--");
        cost.setCostId(Integer.parseInt(id));
        try {
            con = dbUtil.getCon();
            JSONObject result = new JSONObject();
            int saveNum = 0;
            System.out.println("执行修改操作"+newCost);
            saveNum = costDao.costModify(con,cost,newCost);
            if(saveNum>0){
                result.put("success","true");
            }else {
                result.put("success", "true");
                result.put("errorMsg", "保存失败");
            }
            ResponseUtil.write(response, result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                dbUtil.closeCon(con);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
