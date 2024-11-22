package personal.neuro.neuroAPI.repositories;

import java.util.List;

import personal.neuro.neuroAPI.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContaining(String partName);

    List<Employee> findByNameContainingIgnoreCase(String name);
}
