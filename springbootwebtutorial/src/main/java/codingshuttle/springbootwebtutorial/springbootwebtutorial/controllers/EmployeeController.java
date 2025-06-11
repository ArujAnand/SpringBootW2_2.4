package codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees") //gives path for the parent
public class EmployeeController {
//    @GetMapping(path = "/getMessage")
//    public String getMyMessage() {
//        return "Secret Message: dn@#jas*";
//    }
    @GetMapping("{employeeID}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeID) {
        return new EmployeeDTO(employeeID, "Aruj", "aruj@gmail.com", 23, LocalDate.of(2021,1,12), true);
    }

    @GetMapping
    public String getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
        return "Hi age: " + age + " " + sortBy;
    }

    @PostMapping
    public String createNewEmployee() {
        return "Hello from POST";
    }

    @PutMapping
    public String updateEmployeeId() {
        return "Hello from PUT";
    }

    @PostMapping (path = "/addEmpployee")
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee) {
        inputEmployee.setId(100L);
        return inputEmployee; //ideally we save it inside DB
    }
}