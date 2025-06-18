package codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }
}
