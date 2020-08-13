package com.appStore.appStore.authentication.VO;

import java.util.ArrayList;
import java.util.List;

public class ClientDB {
    private String username;
    private String password;
    private String fname;
    private String lname;
    private String email;
    private boolean isEnabled;
    private String role;
    private List<String> permissions = new ArrayList<String>();

    public ClientDB(String username,
                    String password,
                    String fname,
                    String lname,
                    String email,
                    boolean isEnabled,
                    String role,
                    List<String> permissions) {
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.isEnabled = isEnabled;
        this.role = role;
        this.permissions = permissions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}