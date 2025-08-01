package fr1sbee.dev.janus.authentication.login;

import fr1sbee.dev.janus.exceptions.UserException;
import fr1sbee.dev.janus.utils.JanusUtils;
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

import java.text.MessageFormat;
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
        Optional<JanusUser> optionalUser = userRepository.findByUsername(username);

        try {
            JanusUser user = optionalUser.orElseThrow(() -> new UserException(MessageFormat.format("cannot find user by username {0}" , username)));
            return createCustomUserDetails(user);
        } catch (UserException userException) {
            throw new UsernameNotFoundException(userException.getMessage() ,userException);
        }
    }



    private CustomUserDetails createCustomUserDetails(final JanusUser user) throws UserException {

        Set<Role> userRoles = new HashSet<>(userRoleRepository.getUserRoles(user));

        if (userRoles.isEmpty()) {
            throw new UserException(MessageFormat.format("The user with id {0} is not assigned any role" , user.getId()));
        }

        Role activeRol = userRoles.stream().
                filter(Role::isActive)
                .findFirst()
                .orElseThrow(() -> new UserException(MessageFormat.format("The user with id {0} does not have any active roles" , user.getId())));

        Set<String> userRolesOrganized = JanusUtils.orderRolesForProfile(userRoles);

        Profile profile = new Profile(
                user.getUsername(),
                userRolesOrganized,
                activeRol.getAuthorities(),
                activeRol);

        return new CustomUserDetails(profile,
                user.getPasswordHash(),
                getAuthorities(profile.authorities()));
    }

    private List<SimpleGrantedAuthority> getAuthorities(Set<Authority> authoritySet) {
        return authoritySet.stream()
                .map(Authority::getCode)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
