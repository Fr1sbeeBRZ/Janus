package fr1sbee.dev.janus.authentication;

import fr1sbee.dev.janus.authentication.login.CustomUserDetails;
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
}
