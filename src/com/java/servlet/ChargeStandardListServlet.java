package com.java.servlet;

import com.java.dao.CostDao;
import com.java.model.Cost;
import com.java.model.PageBean;
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

@WebServlet(name = "ChargeStandardListServlet", value = "/ChargeStandardListServlet")
public class ChargeStandardListServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    Cost cost = new Cost();
    CostDao costDao = new CostDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Connection con=null;
        try{
            con = dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray= JsonUtil.formatRsToJsonArray(costDao.costList(con,pageBean,cost));
            int total=costDao.costCount(con,cost);
            result.put("rows", jsonArray);
            result.put("total", total);
            ResponseUtil.write(response, result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            }catch (Exception e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
