package com.java.servlet;

import com.java.dao.ParkingSpaceDao;
import com.java.model.PageBean;
import com.java.model.ParkingSpace;
import com.java.util.DbUtil;
import com.java.util.JsonUtil;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "ParkingSpaceSaveServlet", value = "/ParkingSpaceSaveServlet")
public class ParkingSpaceSaveServlet extends HttpServlet {
    ParkingSpaceDao parkingSpaceDao = new ParkingSpaceDao();
    DbUtil dbUtil = new DbUtil();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String bianHao = request.getParameter("bianHao");
        String leiBie = request.getParameter("leiBie");
        String quYu = request.getParameter("quYu");
        String cheweiId = request.getParameter("cheweiId");
        System.out.println(";编号：" + bianHao + ";类别：" + leiBie + ";区域：" + quYu+"ID:"+cheweiId);

        ParkingSpace parkingSpace = new ParkingSpace(bianHao,leiBie,quYu);
        /*判断获取的ID是否为空*/
        if(StringUtil.isNotEmpty(cheweiId)){
            parkingSpace.setCheWeiId(Integer.parseInt(cheweiId));
        }else {
            System.out.println("ID为空");
        }

        Connection con=null;

        try{
            con = dbUtil.getCon();
            int saveNum = 0;
            JSONObject result = new JSONObject();
            /*如果车位ID不为空，执行修改操作，否则执行查询操作*/
            if(StringUtil.isNotEmpty(cheweiId)){
                System.out.println("修改停车场信息，ID"+cheweiId);
                saveNum = parkingSpaceDao.parkingSpaceModify(con,parkingSpace);
            }else {
                System.out.println("添加停车场");
                saveNum = parkingSpaceDao.parkingSpaceAdd(con,parkingSpace);
            }
            /*判断是否成功执行*/
            if(saveNum>0){
                result.put("success","true");
            } else {
                result.put("success", "true");
                result.put("errorMsg", "保存失败");
            }
            ResponseUtil.write(response, result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                dbUtil.closeCon(con);
            }catch (Exception e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
