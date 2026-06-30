package cmms.HumanResource.services;

import cmms.HumanResource.Dto.EmployeeRequestDto;
import cmms.HumanResource.Dto.EmployeeResponseDto;
import cmms.HumanResource.entity.Employee;
import cmms.HumanResource.common.entity.Plants;
import cmms.HumanResource.repository.EmployeeRepository;
import cmms.HumanResource.common.entity.repository.PlantsRepository;
import cmms.HumanResource.util.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServicesImpl implements EmployeeServices{

    private final EmployeeRepository employeeRepository;

    private final PlantsRepository plantsRepository;

    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeResponseDto> GetAllEmployee(){
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Employee AddEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto) {

        Plants plant = plantsRepository.findById(requestDto.getPlantId())
                .orElseThrow(() -> new RuntimeException("Plant not found with ID: " + requestDto.getPlantId()));

        Employee employee = employeeMapper.EmployeeResponseDtoToEmployee(plant,requestDto);

        Employee savedEmployee = AddEmployee(employee);

        return employeeMapper.EmployeeToEmployeeResponseDta(plant,savedEmployee);

    }

}
