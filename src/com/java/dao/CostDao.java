package com.java.dao;

import com.java.model.Admin;
import com.java.model.Cost;
import com.java.model.PageBean;
import com.java.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CostDao {
    /*
    * 查询费用信息
    * */
    public ResultSet costList(Connection con, PageBean pageBean , Cost cost) throws SQLException {
        StringBuffer sb = new StringBuffer("select * from fei");

        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        ResultSet rs = pstmt.executeQuery();
        return pstmt.executeQuery();
    }

    /*
    * 输出信息数量
    * */
    public int costCount(Connection con, Cost cost)throws Exception{
        StringBuffer sb=new StringBuffer("select count(*) as total from fei");

        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }
    }

    /*
    * 修改收费金额
    * */
    public int costModify(Connection con,Cost cost,String newCost) throws Exception{
        String sql = "UPDATE fei set price = "+newCost+" WHERE id=?";
        PreparedStatement pre = con.prepareStatement(sql);
        System.out.println("ID为："+cost.getCostId()+"新增价格："+newCost);
        pre.setString(1,Integer.toString(cost.getCostId()));
        return pre.executeUpdate();
    }

}
