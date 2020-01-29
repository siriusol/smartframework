package org.smart4j.framework.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Ther
 */
public class DBUtil {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static ThreadLocal<Connection> connectionContainer = new ThreadLocal<>();

    public static Connection getConnection() {
        Connection connection = connectionContainer.get();
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            connectionContainer.set(connection);
        }
        return connection;
    }

    public static void closeConnection() {
        Connection connection = connectionContainer.get();
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionContainer.remove();
        }
    }
}
