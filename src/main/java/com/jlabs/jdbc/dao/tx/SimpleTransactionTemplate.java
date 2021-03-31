package com.jlabs.jdbc.dao.tx;

import java.sql.Connection;
import java.sql.SQLException;

public class SimpleTransactionTemplate {

    private Connection connection;

    public SimpleTransactionTemplate(Connection connection) {
        this.connection = connection;
    }

    public <E> E execute(TransactionCallback<E> transactionCallback) throws SQLException {

        E e = null;
        boolean autoCommitMode = connection.getAutoCommit();

        try {

            connection.setAutoCommit( false );

            e = transactionCallback.runInTransaction( connection );

            connection.commit();

        } catch ( SQLException ex ) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit( autoCommitMode );
        }

        return e;
    }

}
