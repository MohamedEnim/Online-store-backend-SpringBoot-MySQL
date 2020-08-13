package com.appStore.appStore.authentication.authDomain;

import com.appStore.appStore.domain.Shipping;
import com.appStore.appStore.domain.ShoppingCart;
import com.appStore.appStore.domain.ShoppingCartProducts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;


import java.util.*;
import java.util.stream.Collectors;

@Entity
public class AuthenticationUser implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(unique = true)
    private String username;
    private String password;
    @Email(message = "Email should be valid")
    private String email;
    private String fname;
    private String lname;
    private boolean isEnabled;
    private String role;
    @OneToMany(targetEntity = AuthenticationPermission.class, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "userId")
    private Set<AuthenticationPermission> permissions;
    @OneToMany(targetEntity = ShoppingCart.class, cascade = {CascadeType.ALL})
    @JoinColumn(name="user_id", referencedColumnName = "userId")
    private List<ShoppingCart> shoppingCarts;
    @OneToMany(targetEntity = Shipping.class, cascade = {CascadeType.ALL})
    @JoinColumn(name="user_id", referencedColumnName = "userId")
    private List<Shipping> shippings;
    @OneToMany(targetEntity = ShoppingCartProducts.class, cascade = {CascadeType.ALL})
    @JoinColumn(name="user_id", referencedColumnName = "userId")
    private List<ShoppingCartProducts> shoppingCartProducts;

    public AuthenticationUser() {
    }

    public AuthenticationUser(Long userId,
                              String username,
                              String password,
                              @Email(message = "Email should be valid") String email,
                              String fname,
                              String lname,
                              boolean isEnabled,
                              String role,
                              Set<AuthenticationPermission> permissions,
                              List<ShoppingCart> shoppingCarts,
                              List<Shipping> shippings,
                              List<ShoppingCartProducts> shoppingCartProducts) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.isEnabled = isEnabled;
        this.role = role;
        this.permissions = permissions;
        this.shoppingCarts = shoppingCarts;
        this.shippings = shippings;
        this.shoppingCartProducts = shoppingCartProducts;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<AuthenticationPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<AuthenticationPermission> permissions) {
        this.permissions = permissions;
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public List<Shipping> getShippings() {
        return shippings;
    }

    public void setShippings(List<Shipping> shippings) {
        this.shippings = shippings;
    }

    public List<ShoppingCartProducts> getShoppingCartProducts() {
        return shoppingCartProducts;
    }

    public void setShoppingCartProducts(List<ShoppingCartProducts> shoppingCartProducts) {
        this.shoppingCartProducts = shoppingCartProducts;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.addAll(permissions.stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                    .collect(Collectors.toSet()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }



}
