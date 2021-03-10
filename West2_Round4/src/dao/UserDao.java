package dao;

import database.DbUtil;
import database.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    //建表语句
    public void buildUser(){
        //语句
        String sql = "Create table User ( userID INT PRIMARY KEY AUTO_INCREMENT  , administrator boolean," +
                "account int UNIQUE NOT NULL , password VARCHAR(50) NOT NULL, username VARCHAR(50) NOT NULL )" ;
        //Create table User ( userID INT PRIMARY KEY AUTO_INCREMENT, administrator boolean,account int UNIQUE NOT NULL , password VARCHAR(50) NOT NULL, username VARCHAR(50) NOT NULL );
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            //开启链接
            conn = DbUtil.getConnection();
            ptmt = conn.prepareStatement(sql);
            //执行
            ptmt.execute();
            System.out.println("------------------\nConstruction is finished.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭连接
            DbUtil.close(conn);
            DbUtil.close(ptmt);
        }
    }

    //添加记录
    public void addUser(User user) {
        //语句
        String sql = "INSERT INTO User (account, password, username, administrator)" +
                "values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            //开启链接
            conn = DbUtil.getConnection();
            //利用对象给语句赋值
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, user.getAccount());
            ptmt.setString(2, user.getPassword());
            ptmt.setString(3, user.getUsername());
            ptmt.setBoolean(4,user.getAdministrator());
            //执行
            ptmt.execute();
            System.out.println("-------User: NO."+user.getUserID()+"-------\nAddition is finished.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            DbUtil.close(ptmt);
            DbUtil.close(conn);
        }
    }

    //更新用户信息--更改昵称
    public void updateNickname(User user,String newName){
        //语句
        String sql = "UPDATE User SET username=? WHERE userID=?";

        Connection conn = null;
        PreparedStatement ptmt = null;

        try {
            //开启链接
            conn = DbUtil.getConnection();
            ptmt = conn.prepareStatement(sql);
            //利用对象给语句赋值
            ptmt.setString(1,newName);
            ptmt.setInt(2,user.getUserID());
            //执行语句
            ptmt.execute();
            System.out.println("-------User: NO."+user.getUserID()+"-------\nNickname is updated successfully.");
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            DbUtil.close(ptmt);
            DbUtil.close(conn);
        }
    }

    //更新用户信息--更改密码
    public void updatePassword(User user,String password){
        //语句
        String sql = "UPDATE User SET password=? WHERE userID=?";

        Connection conn = null;
        PreparedStatement ptmt = null;

        try {
            //开启链接
            conn = DbUtil.getConnection();
            ptmt = conn.prepareStatement(sql);
            //利用对象给语句赋值
            ptmt.setString(1,password);
            ptmt.setInt(2,user.getUserID());
            //执行语句
            ptmt.execute();
            System.out.println("-------User: NO."+user.getUserID()+"-------\nPassword is updated successfully.");
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            DbUtil.close(ptmt);
            DbUtil.close(conn);
        }
    }

    //删除语句--整条删除
    public void delUser(User user)throws SQLException {
        //语句
        String sql = "delete from User where userID=?";
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            //开启链接
            conn = DbUtil.getConnection();
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, user.getUserID());
            //执行
            ptmt.execute();
            System.out.println("-------User: NO." + user.getUserID() + "-------\nDeletion is finished.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            DbUtil.close(ptmt);
            DbUtil.close(conn);
        }
    }
        //查询语句-->帐号查询
        public User findAccount(int account) {
            //语句
            String sql = "select * from User where account=?";
            Connection conn = null;
            PreparedStatement ptmt = null;
            //用结果集来存储信息
            ResultSet rs = null;
            //创建一个集合对象用来存放查询到的数据
            User mid = new User();
            try {
                //开启链接
                conn = DbUtil.getConnection();
                ptmt = conn.prepareStatement(sql);
                ptmt.setLong(1, account);
                rs = (ResultSet) ptmt.executeQuery();
                if (rs.next()) {
                    //给要放入结果集的对象赋值
                    mid.setUserID(rs.getInt("userID"));
                    mid.setUsername(rs.getString("username"));
                    mid.setAccount(rs.getInt("account"));
                    mid.setPassword(rs.getString("password"));
                    mid.setAdministrator(rs.getBoolean("administrator"));
                }
                else {
                    return null;
                }
                //将对象放到集合中
                //打印结果
                mid.printInformation();
                System.out.println("-------Account:" + account + "-------\nSelection is finished");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                //关闭连接
                DbUtil.close(ptmt);
                DbUtil.close(conn);
            }
            return mid;
        }

     //验证帐号密码
    public boolean verifyUser(int account,String password){
        boolean result = false;
        User target = findAccount(account);
        if(target!=null){
            //成果找到帐号
            if(target.getPassword().equals(password)){
                //账号密码匹配
                result=true;
                System.out.println("Access allowed.");
            }
            else{
                //账号密码不匹配
                System.out.println("Password is not matched.");
            }
        }
        else {
            //未找到帐号
            System.out.println("Account does not exist.");
        }
        return result;
    }

    //搜索是否有相应昵称帐号
    public boolean findUsername(String username){
        boolean result = false;
        String sql = "select * from User where username=?";
        Connection conn = null;
        PreparedStatement ptmt = null;
        //用结果集来存储信息
        ResultSet rs = null;
        //创建一个集合对象用来存放查询到的数据
        User mid = new User();
        try {
            //开启链接
            conn = DbUtil.getConnection();
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, username);
            rs = (ResultSet) ptmt.executeQuery();
            if (rs.next()) {
             result = true;
            }
            else {
             result = false;
            }
            //将对象放到集合中
            //打印结果
            mid.printInformation();
            System.out.println("-------User:" + username + "-------\nSelection is finished");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            DbUtil.close(ptmt);
            DbUtil.close(conn);
        }
        return result;
    }



}
