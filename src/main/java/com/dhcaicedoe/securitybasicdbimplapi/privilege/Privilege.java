package com.dhcaicedoe.securitybasicdbimplapi.privilege;

import com.dhcaicedoe.securitybasicdbimplapi.role.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Model or entity that maps the database data to the privilege table
 *
 * @author Daniel Caicedo
 * @since 1.0.0
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Privilege")
@Table(name = "privilege")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(
            mappedBy = "privileges"
    )
    private List<Role> roles;


}
