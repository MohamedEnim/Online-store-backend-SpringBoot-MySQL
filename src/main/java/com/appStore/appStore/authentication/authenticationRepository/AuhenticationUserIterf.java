package com.appStore.appStore.authentication.authenticationRepository;

import com.appStore.appStore.authentication.authDomain.AuthenticationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuhenticationUserIterf extends JpaRepository<AuthenticationUser, Long> {

    Optional<AuthenticationUser> findByusername(String username);
}
