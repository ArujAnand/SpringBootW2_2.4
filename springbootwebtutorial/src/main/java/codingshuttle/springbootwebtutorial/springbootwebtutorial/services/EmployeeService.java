package codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
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

    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long employeeId) {
//        create an employee with this data if not present otherwise update
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElse(new EmployeeEntity());

        employeeEntity.setName(employeeDTO.getName());
        employeeEntity.setEmail(employeeDTO.getEmail());
        employeeEntity.setAge(employeeDTO.getAge());
        employeeEntity.setDateOfJoining(employeeDTO.getDateOfJoining());
        employeeEntity.setIsActive(employeeDTO.getIsActive());
//        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return mapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean existsById (Long employeeId) {
        if (employeeRepository.existsById(employeeId))
            return true;
        return false;
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exists = existsById(employeeId);
        if (exists) {
            employeeRepository.deleteById(employeeId);
            return true;
        }
        return false;
    }

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