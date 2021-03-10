package dao;

import database.DbUtil;
import database.UncheckedFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UncheckedFileDao {

    //建表语句
    public void buildUUncheckedFile(){
        //语句
        String sql = "Create table UncheckedFile ( account INT NOT NULL , targetPath VARCHAR(50) NOT NULL," +
                     "presentPath VARCHAR(50) NOT NULL , type VARCHAR(50) NOT NULL)" ;

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

    //添加未验证文件
    public void addUncheckedFile(UncheckedFile file) {
        //语句
        String sql = "INSERT INTO UncheckedFile (account, targetPath, presentPath, type)" +
                "values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            //开启链接
            conn = DbUtil.getConnection();
            //利用对象给语句赋值
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, file.getAccount());
            ptmt.setString(2, file.getTargetPath());
            ptmt.setString(3, file.getPresentPath());
            ptmt.setString(4,file.getType());
            //执行
            ptmt.execute();
            System.out.println("-------User: NO."+file.getPresentPath()+"-------\nAddition is finished.");
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

    //删除语句--删除文件夹路径的文件--在数据中删除不符合要求的文件
    public void delUncheckedFile(String presentPath)throws SQLException {
        //语句
        String sql = "delete from UncheckedFile where presentPath=?";
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            //开启链接
            conn = DbUtil.getConnection();
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, presentPath);
            //执行
            ptmt.execute();
            System.out.println("-------" + presentPath + "-------\nDeletion is finished.");
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

    //删除语句--整条删除--根据账号
    public void delUncheckedFile(int account)throws SQLException {
        //语句
        String sql = "delete from UncheckedFile where account=?";
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            //开启链接
            conn = DbUtil.getConnection();
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, account);
            //执行
            ptmt.execute();
            System.out.println("-------" + account + "-------\nDeletion is finished.");
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

    //查询语句-->整条查询-->查询未验证文件
    public List<UncheckedFile> findUncheckedFile() {
        //语句
        String sql = "select * from UncheckedFile";
        Connection conn = null;
        PreparedStatement ptmt = null;
        //用结果集来存储信息
        ResultSet rs = null;
        List<UncheckedFile> result = new ArrayList<>();
        //创建一个集合对象用来存放查询到的数据
        try {
            //开启链接
            conn = DbUtil.getConnection();
            ptmt = conn.prepareStatement(sql);
            rs = (ResultSet) ptmt.executeQuery();
            while (rs.next()) {
                UncheckedFile file = new UncheckedFile();
                //给要放入结果集的对象赋值
                file.setAccount(rs.getInt("account"));
                file.setPresentPath(rs.getString("presentPath"));
                file.setTargetPath(rs.getString("targetPath"));
                file.setType(rs.getString("password"));
                result.add(file);
            }
            //将对象放到集合中
            //打印结果
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

    //查询语句-->整条查询
    public UncheckedFile findUncheckedFile(String presentPath) {
        //语句
        String sql = "select * from UncheckedFile where presentPath = ?";
        Connection conn = null;
        PreparedStatement ptmt = null;
        //用结果集来存储信息
        ResultSet rs = null;
        List<UncheckedFile> result = new ArrayList<>();
        UncheckedFile file = new UncheckedFile();
        //创建一个集合对象用来存放查询到的数据
        try {
            //开启链接
            conn = DbUtil.getConnection();
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, presentPath);
            rs = (ResultSet) ptmt.executeQuery();
            if (rs.next()) {
                //给要放入结果集的对象赋值
                file.setAccount(rs.getInt("account"));
                file.setPresentPath(rs.getString("presentPath"));
                file.setTargetPath(rs.getString("targetPath"));
                file.setType(rs.getString("password"));

            }
            else {
                return null;
            }
            //将对象放到集合中
            //打印结果
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            DbUtil.close(ptmt);
            DbUtil.close(conn);
        }
        return file;
    }






}
