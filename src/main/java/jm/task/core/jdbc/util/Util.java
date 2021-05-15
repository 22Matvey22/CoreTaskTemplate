package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:mysql://localhost/userdb";
    private static final String password = "1234";
    private static final String username = "root";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}
