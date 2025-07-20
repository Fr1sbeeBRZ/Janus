package fr1sbee.dev.janus.web.database.repository;

import fr1sbee.dev.janus.web.database.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {

     Optional<Role> findRoleByCode(String code);

}
