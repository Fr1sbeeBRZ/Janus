package fr1sbee.dev.janus.authentication.login;

import fr1sbee.dev.janus.web.database.entities.Authority;
import fr1sbee.dev.janus.web.database.entities.JanusUser;
import fr1sbee.dev.janus.web.database.entities.Role;
import fr1sbee.dev.janus.web.database.entities.UserRole;
import fr1sbee.dev.janus.web.database.repository.RoleRepository;
import fr1sbee.dev.janus.web.database.repository.UserRepository;
import fr1sbee.dev.janus.web.database.repository.UserRoleRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public CustomUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JanusUser> user = userRepository.findByUsername(username);

        return user.map(this::toCustomUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }


    private CustomUserDetails toCustomUserDetails(final JanusUser user) {

        Set<Role> userRoles = new HashSet<>(userRoleRepository.getUserRoles(user));

        Profile profile = new Profile(user.getUsername() , userRoles);


        return new CustomUserDetails(profile , user.getPasswordHash() , getAuthorities(profile.getAuthorities()));
    }

    private List<SimpleGrantedAuthority> getAuthorities(Set<Authority> authoritySet) {
        return authoritySet.stream()
                .map(Authority::getCode)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
