package com.java.dao;

import com.java.model.PageBean;
import com.java.model.ParkingSpace;
import com.java.util.DateUtil;
import com.java.util.StringUtil;

import java.sql.*;
import java.util.Date;

public class ParkingSpaceDao {
    /*
    * 输出所有车位信息
    * */
    public ResultSet parkingSpaceList(Connection con, PageBean pageBean, ParkingSpace parkingSpace) throws Exception{
        StringBuffer sb = new StringBuffer("select * from chewei");
        if(StringUtil.isNotEmpty(parkingSpace.getBianHao())){
            sb.append(" and bianHao like '%"+parkingSpace.getBianHao()+"%'");
            System.out.println("查找编号");
        }
        if(StringUtil.isNotEmpty(parkingSpace.getLeiBie())){
            sb.append(" and leiBie like '%"+parkingSpace.getLeiBie()+"%'");
            System.out.println("查找类别");
        }
        if(StringUtil.isNotEmpty(parkingSpace.getQuYu())){
            sb.append(" and quYu like '%"+parkingSpace.getQuYu()+"%'");
            System.out.println("查找区域");
        }
/*        if(DateUtil.isNotEmpty(parkingSpace.getwDate())){
            sb.append(" and bianHao like '%"+parkingSpace.getBianHao()+"%'");
        }*/
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
        }
        PreparedStatement pre = con.prepareStatement(sb.toString().replaceFirst("and","where"));
        return pre.executeQuery();
    }

    /*
    * 输出所有空闲车位信息
    * */
    public ResultSet parkingSpaceKong(Connection con, PageBean pageBean, ParkingSpace parkingSpace) throws Exception{
        StringBuffer sb = new StringBuffer("select * from chewei WHERE chePai='车位空闲'");
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
        }
        PreparedStatement pre = con.prepareStatement(sb.toString().replaceFirst("and","where"));
        return pre.executeQuery();
    }
    /*
    * 输出车位数量
    * */
    public int parkingSpaceCount(Connection con,ParkingSpace parkingSpace) throws  Exception{
        StringBuffer sb = new StringBuffer("select count(*) as total from chewei");
        PreparedStatement pre = con.prepareStatement(sb.toString().replaceFirst("and","where"));
        ResultSet res = pre.executeQuery();
        if(res.next()){
            return res.getInt("total");
        }else {
            return  0;
        }
    }

    /*
    * 删除停车位
    * */
    public int parkingSpaceDelete(Connection con,String delIds) throws  Exception{
        String sql = "DELETE FROM chewei WHERE cheweiId in ("+delIds+")";
        PreparedStatement pre = con.prepareStatement(sql);
        return pre.executeUpdate();
    }

    /*
    * 增加车位信息
    * */
    public  int parkingSpaceAdd(Connection con,ParkingSpace parkingSpace) throws SQLException {
        String sql = "INSERT INTO chewei(bianHao,leiBie,quYu) values(?,?,?)";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,parkingSpace.getBianHao());
        pre.setString(2,parkingSpace.getLeiBie());
        pre.setString(3,parkingSpace.getQuYu());
        return pre.executeUpdate();
    }

    /*
    * 修改车位信息
    * */
    public int parkingSpaceModify(Connection con,ParkingSpace parkingSpace) throws Exception{
        String sql = "UPDATE chewei set bianHao=?,leiBie=?,quYu=? WHERE cheweiId=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,parkingSpace.getBianHao());
        pre.setString(2,parkingSpace.getLeiBie());
        pre.setString(3,parkingSpace.getQuYu());
        pre.setInt(4,parkingSpace.getCheWeiId());
        return pre.executeUpdate();
    }

    /*
    * 修改车位状态信息
    * */
    public int parkingSpaceStateModify(Connection con,ParkingSpace parkingSpace,String data) throws Exception{
        String sql = "UPDATE chewei set chePai=?,wDate=? WHERE cheweiId=?";

        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        System.out.println(data+"--"+timeStamp+"--"+parkingSpace.getChePai());
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,parkingSpace.getChePai());
        pre.setTimestamp(2,timeStamp);
        pre.setInt(3,parkingSpace.getCheWeiId());
        return pre.executeUpdate();
    }


    /*
    * 查找当前用户是否已经停车
    * */
    public boolean parkingYesOrNot(Connection con,ParkingSpace parkingSpace) throws  Exception{
        String sql = "SELECT * FROM chewei WHERE chePai = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1,parkingSpace.getChePai());
        ResultSet res = preparedStatement.executeQuery();
        if(res.next()){
            return true;
        }else {
            return false;
        }
    }

}
