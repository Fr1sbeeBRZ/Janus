package fr1sbee.dev.janus.authentication;

import fr1sbee.dev.janus.authentication.login.CustomUserDetails;
import fr1sbee.dev.janus.authentication.login.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthenticationFacade implements AuthenticationFacade {
    @Override
    public String getCurrentUsername() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return customUserDetails.getUsername();
    }

    @Override
    public Profile getCurrentProfile() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return customUserDetails.getProfile();
    }
}
