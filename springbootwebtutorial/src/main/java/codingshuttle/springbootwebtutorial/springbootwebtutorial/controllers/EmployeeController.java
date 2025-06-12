package codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees") //gives path for the parent
public class EmployeeController {
//    @GetMapping(path = "/getMessage")
//    public String getMyMessage() {
//        return "Secret Message: dn@#jas*";
//    }

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("{employeeID}")
    public EmployeeEntity getEmployeeById(@PathVariable Long employeeID) {
        return employeeRepository.findById(employeeID).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
        return employeeRepository.findAll();
    }

    @PostMapping
    public String createNewEmployee() {
        return "Hello from POST";
    }

    @PutMapping
    public String updateEmployeeId() {
        return "Hello from PUT";
    }

    @PostMapping (path = "/addEmployee")
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee) {
        return employeeRepository.save(inputEmployee);
    }
}