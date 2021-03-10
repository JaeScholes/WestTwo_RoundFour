package database;

import java.sql.*;

public class DbUtil {
    public static final String URL = "jdbc:mysql://localhost:3306/west2?allowPublicKeyRetrieval=true&useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    static Connection conn = null;

    //获取链接
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }
    //关闭连接
    public static void close(PreparedStatement pstmt){
        if(pstmt != null){						//避免出现空指针异常
            try{
                pstmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }

        }
    }

    public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    public static boolean validateTableExist(String tableName) throws SQLException {
        //定义一个变量标示
        DatabaseMetaData dmd = conn.getMetaData();
        boolean flag = true ;
        ResultSet rs = dmd.getTables(null, null, "TEST", null);
        try {
            if (rs.next()) flag=true;
        } catch (SQLException throwables) {
            flag = false;
            throwables.printStackTrace();
        }
        return flag;
    }


}
