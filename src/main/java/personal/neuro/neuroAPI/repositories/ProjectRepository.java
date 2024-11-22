package personal.neuro.neuroAPI.repositories;

import java.util.List;
import personal.neuro.neuroAPI.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByNameContaining(String partName);

    List<Project> findByNameContainingIgnoreCase(String name);
}
