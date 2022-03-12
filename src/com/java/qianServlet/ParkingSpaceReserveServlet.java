package com.java.qianServlet;

import com.java.dao.ParkingSpaceDao;
import com.java.model.ParkingSpace;
import com.java.util.DbUtil;
import com.java.util.ResponseUtil;
import com.mysql.cj.Session;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ParkingSpaceReserveServlet", value = "/ParkingSpaceReserveServlet")
public class ParkingSpaceReserveServlet extends HttpServlet {
    ParkingSpaceDao parkingSpaceDao = new ParkingSpaceDao();
    DbUtil dbUtil = new DbUtil();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ParkingSpace parkingSpace = new ParkingSpace();
        String cheweiId = request.getParameter("cheweiId");
        String chepai = request.getParameter("chepai");
        String bianHao = request.getParameter("bianHao");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");    // 这里填写的是想要进行转换的时间格式
        Date date = new Date();//获取当前日期
        String str = format.format(date);//获取String类型的当前时间

        PrintWriter out = response.getWriter();

        System.out.println("车位"+cheweiId+"chepai"+chepai+"当前时间："+str);
        parkingSpace.setCheWeiId(Integer.parseInt(cheweiId));
        parkingSpace.setChePai(chepai);
        parkingSpace.setwDate(date);
        Connection con = null;
        try{
            con = dbUtil.getCon();
            int saveNum = 0;
            JSONObject result = new JSONObject();
            Boolean b =parkingSpaceDao.parkingYesOrNot(con,parkingSpace);
            HttpSession session=request.getSession();
            session.setAttribute("jieguo",b);
            if(b==false){
            saveNum = parkingSpaceDao.parkingSpaceStateModify(con,parkingSpace,str);
            if(saveNum>0){
                result.put("success","true");
            }else {
                result.put("errorMsg", "保存失败");
            }
            }else {
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
