package com.java.dao;

import com.java.model.Bill;
import com.java.model.PageBean;
import com.java.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BillDao {
    /*
    * 查找所有收费信息
    * */
    public ResultSet billList(Connection con, PageBean pageBean, Bill bill) throws Exception{
        StringBuffer sb = new StringBuffer("select * from cfei");
        if(StringUtil.isNotEmpty(bill.getBillHao())){
            sb.append(" and hao like '%"+bill.getBillHao()+"%'");
            System.out.println("查找车牌号");
        }
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
        }
        PreparedStatement pre = con.prepareStatement(sb.toString().replaceFirst("and","where"));
        return pre.executeQuery();
    }

    /*
    * 输出车费信息数量
    * */
    public int billCount(Connection con,Bill bill) throws Exception{
        StringBuffer sb = new StringBuffer("select count(*) as total from fei");
        PreparedStatement pre = con.prepareStatement(sb.toString().replaceFirst("and","where"));
        ResultSet resultSet = pre.executeQuery();
        if (resultSet.next()){
            return resultSet.getInt("total");
        }else {
            return 0;
        }
    }

    /*
    * 删除订单
    * */
    public int billDelete(Connection con,String delIds) throws Exception{
        String sql = "DELETE FROM cfei WHERE id in ("+delIds+")";
        PreparedStatement pre = con.prepareStatement(sql);
        return pre.executeUpdate();
    }

}
