package personal.neuro.neuroAPI.services;

import java.util.List;
import java.util.stream.Collectors;
import personal.neuro.neuroAPI.dtos.*;
import personal.neuro.neuroAPI.entities.*;
import personal.neuro.neuroAPI.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private EmployeeRepository employerRepository;

    public EmployeeService(EmployeeRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee newEmployee = employeeDTO.toEntity();

        Employee save = employerRepository.save(newEmployee);

        return EmployeeDTO.fromEntity(save);
    }

    @Transactional
    public List<EmployeeDTO> getAllEmployers() {
        return employerRepository.findAll().stream().map(EmployeeDTO::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    public EmployeeDTO getEmployerById(Long id) {
        Employee employee = employerRepository.findById(id).orElse(null);

        employee.getTechnicalSkills().size();
        employee.getCertifications().size();

        return EmployeeDTO.fromEntity(employee);
    }

    public EmployeeDTO updateEmployer(Long id, EmployeeDTO employeeDTO) {
        try {
            Employee existingEmployee = employerRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));

            existingEmployee.setName(employeeDTO.getName());
            existingEmployee.setEmail(employeeDTO.getEmail());
            existingEmployee.setMatricula(employeeDTO.getMatricula());
            existingEmployee.setLinkedin(employeeDTO.getLinkedin());
            existingEmployee.setPhone(employeeDTO.getPhone());

            existingEmployee.getTechnicalSkills().clear();
            List<TechnicalSkill> newSkills = TechnicalSkillDTO.toEntites(employeeDTO.getTechnicalSkills());
            for (TechnicalSkill skill : newSkills) {
                skill.setEmployee(existingEmployee);
            }
            existingEmployee.getTechnicalSkills().addAll(newSkills);

            existingEmployee.getCertifications().clear();
            List<Certification> newCertifications = CertificationDTO.toEntites(employeeDTO.getCertifications());
            for (Certification certification : newCertifications) {
                certification.setEmployee(existingEmployee);
            }
            existingEmployee.getCertifications().addAll(newCertifications);

            Employee updatedEmployee = employerRepository.save(existingEmployee);

            return EmployeeDTO.fromEntity(updatedEmployee);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public void deleteEmployer(Long id) {
        employerRepository.deleteById(id);
    }

}
