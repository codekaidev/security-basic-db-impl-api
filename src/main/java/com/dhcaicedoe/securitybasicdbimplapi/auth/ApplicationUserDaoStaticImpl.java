package com.dhcaicedoe.securitybasicdbimplapi.auth;

import com.dhcaicedoe.securitybasicdbimplapi.privilege.Privilege;
import com.dhcaicedoe.securitybasicdbimplapi.role.Role;
import com.dhcaicedoe.securitybasicdbimplapi.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Static implementation of the methods defined in the ApplicationUserDao interface
 *
 * @author Daniel Caicedo
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Repository("applicationUserDaoStaticImpl")
public class ApplicationUserDaoStaticImpl implements ApplicationUserDao{

    private final PasswordEncoder passwordEncoder;

    @Override public Optional<User> findByUsername(String username) {
        return getAllApplicationUser()
                .stream()
                .filter(f -> f.getUsername().equals(username))
                .findFirst();
    }

    @Override public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }


    private List<User> getAllApplicationUser(){
        Privilege readPrivilege = Privilege
                .builder()
                .name("PRIVILEGE_READ_DEMO")
                .build();
        Privilege writePrivilege = Privilege
                .builder()
                .name("PRIVILEGE_WRITE_DEMO")
                .build();

        Role adminRole = Role
                .builder()
                .name("ROLE_ADMIN")
                .privileges(Arrays.asList(readPrivilege, writePrivilege))
                .build();
        Role userRole = Role
                .builder()
                .name("ROLE_USER")
                .privileges(Collections.singletonList(readPrivilege))
                .build();

        User user1 = User
                .builder()
                .firstName("Juan")
                .lastName("Lopez")
                .email("jlopez@correo.com")
                .username("jlopez")
                .password(passwordEncoder.encode("password"))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .roles(Collections.singletonList(adminRole))
                .build();

        User user2 = User
                .builder()
                .firstName("Pedro")
                .lastName("Torres")
                .email("ptorres@correo.com")
                .username("ptorres")
                .password(passwordEncoder.encode("password"))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .roles(Collections.singletonList(userRole))
                .build();

        User user3 = User
                .builder()
                .firstName("Pablo")
                .lastName("Gomez")
                .email("pgomez@correo.com")
                .username("pgomez")
                .password(passwordEncoder.encode("password"))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .roles(Arrays.asList(adminRole, userRole))
                .build();
        return Arrays.asList(user1, user2, user3);
    }
}
