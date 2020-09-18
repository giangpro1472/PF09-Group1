package project.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost:3306/gms_project";
    private static String user = "root";
    private static String password = "giang1472";

    private DbUtil() {
        throw new IllegalStateException("Can't init DbUtil instance!");
    }

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
}