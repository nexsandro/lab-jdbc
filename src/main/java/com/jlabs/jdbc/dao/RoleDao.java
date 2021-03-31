package com.jlabs.jdbc.dao;

import com.jlabs.jdbc.model.Role;

import java.sql.SQLException;
import java.util.Set;

public interface RoleDao {

    Role save(Role role) throws SQLException;

    Role get(Integer roleId) throws SQLException;

    void delete(Integer roleId) throws SQLException;

    Set<Role> listAll() throws SQLException;
}
