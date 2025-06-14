package codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity <EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeDTO, employeeId));
    }

    @PostMapping (path = "/addEmployee")
    public ResponseEntity <EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO inputEmployee) {
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @DeleteMapping (path = "/{employeeId}")
    public ResponseEntity <Boolean> deleteEmployeeById(@PathVariable Long employeeId) {
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);

        if (gotDeleted)
            return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity <EmployeeDTO> updatePartialEmployeeById (@RequestBody Map<String, Object> updates, @PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId, updates);
        if (employeeDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}