package com.jlabs.jdbc.role;

import com.jlabs.jdbc.ConnectionManager;
import com.jlabs.jdbc.DbInitializer;
import com.jlabs.jdbc.dao.RoleDao;
import com.jlabs.jdbc.dao.RoleDaoJdbc;
import com.jlabs.jdbc.dao.tx.TransactionCallback;
import com.jlabs.jdbc.dao.tx.SimpleTransactionTemplate;
import com.jlabs.jdbc.model.Role;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class RoleDaoTest {

    @BeforeClass
    public static void setup() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        new DbInitializer(connection).initialize();
        connection.close();
    }

    @After
    public void tearDown() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        new DbInitializer(connection).truncate();
        connection.close();
    }

    @Test
    public void testInsert() throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        Role adminRole = new SimpleTransactionTemplate(connection).execute(new TransactionCallback<Role>() {
            @Override
            public Role runInTransaction(Connection connection) throws SQLException {
                RoleDao roleDao = new RoleDaoJdbc(connection);
                Role admin = roleDao.save(new Role("ADMIN"));
                return admin;
            }
        });

        ConnectionManager.close( connection );

    }
}
