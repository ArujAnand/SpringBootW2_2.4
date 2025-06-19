package codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.DepartmentDTO;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.DepartmentEntity;
import codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.DepartmentExistsException;
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
}
