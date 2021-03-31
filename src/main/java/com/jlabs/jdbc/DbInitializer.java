package com.jlabs.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbInitializer {

    private static final String[] CREATE_DATABASE_STATEMENTS = {
            "CREATE TABLE TB_ROLE(ID_ROLE INT AUTO_INCREMENT(1, 1), NO_ROLE VARCHAR(255))"
    };

    private static final String[] TRUNCATE_DATABASE_STATEMENTS = {
            "TRUNCATE TABLE TB_ROLE"
    };

    private Connection connection;

    public DbInitializer(Connection connection) {
        this.connection = connection;
    }

    public void initialize() throws SQLException {
        for(String sqlStatement : CREATE_DATABASE_STATEMENTS) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.executeUpdate();
            ConnectionManager.close( statement );
        }
    }

    public void truncate() throws SQLException {
        for(String sqlStatement : TRUNCATE_DATABASE_STATEMENTS) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.executeUpdate();
            ConnectionManager.close( statement );
        }
    }

}
