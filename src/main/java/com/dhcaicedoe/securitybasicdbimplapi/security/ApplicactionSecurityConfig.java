package com.dhcaicedoe.securitybasicdbimplapi.security;

import com.dhcaicedoe.securitybasicdbimplapi.auth.ApplicationUserService;
import com.dhcaicedoe.securitybasicdbimplapi.shared.advice.exception.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Allows you to configure the security of the application
 *
 * @author Daniel Caicedo
 * @version 1.0, 06/04/2023
 */

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class ApplicactionSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;


    private final ApplicationUserService applicationUserService;


    /**
     * <p>
     * Configure request security
     * </p>
     * <ul>
     *     <li>Disable crf.</li>
     *     <li>Allow unrestricted requests to authenticating endpoints.</li>
     *     <li>The authentication type is Basic.</li>
     * </ul>
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/auth/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/**/demo/user").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/**/demo/admin").hasAnyAuthority("PRIVILEGE_WRITE_DEMO")
                .antMatchers(HttpMethod.GET, "/api/**/demo/admin").hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    /**
     * Bean for handling exceptions for access denied
     *
     * @return custom implementation of AccessDeniedHandler for handling access denied errors
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

}
