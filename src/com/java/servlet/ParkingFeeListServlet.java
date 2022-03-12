package com.java.servlet;

import com.java.dao.BillDao;
import com.java.model.Bill;
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

@WebServlet(name = "ParkingFeeListServlet", value = "/ParkingFeeListServlet")
public class ParkingFeeListServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    BillDao billDao = new BillDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Bill bill = new Bill();
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");

        String hao = request.getParameter("hao");

        bill.setBillHao(hao);

        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Connection con=null;
        try{
            con = dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray= JsonUtil.formatRsToJsonArray(billDao.billList(con,pageBean,bill));
            int total=billDao.billCount(con,bill);
            result.put("rows", jsonArray);
            result.put("total", total);
            ResponseUtil.write(response, result);
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
