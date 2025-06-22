package codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.DepartmentDTO;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.DepartmentEntity;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.DepartmentExistsException;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }


    /**
     * @param departmentId id of the department to be searched
     * @throws ResourceNotFoundException If no department is found with the given ID.
     */
    public void isDepartmentExistsById(long departmentId) {
        if (departmentRepository.existsById(departmentId)) {
            return;
        }

        throw new ResourceNotFoundException("Department with id: " + departmentId + " does not exist");
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities.stream()
                .map(departmentEntity -> mapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createNewDepartment(DepartmentDTO inputDepartment) {
        DepartmentEntity toSaveEntity = mapper.map(inputDepartment, DepartmentEntity.class);
        String title = toSaveEntity.getTitle();
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        boolean departmentExists = departmentEntities.stream()
                .anyMatch(entity -> entity.getTitle().equals(title));

        if(departmentExists) {
            throw new DepartmentExistsException("Department with name: "+ title + " already exists");
        }
        DepartmentEntity savedDepartment = departmentRepository.save(toSaveEntity);
        return mapper.map(savedDepartment, DepartmentDTO.class);
    }

    /**
     * Retrieves a department by its unique identifier.
     *
     * @param departmentId The unique ID of the department to retrieve.
     *  @return The {@link DepartmentDTO} containing the department's details.
     * @throws ResourceNotFoundException If no department is found with the given ID.
     */
    public DepartmentDTO getDepartmentByid(long departmentId) {
        //check if department exists
        isDepartmentExistsById(departmentId);
        //if exists - which we are sure it will as we check for existence beforehand hence using .get
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).get();
        return mapper.map(departmentEntity, DepartmentDTO.class);
    }

    public boolean deleteEmployeeById(long departmentId) {
        isDepartmentExistsById(departmentId);

        departmentRepository.deleteById(departmentId);
        return true;
    }
}