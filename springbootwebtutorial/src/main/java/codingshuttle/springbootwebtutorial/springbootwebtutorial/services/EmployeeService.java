package codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.springframework.util.ReflectionUtils;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public Optional <EmployeeDTO> getEmployeeById(Long employeeID) {
        return employeeRepository.findById(employeeID).map(employeeEntity1 -> mapper.map(employeeEntity1, EmployeeDTO.class));
        //If optional is empty the map() simply returns Optional.empty immediately  it never executes the mapping function
        //findById function provided by JPA also returns an optional
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .map(employeeEntity -> mapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = mapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return mapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    /**
     * Checks if the employee with given id exists
     * @param employeeId id to be searched in the DB for existence
     *@return {@code TRUE} if found else throw ResourceNotFoundException
     */
    public boolean isExistsByEmployeeId(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists)
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        return true;
    }

    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long employeeId) {
        isExistsByEmployeeId(employeeId);
        //        create an employee with this data if not present otherwise update
        EmployeeEntity employeeEntity = mapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return mapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    /**
     * Checks if the employee with given id exists
     * @param employeeId id to be searched in the DB for existence
     * @return {@code TRUE} if found else {@code FALSE}
     */
    public boolean existsById (Long employeeId) {
        return employeeRepository.existsById(employeeId);
    }

    /**
     * Deletes employee with given id
     * @param employeeId id of the employee to be deleted
     * @return {@code TRUE} if the employee is deleted else return {@code False}
     */
    public boolean deleteEmployeeById(Long employeeId) {
        isExistsByEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    /**
     * Updates partial fields for the given employee ID
     * @param updates Map of fields updated for the employeeID
     * @param employeeId EmployeeId of the employee for which fields has to be changed
     */
    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        boolean exists = existsById(employeeId);
        if (!exists) {
            return null;
        }

       EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });

        return mapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}