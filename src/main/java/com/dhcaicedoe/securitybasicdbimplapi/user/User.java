package com.dhcaicedoe.securitybasicdbimplapi.user;

import com.dhcaicedoe.securitybasicdbimplapi.privilege.Privilege;
import com.dhcaicedoe.securitybasicdbimplapi.role.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Model or entity that maps the database data to the user table
 *
 * @author Daniel Caicedo
 * @since 1.0.0
 */
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_email_unique",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "user_username_unique",
                        columnNames = "username"
                )
        }
)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_account_non_expired", length = 1)
    private boolean isAccountNonExpired;

    @Column(name = "is_account_non_locked", length = 1)
    private boolean isAccountNonLocked;

    @Column(name = "is_credentials_non_expired", length = 1)
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enabled", length = 1)
    private boolean isEnabled;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_usersroles_user")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_usersroles_role")
            )
    )
    private List<Role> roles;

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(getPrivileges(this.roles));
    }


    @Override public String getUsername() {
        return email;
    }

    @Override public String getPassword() {
        return password;
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

    //
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

    @Override public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAccountNonExpired=" + isAccountNonExpired +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
