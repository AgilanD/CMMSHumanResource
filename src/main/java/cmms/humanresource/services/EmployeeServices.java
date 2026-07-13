package cmms.humanresource.services;

import cmms.humanresource.dto.EmployeeRequestDto;
import cmms.humanresource.dto.EmployeeResponseDto;
import cmms.humanresource.entity.Employee;

import java.util.List;

public interface EmployeeServices {

    EmployeeResponseDto getEmployeeById(Long id);

    Employee addEmployee(Employee employee);

    EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto);

    List<EmployeeResponseDto> getAllEmployee();

    void deleteEmployee(Long id);

    List<EmployeeResponseDto> getAllDeletedEmployees();

    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto requestDto);
}