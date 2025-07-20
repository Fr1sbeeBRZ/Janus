package fr1sbee.dev.janus.web.database.repository;

import fr1sbee.dev.janus.web.database.entities.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
}
