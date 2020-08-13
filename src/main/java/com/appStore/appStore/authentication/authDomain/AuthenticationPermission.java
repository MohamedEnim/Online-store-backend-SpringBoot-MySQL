package com.appStore.appStore.authentication.authDomain;

import javax.persistence.*;

@Entity
public class AuthenticationPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long permissionId;
    private String permission;

    public AuthenticationPermission() {
    }

    public AuthenticationPermission(Long permissionId, String permission) {
        this.permissionId = permissionId;
        this.permission = permission;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
