package personal.neuro.neuroAPI.dtos;

import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import personal.neuro.neuroAPI.entities.Certification;
import personal.neuro.neuroAPI.entities.Employee;
import personal.neuro.neuroAPI.entities.TechnicalSkill;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL) // Ignorar quando for null
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // Apenas leitura
    private Long id;

    private String name;
    private String email;
    private int experience;
    private int matricula;
    private String linkedin;
    private String phone;
    private List<TechnicalSkillDTO> technicalSkills;
    private List<CertificationDTO> certifications;

    private Long projectLeaderId; // ID do projeto liderado

    public static EmployeeDTO fromEntity(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setExperience(employee.getExperience());
        employeeDTO.setMatricula(employee.getMatricula());
        employeeDTO.setLinkedin(employee.getLinkedin());
        employeeDTO.setPhone(employee.getPhone());

        employeeDTO.setTechnicalSkills(TechnicalSkillDTO.fromEntities(employee.getTechnicalSkills()));
        employeeDTO.setCertifications(CertificationDTO.fromEntities(employee.getCertifications()));
        employeeDTO
                .setProjectLeaderId(employee.getProjectLeader() != null ? employee.getProjectLeader().getId() : null);
        return employeeDTO;
    }

    public Employee toEntity() {
        Employee employee = new Employee();
        employee.setName(this.getName());
        employee.setEmail(this.getEmail());
        employee.setExperience(this.getExperience());
        employee.setMatricula(this.getMatricula());
        employee.setLinkedin(this.getLinkedin());
        employee.setPhone(this.getPhone());

        List<TechnicalSkill> technicalSkills = TechnicalSkillDTO.toEntities(this.getTechnicalSkills());
        for (TechnicalSkill skill : technicalSkills) {
            skill.setEmployee(employee); // Vincular o Employee
        }
        employee.setTechnicalSkills(technicalSkills);

        List<Certification> certifications = CertificationDTO.toEntities(this.getCertifications());
        for (Certification certification : certifications) {
            certification.setEmployee(employee); // Vincular o Employee
        }
        employee.setCertifications(certifications);
        return employee;
    }
}
