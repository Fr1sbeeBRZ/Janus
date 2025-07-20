package fr1sbee.dev.janus.web.database.repository;

import fr1sbee.dev.janus.web.database.entities.ProjectRole;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRoleRepository extends CrudRepository<ProjectRole,Long> {

}
