package codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+employeeID));
    }

    /**
     * If NoSuchElement Exception occurs in this EmployeeController Class then it will be handled by this method
     * @param exception NoSuchElementException
     * @return Status code with message
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleEmployeeNotFound(NoSuchElementException exception) {
        return new ResponseEntity<>("Employee was not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    /**
     * @param employeeDTO Details of the employee
     * @param employeeId Employee to be updated
     * @return Response Entity with the updated data
     * Updates information for the given employee id if exits else throws ResourceNotFoundException
     */
    @PutMapping(path = "/{employeeId}")
    public ResponseEntity <EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeDTO, employeeId));
    }

    @PostMapping (path = "/addEmployee")
    public ResponseEntity <EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee) {
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
    //Not added @Valid here as we may or may not get some inputs
    /**
     * Updates partial fields for the given employee ID
     * @param updates Map of fields updated for the employeeID
     * @param employeeId EmployeeId of the employee for which fields has to be changed
     */
    public ResponseEntity <EmployeeDTO> updatePartialEmployeeById (@RequestBody Map<String, Object> updates, @PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId, updates);
        if (employeeDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}