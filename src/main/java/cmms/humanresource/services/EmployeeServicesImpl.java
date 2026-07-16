package cmms.humanresource.services;

import cmms.humanresource.dto.EmployeeRequestDto;
import cmms.humanresource.dto.EmployeeResponseDto;
import cmms.humanresource.entity.Employee;
import cmms.humanresource.common.entity.Plants;
import cmms.humanresource.exception.EmployeeNotFoundException;
import cmms.humanresource.repository.EmployeeRepository;
import cmms.humanresource.common.entity.repository.PlantsRepository;
import cmms.humanresource.util.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServicesImpl implements EmployeeServices {

    private final EmployeeRepository employeeRepository;
    private final PlantsRepository plantsRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeResponseDto> getAllEmployee() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::convertToResponseDto)
                .toList();
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return EmployeeMapper.convertToResponseDto(employee);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto) {
        Plants plant = plantsRepository.findById(requestDto.getPlantId())
                .orElseThrow(() -> new RuntimeException("Plant not found with ID: " + requestDto.getPlantId()));

        Employee employee = employeeMapper.employeeResponseDtoToEmployee(plant, requestDto);
        Employee savedEmployee = addEmployee(employee);

        return employeeMapper.employeeToEmployeeResponseDta(plant, savedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeResponseDto> getAllDeletedEmployees() {
        return employeeRepository.findAllDeletedEmployees().stream()
                .map(EmployeeMapper::convertToResponseDto)
                .toList();
    }

    @Override
    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto requestDto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        Plants plant = plantsRepository.findById(requestDto.getPlantId())
                .orElseThrow(() -> new RuntimeException("Plant not found with ID: " + requestDto.getPlantId()));

        existingEmployee.setFullName(requestDto.getFullName());
        existingEmployee.setDesignation(requestDto.getDesignation());
        existingEmployee.setDateOfBirth(requestDto.getDateOfBirth());
        existingEmployee.setJoiningDate(requestDto.getJoiningDate());
        existingEmployee.setPlant(plant);

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return employeeMapper.employeeToEmployeeResponseDta(plant, updatedEmployee);
    }
}