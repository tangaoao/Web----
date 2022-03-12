package com.java.model;

public class Admin {
    public int adminId;//管理员编号
    public String adminUserName;//账户姓名
    public String adminPass;//账户密码
    public String adminName;//管理员真实姓名

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Admin(String adminUserName, String adminPass) {
        this.adminUserName = adminUserName;
        this.adminPass = adminPass;
    }

    public Admin(String adminUserName, String adminPass, String adminName) {
        this.adminUserName = adminUserName;
        this.adminPass = adminPass;
        this.adminName = adminName;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminUserName='" + adminUserName + '\'' +
                ", adminPass='" + adminPass + '\'' +
                ", adminName='" + adminName + '\'' +
                '}';
    }

    public Admin(){
        super();
    }
}
