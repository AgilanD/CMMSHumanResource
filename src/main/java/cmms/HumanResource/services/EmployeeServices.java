package cmms.HumanResource.services;


import cmms.HumanResource.Dto.EmployeeRequestDto;
import cmms.HumanResource.Dto.EmployeeResponseDto;
import cmms.HumanResource.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeServices {

    public Employee AddEmployee(Employee employee);

    public EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto);

    public List<EmployeeResponseDto> GetAllEmployee();

}
