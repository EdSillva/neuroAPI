package personal.neuro.neuroAPI.dtos;

import java.io.Serializable;

import lombok.*;
import personal.neuro.neuroAPI.entities.Project;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO implements Serializable {
    private int numProjet;
    private String name;
    private String description;
    private String status;
    private Long leaderId;

    public static ProjectDTO fromEntity(personal.neuro.neuroAPI.entities.Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setNumProjet(project.getNumProjet());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setStatus(project.getStatus());
        projectDTO.setLeaderId(project.getLeader() != null ? project.getLeader().getId() : null);
        return projectDTO;
    }

    public Project toEntity() {
        Project project = new Project();
        project.setNumProjet(this.getNumProjet());
        project.setName(this.getName());
        project.setDescription(this.getDescription());
        project.setStatus(this.getStatus());
        return project;
    }
}
