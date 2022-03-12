package com.java.dao;

import com.java.model.Admin;
import com.java.model.PageBean;
import com.java.model.User;
import com.java.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    /*
     *用户登陆验证
     */
    public User login(Connection con,User user) throws Exception{
        User resultUser = null;
        String sql = "SELECT * FROM userinfo WHERE email=? and pwd=?";
        PreparedStatement pre =con.prepareStatement(sql);
        pre.setString(1,user.getUserEmail());
        pre.setString(2,user.getUserPass());
        ResultSet res = pre.executeQuery();
        if(res.next()){
            resultUser = new User();
            resultUser.setUserEmail(res.getString("email"));
            resultUser.setUserPass(res.getString("pwd"));
            resultUser.setUserName(res.getString("name"));
            resultUser.setUserMoney(res.getString("jine"));
            resultUser.setUserAge(res.getString("age"));
            resultUser.setUserId(res.getInt("userId"));
        }
        return resultUser;
    }

     /*
     *添加用户
     */
    public User addUser(Connection con,User user) throws SQLException{
        User resultUser = null;
        String sql = "INSERT INTO userinfo(NAME,pwd,age,email) VALUES (?, ?, ?, ? );";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,user.getUserName());
        pre.setString(2,user.getUserPass());
        pre.setString(3,user.getUserAge());
        pre.setString(4,user.getUserEmail());
        pre.executeUpdate();
        return resultUser;
    }
    /*
    *查询用户是否存在
    */
    public boolean selsetUser(Connection con,User user) throws Exception{
        User resultUser = null;
        String sql = "SELECT * FROM userinfo WHERE email = ?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,user.getUserEmail());
        ResultSet res = pre.executeQuery();
        if(res.next()){
            return true;
        }
        return false;
    }

    /*
    * 输出用户信息
    */
    public ResultSet userList(Connection con, PageBean pageBean,User user) throws Exception{
        StringBuffer sb = new StringBuffer("select * from userinfo");
        if(  StringUtil.isNotEmpty(user.getUserName())){
            sb.append(" and name like '%"+user.getUserName()+"%'");
        }
        if(StringUtil.isNotEmpty(user.getUserPass())){
            sb.append(" and pwd like '%"+user.getUserPass()+"%'");
        }
        if(StringUtil.isNotEmpty(user.getUserAge())){
            sb.append(" and age like '%"+user.getUserAge()+"%'");
        }
        if(StringUtil.isNotEmpty(user.getUserEmail())){
            sb.append(" and email like '%"+user.getUserEmail()+"%'");
        }
        PreparedStatement pre = con.prepareStatement(sb.toString().replaceFirst("and","where"));
        ResultSet res = pre.executeQuery();
        return pre.executeQuery();
    }

    /*
    *输出用户人数
    */
    public int userCount(Connection con,User user) throws  Exception{
        StringBuffer sb = new StringBuffer("select count(*) as total from userinfo");
        if(StringUtil.isNotEmpty(user.getUserName())){
            sb.append(" and name like '%"+user.getUserName()+"%'");
        }
        PreparedStatement pre = con.prepareStatement(sb.toString().replaceFirst("and","where"));
        ResultSet res = pre.executeQuery();
        if(res.next()){
            return res.getInt("total");
        }else {
            return 0;
        }
    }

    /*
    *添加用户
    */
    public int addUsers(Connection con,User user) throws SQLException {
        UserDao userDao = new UserDao();
        String sql = "INSERT INTO userinfo(name,pwd,age,email) values(?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,user.getUserName());
        pre.setString(2,user.getUserPass());
        pre.setString(3,user.getUserAge());
        pre.setString(4,user.getUserEmail());
        System.out.println("添加用户信息");
        return pre.executeUpdate();

    }
    /*
    *修改用户信息
    */
    public int userModify(Connection con,User user) throws Exception{
        UserDao userDao = new UserDao();
        String sql = "UPDATE userinfo set name=?,pwd=?,age=?,email=? WHERE userId=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,user.getUserName());
        pre.setString(2,user.getUserPass());
        pre.setString(3,user.getUserAge());
        pre.setString(4,user.getUserEmail());
        pre.setString(5,Integer.toString(user.getUserId()));
        System.out.println("修改"+user.getUserId()+"的用户信息");
        return pre.executeUpdate();
    }

    /*
    *删除用户信息
    */
    public int userDelete(Connection con,String delIds) throws  Exception{
        String sql = "DELETE FROM userinfo WHERE userId in ("+delIds+")";
        PreparedStatement pre = con.prepareStatement(sql);
        return pre.executeUpdate();
    }

    /*
    * 修改账户余额
    */
    public int addUserMoney(Connection con,User user,String addMoney ) throws  Exception{
        String sql = "UPDATE userinfo SET jine =jine+ ("+addMoney+") WHERE userId=? ";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1,Integer.toString(user.getUserId()));
        return pre.executeUpdate();
    }
}
