package codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees") //gives path for the parent
public class EmployeeController {
//    @GetMapping(path = "/getMessage")
//    public String getMyMessage() {
//        return "Secret Message: dn@#jas*";
//    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("{employeeID}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeID) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(employeeID);
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
        return employeeService.getAllEmployees();
    }

    @PutMapping(path = "/{employeeId}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId) {
        return employeeService.updateEmployeeById(employeeDTO, employeeId);
    }

    @PostMapping (path = "/addEmployee")
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee) {
        return employeeService.createNewEmployee(inputEmployee);
    }

    @DeleteMapping (path = "/{employeeId}")
    public boolean deleteEmployeeById(@PathVariable Long employeeId) {
        return employeeService.deleteEmployeeById(employeeId);
    }

    @PatchMapping(path = "/{employeeId}")
    public EmployeeDTO updatePartialEmployeeById (@RequestBody Map<String, Object> updates, @PathVariable Long employeeId) {
        return employeeService.updatePartialEmployeeById(employeeId, updates);
    }
}