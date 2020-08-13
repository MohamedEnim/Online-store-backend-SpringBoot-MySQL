package com.appStore.appStore.authentication.authConfig;

import com.appStore.appStore.authentication.authService.AuthenticationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class AuthenticationUserConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private AuthenticationUserService authUserService;

    @Autowired
    public AuthenticationUserConfig(PasswordEncoder passwordEncoder,
                                    AuthenticationUserService authUserService) {
        this.passwordEncoder = passwordEncoder;
        this.authUserService = authUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(new PermissiveCorsConfigurationSource())
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/appstore/categories", "/authenticate/register", "/appstore/select/**", "/appstore/shipping/**").permitAll()
                .antMatchers("/appstore/addShoppingCart/**","/appstore/addProdToShoppingCart/**", "/appstore/deleteShoppingCartProduct/**").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/appstore/getShoppingCart/**").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/authenticate/**").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/appstore/product/**", "/appstore/postProduct/**").hasRole("ADMIN")
                .antMatchers("/appstore/orders/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/appstore/category").hasRole("ADMIN")
                .antMatchers("/appstore/deleteProduct/**").hasRole("ADMIN")
                .antMatchers("/appstore/my/orders/**", "/appstore/my/order/detail/**").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/appstore/deleteShoppingCart/**", "/appstore/existeCategory/**").hasAnyRole("CUSTOMER", "ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(authUserService);
        return provider;
    }

 /*   @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200/authenticate/mohamed"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept",
                "Authorization", "Access-Control-Allow-Credentials", "Access-Control-Allow-Headers", "Access-Control-Allow-Methods",
                "Access-Control-Allow-Origin", "Access-Control-Expose-Headers", "Access-Control-Max-Age",
                "Access-Control-Request-Headers", "Access-Control-Request-Method", "Age", "Allow", "Alternates",
                "Content-Range", "Content-Disposition", "Content-Description"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/

    private static class PermissiveCorsConfigurationSource implements CorsConfigurationSource {
        /**
         * Return a {@link CorsConfiguration} based on the incoming request.
         *
         * @param request
         * @return the associated {@link CorsConfiguration}, or {@code null} if none
         */
        @Override
        public CorsConfiguration getCorsConfiguration(final HttpServletRequest request) {
            final CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowCredentials(true);
            configuration.setAllowedHeaders(Collections.singletonList("*"));
            configuration.setAllowedMethods(Collections.singletonList("*"));
            configuration.setAllowedOrigins(Collections.singletonList("*"));
            return configuration;
        }

    }
}

