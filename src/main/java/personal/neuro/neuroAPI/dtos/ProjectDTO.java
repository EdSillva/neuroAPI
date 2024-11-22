package personal.neuro.neuroAPI.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import personal.neuro.neuroAPI.entities.Project;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL) // Ignorar quando for null
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // Apenas leitura
    private Long id;

    private int numProject;
    private String name;
    private String description;
    private Date initialDate;
    private Date finalDate;
    private String status;
    private Long leaderId;
    private List<Long> teamEmployeeIds;

    public static ProjectDTO fromEntity(personal.neuro.neuroAPI.entities.Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setNumProject(project.getNumProject());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setInitialDate(project.getInitialDate());
        projectDTO.setFinalDate(project.getFinalDate());
        projectDTO.setStatus(project.getStatus());
        projectDTO.setLeaderId(project.getLeader() != null ? project.getLeader().getId() : null);
        return projectDTO;
    }

    public Project toEntity() {
        Project project = new Project();
        project.setNumProject(this.getNumProject());
        project.setName(this.getName());
        project.setDescription(this.getDescription());
        project.setInitialDate(this.getInitialDate());
        project.setFinalDate(this.getFinalDate());
        project.setStatus(this.getStatus());
        return project;
    }
}
