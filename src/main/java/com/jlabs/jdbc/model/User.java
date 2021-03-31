package com.jlabs.jdbc.model;

import java.util.HashSet;
import java.util.Set;

public class User {

    private Integer id;

    private String login;

    private Set<Role> roles = new HashSet<>();

    private Set<UserGroup> groups = new HashSet<>();

    public User(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<UserGroup> groups) {
        this.groups = groups;
    }

    public void addRole(Role role) {
        this.roles.add( role );
    }

    public void addGroup(UserGroup group) {
        this.groups.add( group );
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", roles=" + roles +
                ", groups=" + groups +
                '}';
    }
}
