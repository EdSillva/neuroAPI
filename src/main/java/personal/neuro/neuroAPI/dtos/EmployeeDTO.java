package personal.neuro.neuroAPI.dtos;

import lombok.*;
import java.util.List;

import java.io.Serializable;

import personal.neuro.neuroAPI.entities.Certification;
import personal.neuro.neuroAPI.entities.Employee;
import personal.neuro.neuroAPI.entities.TechnicalSkill;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable {
    private String name;
    private String email;
    private int experience;
    private int matricula;
    private String linkedin;
    private String phone;
    private List<TechnicalSkillDTO> technicalSkills;
    private List<CertificationDTO> certifications;

    public static EmployeeDTO fromEntity(personal.neuro.neuroAPI.entities.Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setExperience(employee.getExperience());
        employeeDTO.setMatricula(employee.getMatricula());
        employeeDTO.setLinkedin(employee.getLinkedin());
        employeeDTO.setPhone(employee.getPhone());
        employeeDTO.setTechnicalSkills(TechnicalSkillDTO.fromEntities(employee.getTechnicalSkills()));
        employeeDTO.setCertifications(CertificationDTO.fromEntities(employee.getCertifications()));
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

        List<TechnicalSkill> technicalSkills = TechnicalSkillDTO.toEntites(this.getTechnicalSkills());
        for (TechnicalSkill skill : technicalSkills) {
            skill.setEmployee(employee); // Vincular o Employee
        }
        employee.setTechnicalSkills(technicalSkills);

        List<Certification> certifications = CertificationDTO.toEntites(this.getCertifications());
        for (Certification certification : certifications) {
            certification.setEmployee(employee); // Vincular o Employee
        }
        employee.setCertifications(certifications);
        return employee;
    }
}
