package personal.neuro.neuroAPI.controllers;

import java.util.List;
import personal.neuro.neuroAPI.dtos.EmployeeDTO;
import personal.neuro.neuroAPI.services.EmployeeService;

import io.swagger.v3.oas.annotations.responses.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private EmployeeService employerService;

    public EmployeeController(EmployeeService employerService) {
        this.employerService = employerService;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeDTO> createEmployer(@RequestBody EmployeeDTO employeeDTO) {
        try {
            employeeDTO = employerService.createEmployee(employeeDTO);
            return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException error) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> getEmployerAll() {
        return employerService.getAllEmployers();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(path = "/{employer_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeDTO> getEmployerId(@PathVariable(name = "employer_id") Long id) {
        EmployeeDTO employeeDTO = employerService.getEmployerById(id);
        if (employeeDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping(path = "/{employer_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeDTO> updateEmployer(@PathVariable(name = "employer_id") Long id,
            @RequestBody EmployeeDTO employeeDTO) {
        try {
            employeeDTO = employerService.updateEmployer(id, employeeDTO);
            if (employeeDTO == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
            }
        } catch (Exception error) {
            error.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content")
    })
    @DeleteMapping(path = "/{employer_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteEmployer(@PathVariable(name = "employer_id") Long id) {
        employerService.deleteEmployer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
