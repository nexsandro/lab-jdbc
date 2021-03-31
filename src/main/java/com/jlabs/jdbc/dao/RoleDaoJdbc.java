package com.jlabs.jdbc.dao;

import com.jlabs.jdbc.ConnectionManager;
import com.jlabs.jdbc.model.Role;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class RoleDaoJdbc implements com.jlabs.jdbc.dao.RoleDao {

    private static final String INSERT_STATEMENT = "insert into TB_ROLE (NO_ROLE) values (?)";

    private static final String UPDATE_STATEMENT = "update TB_ROLE set NO_ROLE=? where ID_ROLE=?";

    private static final String DELETE_STATEMENT = "delete TB_ROLE where ID_ROLE=?";

    private static final String GET_STATEMENT = "select NO_ROLE from TB_ROLE where ID_ROLE=?";

    private static final String LIST_ALL_STATEMENT = "select ID_ROLE, NO_ROLE from TB_ROLE";

    private Connection connection;

    public RoleDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Role save(Role role) throws SQLException {

        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        try {
            // Prepare statement
            if (isInsert(role)) {
                statement = connection.prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            } else {
                statement = connection.prepareStatement(UPDATE_STATEMENT);
            }

            // Bind statement variables
            statement.setString(1, role.getName());
            if (!isInsert(role)) {
                statement.setInt(2, role.getId());
            }

            // Run statement
            int i = statement.executeUpdate();

            // Retrive generated key
            if (isInsert(role)) {
                generatedKeys = statement.getGeneratedKeys();
                setGeneratedKey(role, generatedKeys);
            }
        } finally {
            ConnectionManager.close( generatedKeys, statement );
        }

        return role;
    }

    @Override
    public Role get(Integer roleId) throws SQLException {

        Role role = null;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(GET_STATEMENT);

            statement.setInt(1, roleId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                role = new Role( roleId, resultSet.getString("NO_ROLE" ) );
            }
        } finally {
            ConnectionManager.close( resultSet, statement );
        }

        return role;
    }

    @Override
    public void delete(Integer roleId) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(DELETE_STATEMENT);

            statement.setInt(1, roleId);

            statement.executeUpdate();

        } finally {
            ConnectionManager.close( statement );
        }

    }

    @Override
    public Set<Role> listAll() throws SQLException {
        Set<Role> result = new HashSet<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(LIST_ALL_STATEMENT);
            resultSet = statement.executeQuery();

            while( resultSet.next() ) {
                result.add( new Role(resultSet.getInt("ID_ROLE"), resultSet.getString("NO_ROLE")));
            }
        } finally {
            ConnectionManager.close( resultSet, statement );
        }

        return result;
    }

    private void setGeneratedKey(Role role, ResultSet generatedKeys) throws SQLException {
        if (generatedKeys.next()) {
            role.setId( generatedKeys.getInt(1) );
        }
    }

    private boolean isInsert(Role role) {
        return role.getId() == null;
    }
}
