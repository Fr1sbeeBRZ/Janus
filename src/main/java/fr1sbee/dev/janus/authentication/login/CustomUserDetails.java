package fr1sbee.dev.janus.authentication.login;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

    private final Profile profile;

    public CustomUserDetails(final Profile profile, final String password, final Collection<? extends GrantedAuthority> authorities) {
        this(profile, password, true, true, true, true, authorities);
    }

    public CustomUserDetails(final Profile profile, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities) {
        super(profile.username(), password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.profile = profile;
    }

    public Profile getProfile() {
        return this.profile;
    }
}
