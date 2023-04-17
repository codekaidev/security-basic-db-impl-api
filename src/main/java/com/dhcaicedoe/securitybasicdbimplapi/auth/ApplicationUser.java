package com.dhcaicedoe.securitybasicdbimplapi.auth;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@RequiredArgsConstructor
public class ApplicationUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<? extends GrantedAuthority> grantedAuthorities;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;


    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override public String getPassword() {
        return password;
    }

    @Override public String getUsername() {
        return username;
    }

    @Override public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override public boolean isEnabled() {
        return isEnabled;
    }
}
