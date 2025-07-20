package fr1sbee.dev.janus.web.database.repository;

import fr1sbee.dev.janus.web.database.entities.UserProject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProjectRepository  extends CrudRepository<UserProject,Long> {
}
