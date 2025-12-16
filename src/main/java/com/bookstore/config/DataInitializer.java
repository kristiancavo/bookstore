package com.bookstore.config;

import com.bookstore.user.Role;
import com.bookstore.user.RoleRepository;
import com.bookstore.auth.RoleType;
import com.bookstore.user.User;
import com.bookstore.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder encoder
    ) {
        return args -> {

            Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                    .orElseGet(() -> roleRepository.save(new Role(RoleType.ROLE_ADMIN)));

            Role staffRole = roleRepository.findByName(RoleType.ROLE_STAFF)
                    .orElseGet(() -> roleRepository.save(new Role(RoleType.ROLE_STAFF)));

            Role clientRole = roleRepository.findByName(RoleType.ROLE_CLIENT)
                    .orElseGet(() -> roleRepository.save(new Role(RoleType.ROLE_CLIENT)));

            if (userRepository.findByEmail("admin@test.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@test.com");
                admin.setFullName("Admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setActive(true);
                admin.setRoles(Set.of(adminRole));

                userRepository.save(admin);
            }
        };
    }
}