package cmms.HumanResource.util;


import cmms.HumanResource.Dto.EmployeeRequestDto;
import cmms.HumanResource.Dto.EmployeeResponseDto;
import cmms.HumanResource.entity.Employee;
import cmms.HumanResource.entity.Plants;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeResponseDto EmployeeToEmployeeResponseDta(Plants plant , Employee savedEmployee){

        return EmployeeResponseDto.builder()
                .id(savedEmployee.getId())
                .employeeCode(savedEmployee.getEmployeeCode())
                .fullName(savedEmployee.getFullName())
                .designation(savedEmployee.getDesignation())
                .dateOfBirth(savedEmployee.getDateOfBirth())
                .joiningDate(savedEmployee.getJoiningDate())
                .profileImage(savedEmployee.getProfileImage())
                .isActive(savedEmployee.getIsActive())
                .plantId(plant.getId())
                .plantName(plant.getName())
                .build();

    }


    public Employee EmployeeResponseDtoToEmployee(Plants plant , EmployeeRequestDto requestDto){
        Employee employee = Employee.builder()
                .employeeCode("EMP-" + java.util.UUID.randomUUID().toString().substring(0, 5).toUpperCase())
                .fullName(requestDto.getFullName())
                .designation(requestDto.getDesignation())
                .dateOfBirth(requestDto.getDateOfBirth())
                .joiningDate(requestDto.getJoiningDate())
                .plant(plant)
                .build();
        return employee;
    }


    public static EmployeeResponseDto convertToResponseDto(Employee employee) {
        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .createdAt(employee.getCreatedAt())
                .createdBy(employee.getCreatedBy())
                .lastModifiedAt(employee.getLastModifiedAt())
                .lastModifiedBy(employee.getLastModifiedBy())
                .employeeCode(employee.getEmployeeCode())
                .fullName(employee.getFullName())
                .designation(employee.getDesignation())
                .dateOfBirth(employee.getDateOfBirth())
                .joiningDate(employee.getJoiningDate())
                .profileImage(employee.getProfileImage())
                .isActive(employee.getIsActive())
                .plantId(employee.getPlant() != null ? employee.getPlant().getId() : null)
                .plantName(employee.getPlant() != null ? employee.getPlant().getName() : null) // Assuming Plants has a getName()
                .build();
    }

}
