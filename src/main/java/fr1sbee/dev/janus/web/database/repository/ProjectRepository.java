package fr1sbee.dev.janus.web.database.repository;

import fr1sbee.dev.janus.web.database.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
}
