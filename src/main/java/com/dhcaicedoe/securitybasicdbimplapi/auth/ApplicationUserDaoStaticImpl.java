package com.dhcaicedoe.securitybasicdbimplapi.auth;

import com.dhcaicedoe.securitybasicdbimplapi.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.dhcaicedoe.securitybasicdbimplapi.security.ApplicationUserRole.*;

@RequiredArgsConstructor
@Repository("applicationUserDaoStaticImpl")
public class ApplicationUserDaoStaticImpl implements ApplicationUserDao{

    private final PasswordEncoder passwordEncoder;

    @Override public Optional<ApplicationUser> findByUsername(String username) {
        return getAllApplicationUser()
                .stream()
                .filter(f -> f.getUsername().equals(username))
                .findFirst();
    }

    @Override public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }


    private List<ApplicationUser> getAllApplicationUser(){
        ApplicationUser applicationUser1 =
                ApplicationUser
                        .builder()
                        .username("daniel@correo.com").password(passwordEncoder.encode("password"))
                        .grantedAuthorities(USER.getAuthorities())
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isCredentialsNonExpired(true)
                        .isEnabled(true)
                        .build();

        ApplicationUser applicationUser2 =
                ApplicationUser
                        .builder()
                        .username("elias@correo.com").password(passwordEncoder.encode("password"))
                        .grantedAuthorities(ADMIN.getAuthorities())
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isCredentialsNonExpired(true)
                        .isEnabled(true)
                        .build();
        return Arrays.asList(applicationUser1, applicationUser2);
    }
}
