package fr1sbee.dev.janus.web.database.repository;

import fr1sbee.dev.janus.web.database.entities.JanusUser;
import fr1sbee.dev.janus.web.database.entities.Role;
import fr1sbee.dev.janus.web.database.entities.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole,Long> {

    @Query("SELECT role FROM UserRole " +
            "WHERE active = true AND user = :user")
    Collection<Role> getUserRoles(@Param("user") JanusUser user);
}
