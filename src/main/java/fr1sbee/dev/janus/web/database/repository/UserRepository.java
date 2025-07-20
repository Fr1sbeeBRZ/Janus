package fr1sbee.dev.janus.web.database.repository;

import fr1sbee.dev.janus.web.database.entities.JanusUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<JanusUser, Long> {


    Optional<JanusUser> findByUsername(String username);
}
