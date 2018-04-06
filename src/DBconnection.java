/**
 * Created by JamesLee on 2018/4/5.
 */

import java.sql.*;

public class DBconnection {
    // Update your user and password info here!
    private static final String user = "xli76";
    private static final String password = "200203811";
    // Update your user info alone here
    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/" + user; // Using SERVICE_NAME

    private static Connection connection = null;

    private static Statement statement = null;

    public static void initialize() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        if (statement != null) {
            try {
                statement.close();
            } catch (Throwable whatever) {
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (Throwable whatever) {
            }
        }
    }

    public static Statement getStatement() {
        return statement;
    }

}
