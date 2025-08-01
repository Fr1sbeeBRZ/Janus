package fr1sbee.dev.janus.authentication.login;

import fr1sbee.dev.janus.web.database.entities.Authority;
import fr1sbee.dev.janus.web.database.entities.Role;
import lombok.Getter;

import java.io.Serializable;
import java.util.Set;


public record Profile(String username, Set<String> roles, Set<Authority> authorities,
                      Role activeRol) implements Serializable {

}
