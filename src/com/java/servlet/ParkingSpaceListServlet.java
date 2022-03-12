package com.java.servlet;

import com.java.dao.ParkingSpaceDao;
import com.java.model.PageBean;
import com.java.model.ParkingSpace;
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

@WebServlet(name = "ParkingSpaceListServlet", value = "/ParkingSpaceListServlet")
public class ParkingSpaceListServlet extends HttpServlet {
    ParkingSpaceDao parkingSpaceDao = new ParkingSpaceDao();
    DbUtil dbUtil = new DbUtil();
/*      System.out.println("<script>alert('查找车位信息');</script>");*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ParkingSpace parkingSpace = new ParkingSpace();
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");

/*        String cheweiId = request.getParameter("cheweiId");*/
        String bianHao = request.getParameter("bianHao");
        String leiBie = request.getParameter("leiBie");
        String quYu = request.getParameter("quYu");

        System.out.println("区域"+bianHao+"类别："+leiBie+"区域："+quYu);
        parkingSpace.setBianHao(bianHao);
        parkingSpace.setLeiBie(leiBie);
        parkingSpace.setQuYu(quYu);
        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Connection con = null;
        try{
            con = dbUtil.getCon();
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JsonUtil.formatRsToJsonArray(parkingSpaceDao.parkingSpaceList(con,pageBean,parkingSpace));
            int total = parkingSpaceDao.parkingSpaceCount(con,parkingSpace);
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
