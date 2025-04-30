package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionManager {

    private static final String URL_KEY = "jdbc:mysql://localhost:3307/MYSQL";
    private static final String USERNAME_KEY = "root";
    private static final String PASSWORD_KEY = "root";

    private ConnectionManager() {

    }

    static {
        loadDriver();
    }


    private static void loadDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection get() {
        try {
            return DriverManager.getConnection( URL_KEY, USERNAME_KEY, PASSWORD_KEY);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось открыть соединение", e);
        }
    }


}
