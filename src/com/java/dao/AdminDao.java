package com.java.dao;

import com.java.model.Admin;
import com.java.model.PageBean;
import com.java.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    /*
    *管理员登陆验证
     */
    public Admin login(Connection con, Admin admin) throws Exception{
        Admin resultAdmin = null;
        String sql="SELECT * FROM adminer WHERE admin_username=? and admin_pass=?";
        PreparedStatement pre=con.prepareStatement(sql);
        pre.setString(1, admin.getAdminUserName());
        pre.setString(2, admin.getAdminPass());
        ResultSet rs=pre.executeQuery();
        if(rs.next()){
            resultAdmin = new Admin();
            resultAdmin.setAdminUserName(rs.getString("admin_username"));
            resultAdmin.setAdminPass(rs.getString("admin_pass"));
        }
        return resultAdmin;
    }

    /*
    查找所有管理员信息
     */
    public Admin selectAllAdmin(Connection con,Admin admin) throws Exception{
        Admin resultAdmin = null;
        String sql = "SELECT * FROM adminer";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet res = pre.executeQuery();
        if(res.next()){
            resultAdmin = new Admin();
            resultAdmin.setAdminId(res.getInt("admin_id"));
            resultAdmin.setAdminUserName(res.getString("admin_username"));
            resultAdmin.setAdminPass(res.getString("admin_pass"));
            resultAdmin.setAdminName(res.getString("admin_name"));
        }
        return resultAdmin;
    }
    /*将管理员信息输入到列表中*/
    public ResultSet adminList(Connection con, PageBean pageBean, Admin admin)throws Exception{
        StringBuffer sb=new StringBuffer("select * from adminer");

        System.out.println("---"+admin.getAdminUserName()+"能否查询到");
        if(  StringUtil.isNotEmpty(admin.getAdminName())){
            sb.append(" and admin_name like '%"+admin.getAdminName()+"%'");
        }
        if(StringUtil.isNotEmpty(admin.getAdminPass())){
            sb.append(" and admin_pass ='"+admin.getAdminPass()+"'");
        }
        if(StringUtil.isNotEmpty(admin.getAdminUserName())){
            sb.append(" and admin_username like '%"+admin.getAdminUserName()+"%'");
        }
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        ResultSet rs = pstmt.executeQuery();
        return pstmt.executeQuery();
    }

    /*输出管理员人数*/
    public int adminCount(Connection con,Admin admin)throws Exception{
        StringBuffer sb=new StringBuffer("select count(*) as total from adminer");
        if(StringUtil.isNotEmpty(admin.getAdminName())){
            sb.append(" and admin_name like '%"+admin.getAdminName()+"%'");
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }
    }

    /*添加管理员信息*/
    public int addAdmin(Connection con, Admin admin) throws SQLException {
        String sql = "INSERT INTO adminer(admin_username,admin_pass,admin_name) values(?,?,?)";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,admin.getAdminUserName());
        pre.setString(2,admin.getAdminPass());
        pre.setString(3,admin.getAdminName());
        return  pre.executeUpdate();
    }

    /*修改管理员信息*/
    public  int adminModify(Connection con, Admin admin) throws  Exception{
        String sql = "UPDATE adminer set admin_username=?,admin_pass=?,admin_name=? WHERE admin_id=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,admin.getAdminUserName());
        pre.setString(2,admin.getAdminPass());
        pre.setString(3,admin.getAdminName());
        pre.setInt(4,admin.getAdminId());
        return pre.executeUpdate();
    }

    /*删除管理员信息*/
    public int adminDelete(Connection con,String delIds) throws Exception{
        String sql = "DELETE FROM adminer WHERE admin_id in ("+delIds+")";
        PreparedStatement pre = con.prepareStatement(sql);
        System.out.println(sql);
        return pre.executeUpdate();
    }
}
