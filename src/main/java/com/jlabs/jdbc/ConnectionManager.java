package com.jlabs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection getConnection() throws SQLException {
        return DriverManager.
                getConnection("jdbc:h2:mem:lab-jdbc;DB_CLOSE_DELAY=-1", "sa", "");
    }

    public static void close(AutoCloseable ... resources) {
        for(AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
