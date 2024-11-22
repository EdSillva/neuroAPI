package personal.neuro.neuroAPI.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import personal.neuro.neuroAPI.dtos.ProjectDTO;
import personal.neuro.neuroAPI.entities.*;
import personal.neuro.neuroAPI.repositories.*;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    private EmployeeRepository employeeRepository;

    public ProjectService(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project newProject = projectDTO.toEntity();

        if (projectDTO.getLeaderId() != null) {
            Employee leader = employeeRepository.findById(projectDTO.getLeaderId())
                    .orElseThrow(() -> new RuntimeException("Leader not found with ID: " + projectDTO.getLeaderId()));
            newProject.setLeader(leader);
        }

        Project savedProject = projectRepository.save(newProject);
        return ProjectDTO.fromEntity(savedProject);
    }

    @Transactional
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream().map(ProjectDTO::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElse(null);

        return ProjectDTO.fromEntity(project);
    }

    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + id));

        // Atualizando dados do projeto
        existingProject.setName(projectDTO.getName());
        existingProject.setDescription(projectDTO.getDescription());
        existingProject.setInitialDate(projectDTO.getInitialDate());
        existingProject.setFinalDate(projectDTO.getFinalDate());
        existingProject.setStatus(projectDTO.getStatus());

        // Atualizando líder do projeto
        if (projectDTO.getLeaderId() != null) {
            Employee leader = employeeRepository.findById(projectDTO.getLeaderId())
                    .orElseThrow(() -> new RuntimeException("Leader not found with ID: " + projectDTO.getLeaderId()));
            existingProject.setLeader(leader);
        }

        Project updatedProject = projectRepository.save(existingProject);
        return ProjectDTO.fromEntity(updatedProject);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado com ID: " + id));

        project.setLeader(null); // Remove a referência ao líder
        projectRepository.save(project); // Salva o projeto sem o líder

        projectRepository.deleteById(id); // Agora exclui o projeto
    }
}
