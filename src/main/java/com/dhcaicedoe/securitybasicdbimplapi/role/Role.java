package com.dhcaicedoe.securitybasicdbimplapi.role;

import com.dhcaicedoe.securitybasicdbimplapi.privilege.Privilege;
import com.dhcaicedoe.securitybasicdbimplapi.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Model or entity that maps the database data to the role table
 *
 * @author Daniel Caicedo
 * @since 1.0.0
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Role")
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(
            mappedBy = "roles"
    )
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private List<Privilege> privileges;

}
