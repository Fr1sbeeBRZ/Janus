package fr1sbee.dev.janus.authentication;

import fr1sbee.dev.janus.authentication.login.Profile;

public interface AuthenticationFacade {
    String getCurrentUsername();

    Profile getCurrentProfile();
}
