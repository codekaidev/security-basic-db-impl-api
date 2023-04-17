package com.dhcaicedoe.securitybasicdbimplapi.auth;

import com.dhcaicedoe.securitybasicdbimplapi.privilege.Privilege;
import com.dhcaicedoe.securitybasicdbimplapi.role.Role;
import com.dhcaicedoe.securitybasicdbimplapi.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Daniel Caicedo
 * @since 1.0.0
 */
@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserDao applicationUserDao;

    @Autowired
    ApplicationUserService(@Qualifier("applicationUserDaoImplDataBaseRepo") ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userApp = applicationUserDao.findByEmail(username);
        userApp.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        org.springframework.security.core.userdetails.User userDetail =
                (org.springframework.security.core.userdetails.User) org.springframework.security.core.userdetails.User
                        .builder()
                        .username(userApp.get().getEmail())
                        .password(userApp.get().getPassword())
                        .disabled(false)
                        .accountExpired(false)
                        .accountLocked(false)
                        .credentialsExpired(false)
                        .authorities(getAuthorities(userApp.get().getRoles()))
                        .build();

        return userDetail;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        roles.stream().map(role -> {
                    // Instantiate the array that stores all the authorities
                    List<String> authorities = new ArrayList<>();
                    // Add the privileges or permissions to the array
                    role.getPrivileges().stream()
                            .map(Privilege::getName)
                            .forEach(authorities::add);
                    // Also, the role is added to the authorities array.
                    authorities.add(role.getName());
                    return authorities;
                })
                .flatMap(Collection::stream)
                .forEach(privileges::add);

        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
