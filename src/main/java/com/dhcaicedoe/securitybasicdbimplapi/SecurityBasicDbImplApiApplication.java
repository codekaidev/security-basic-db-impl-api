package com.dhcaicedoe.securitybasicdbimplapi;

import com.dhcaicedoe.securitybasicdbimplapi.privilege.Privilege;
import com.dhcaicedoe.securitybasicdbimplapi.privilege.PrivilegeService;
import com.dhcaicedoe.securitybasicdbimplapi.role.Role;
import com.dhcaicedoe.securitybasicdbimplapi.role.RoleService;
import com.dhcaicedoe.securitybasicdbimplapi.user.User;
import com.dhcaicedoe.securitybasicdbimplapi.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class SecurityBasicDbImplApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityBasicDbImplApiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(RoleService roleService,
										PrivilegeService privilegeService,
										UserService userService,
										PasswordEncoder passwordEncoder) {
		return args -> {
			Privilege readPrivilege = Privilege
					.builder()
					.name("PRIVILEGE_READ_DEMO")
					.build();
			Privilege writePrivilege = Privilege
					.builder()
					.name("PRIVILEGE_WRITE_DEMO")
					.build();

			readPrivilege = privilegeService.createPrivilege(readPrivilege);
			writePrivilege = privilegeService.createPrivilege(writePrivilege);

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

			adminRole = roleService.createRole(adminRole);
			userRole = roleService.createRole(userRole);

			User user1 = User
					.builder()
					.firstName("Juan")
					.lastName("Lopez")
					.email("jlopez@correo.com")
					.password(passwordEncoder.encode("password"))
					.roles(Collections.singletonList(adminRole))
					.build();

			User user2 = User
					.builder()
					.firstName("Pedro")
					.lastName("Torres")
					.email("ptorres@correo.com")
					.password(passwordEncoder.encode("password"))
					.roles(Collections.singletonList(userRole))
					.build();

			User user3 = User
					.builder()
					.firstName("Pablo")
					.lastName("Gomez")
					.email("pgomez@correo.com")
					.password(passwordEncoder.encode("password"))
					.roles(Arrays.asList(adminRole, userRole))
					.build();

			userService.createUser(user1);
			userService.createUser(user2);
			userService.createUser(user3);

		};
	}

}
