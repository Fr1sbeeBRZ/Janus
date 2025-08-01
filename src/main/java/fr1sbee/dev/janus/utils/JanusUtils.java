package fr1sbee.dev.janus.utils;

import fr1sbee.dev.janus.web.database.entities.Role;

import java.util.*;

public class JanusUtils {

    /**
     * Orders the role codes of the user.
     *
     * @param roles A set of the user's roles.
     * @return A list of role codes where the active role comes first, followed by the others sorted alphabetically.
     */
    public static Set<String> orderRolesForProfile(Set<Role> roles){
        List<String> otherUserRoles = new ArrayList<>();
        Set<String> orderedRoles = new LinkedHashSet<>();

        for (Role role : roles) {
            String code = role.getCode();
            if (role.isActive()) {
                orderedRoles.add(code);
            } else {
                otherUserRoles.add(code);
            }
        }

        if(!otherUserRoles.isEmpty()){
            otherUserRoles.sort(String::compareTo);
            orderedRoles.addAll(otherUserRoles);
        }

        otherUserRoles.clear();
        roles.clear();
        return orderedRoles;
    }

}
