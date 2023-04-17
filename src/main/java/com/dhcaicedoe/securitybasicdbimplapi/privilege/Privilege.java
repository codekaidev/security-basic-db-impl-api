package com.dhcaicedoe.securitybasicdbimplapi.privilege;

import com.dhcaicedoe.securitybasicdbimplapi.role.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    private String name;

    @ManyToMany(
            mappedBy = "privileges"
    )
    private List<Role> roles;


}
