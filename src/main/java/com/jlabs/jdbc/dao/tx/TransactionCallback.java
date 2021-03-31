package com.jlabs.jdbc.dao.tx;

import java.sql.Connection;
import java.sql.SQLException;

public interface TransactionCallback<E> {

    E runInTransaction(Connection connection) throws SQLException;

}
