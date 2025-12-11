package com.bookstore.auth;



import com.bookstore.user.*;
import com.bookstore.user.RoleRepository;
import com.bookstore.user.User;
import com.bookstore.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest req) {

        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already used");
        }

        User user = new User();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        // default = CLIENT
        Role clientRole = roleRepository
                .findByName(RoleType.ROLE_CLIENT)
                .orElseGet(() -> roleRepository.save(new Role(RoleType.ROLE_CLIENT)));

        user.addRole(clientRole);

        userRepository.save(user);
    }
}
