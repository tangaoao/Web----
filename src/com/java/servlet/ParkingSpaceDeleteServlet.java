package com.java.servlet;

import com.java.dao.ParkingSpaceDao;
import com.java.util.DbUtil;
import com.java.util.ResponseUtil;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "ParkingSpaceDeleteServlet", value = "/ParkingSpaceDeleteServlet")
public class ParkingSpaceDeleteServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    ParkingSpaceDao parkingSpaceDao = new ParkingSpaceDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delIds = request.getParameter("delIds");
        Connection con = null;
        try {
            JSONObject result=new JSONObject();
            con = dbUtil.getCon();
            parkingSpaceDao.parkingSpaceDelete(con,delIds);
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
