package com.appStore.appStore.authentication.DO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class DBClient extends User {
    private Long userId;
    private String email;
    private String fname;
    private String lname;
    private boolean isEnabled;

    public DBClient(String username,
                    String password,
                    Collection<? extends GrantedAuthority> authorities,
                    Long userId,
                    String email,
                    String fname,
                    String lname,
                    boolean isEnabled) {
        super(username, password, authorities);
        this.userId = userId;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.isEnabled = isEnabled;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
