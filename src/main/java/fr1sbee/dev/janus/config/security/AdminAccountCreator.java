package fr1sbee.dev.janus.config.security;


import fr1sbee.dev.janus.web.database.entities.JanusUser;
import fr1sbee.dev.janus.web.database.entities.Role;
import fr1sbee.dev.janus.web.database.entities.UserRole;
import fr1sbee.dev.janus.web.database.repository.RoleRepository;
import fr1sbee.dev.janus.web.database.repository.UserRepository;
import fr1sbee.dev.janus.web.database.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class AdminAccountCreator {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminAccountCreator.class);
    private static final String DEFAULT_USERNAME = "Jadmin";
    private static final String DEFAULT_PASSWORD = "admin";
    private static final String DEFAULT_ROLE = "JANUS_ADMIN";

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public AdminAccountCreator(
            final PasswordEncoder passwordEncoder,
            final RoleRepository roleRepository,
            final UserRepository userRepository,
            final UserRoleRepository userRoleRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @PostConstruct
    public void init() {
        Optional<JanusUser> adminAccount = userRepository.findByUsername(DEFAULT_USERNAME);

        if (adminAccount.isPresent()) {
            LOGGER.info("Admin account found");
            return;
        }

        createAdmin();
    }

    private void createAdmin() {
        LOGGER.info("Creating default admin account...");

        // Create the user
        JanusUser admin = new JanusUser();
        admin.setUsername(DEFAULT_USERNAME);
        admin.setPasswordHash(passwordEncoder.encode(DEFAULT_PASSWORD));
        admin.setActive(true);
        admin.setCreationUser("System");
        admin.setLastModificationUser("System");
        admin.setCreationDate(LocalDate.now());
        admin.setLastModificationDate(LocalDate.now());

        admin = userRepository.save(admin);

        //Get the admin role
        Optional<Role> role =  roleRepository.findRoleByCode(DEFAULT_ROLE);

        if(role.isPresent()){
            UserRole userRole = new UserRole();
            userRole.setRole(role.get());
            userRole.setUser(admin);
            userRole.setActive(true);

            userRoleRepository.save(userRole);

            LOGGER.info("Created default admin account.");
        } else {
            throw new RuntimeException("Role not found");
        }



    }
}

