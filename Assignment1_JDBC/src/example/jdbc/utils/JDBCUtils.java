package example.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtils {
    public static Connection getConnetion() throws Exception {
        String url = "jdbc:mysql://localhost:3306/Articles";
        String uid = "root"; 
        String pwd = "password"; 
        return DriverManager.getConnection(url, uid, pwd);
    }
}
