package com.appStore.appStore.authentication.authService;

import com.appStore.appStore.authentication.DO.DBClient;
import com.appStore.appStore.authentication.VO.ClientDB;
import com.appStore.appStore.authentication.authDomain.AuthenticationPermission;
import com.appStore.appStore.authentication.authDomain.AuthenticationUser;
import com.appStore.appStore.authentication.authenticationRepository.AuhenticationUserIterf;
import com.appStore.appStore.authentication.authenticationRepository.AuthenticationPermissionIterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthenticationUserService implements UserDetailsService {

    private AuhenticationUserIterf authUserRepositoryIterf;
    private AuthenticationPermissionIterf authenticationPermissionIterf;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationUserService(AuhenticationUserIterf authUserRepositoryIterf, AuthenticationPermissionIterf authenticationPermissionIterf, PasswordEncoder passwordEncoder) {
        this.authUserRepositoryIterf = authUserRepositoryIterf;
        this.authenticationPermissionIterf = authenticationPermissionIterf;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<AuthenticationUser> user = this.authUserRepositoryIterf.findByusername(s);

        UserDetails userDetails = new User(
                user.get().getUsername(),
                passwordEncoder.encode(user.get().getPassword()),
                user.get().isAccountNonExpired(),
                user.get().isAccountNonLocked(),
                user.get().isCredentialsNonExpired(),
                user.get().isEnabled(),
                user.get().getAuthorities()
        );
        return userDetails;
    }

    public DBClient returnUser(String username) throws UsernameNotFoundException{
        Optional<AuthenticationUser> user = this.authUserRepositoryIterf.findByusername(username);
        DBClient clientUser = new DBClient(
                user.get().getUsername(),
                passwordEncoder.encode(user.get().getPassword()),
                user.get().getAuthorities(),
                user.get().getUserId(),
                user.get().getEmail(),
                user.get().getFname(),
                user.get().getLname(),
                user.get().isEnabled()
        );

        return  clientUser;
    }

    public AuthenticationUser registerUser( ClientDB clientDB){

        AuthenticationUser authenticationUser = new AuthenticationUser();
        AuthenticationPermission authenticationPermission = new AuthenticationPermission();
        Set<AuthenticationPermission> authenticationPermissions = new HashSet<>();

        authenticationUser.setUsername(clientDB.getUsername());
        authenticationUser.setPassword(clientDB.getPassword());
        authenticationUser.setEmail(clientDB.getEmail());
        authenticationUser.setFname(clientDB.getFname());
        authenticationUser.setLname(clientDB.getLname());
        authenticationUser.setEnabled(clientDB.isEnabled());
        authenticationUser.setRole(clientDB.getRole());

            for(String permission: clientDB.getPermissions() ){
                authenticationPermission.setPermission(permission);
                authenticationPermissions.add(authenticationPermission);
            }

        authenticationUser.setPermissions(authenticationPermissions);
        authenticationUser = this.authUserRepositoryIterf.save(authenticationUser);

        return authenticationUser;
    }
}




