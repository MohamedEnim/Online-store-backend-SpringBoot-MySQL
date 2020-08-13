package com.appStore.appStore.authentication.authenticationRepository;

import com.appStore.appStore.authentication.authDomain.AuthenticationPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationPermissionIterf extends JpaRepository<AuthenticationPermission, Long> {
}
