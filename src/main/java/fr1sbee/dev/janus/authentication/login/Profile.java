package fr1sbee.dev.janus.authentication.login;

import fr1sbee.dev.janus.web.database.entities.Authority;
import fr1sbee.dev.janus.web.database.entities.Role;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Profile implements Serializable {

    private String username;

    private Set<String> roles;

    private Set<Authority> authorities;

    public Profile(String username, Set<Role> roles) {
        Set<String> roleNames = new HashSet<>();
        Set<Authority> authorities = new HashSet<>();

        //Work with the roles for take the name of the role and create a set of all the authorities of the user

        roles.forEach(role -> {
            roleNames.add(role.getCode()); // Add the name of a role to the set of the user roles
            authorities.addAll(role.getAuthorities());
        });

        // Set the values to the object
        this.username = username;
        this.roles = roleNames;
        this.authorities = authorities;





    }
}
