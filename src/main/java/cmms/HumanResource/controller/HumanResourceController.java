package cmms.HumanResource.controller;


import cmms.HumanResource.Dto.CustomerRequestDto;
import cmms.HumanResource.Dto.CustomerResponseDto;
import cmms.HumanResource.Dto.EmployeeRequestDto;
import cmms.HumanResource.Dto.EmployeeResponseDto;
import cmms.HumanResource.entity.Customers;
import cmms.HumanResource.services.CustomerServices;
import cmms.HumanResource.services.EmployeeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/human")
@RequiredArgsConstructor
public class HumanResourceController {

    private  final EmployeeServices employeeservices;

    private final CustomerServices customerservices;


    @GetMapping("/All")
    public List<EmployeeResponseDto> AllEmployee(){
        return employeeservices.GetAllEmployee();
    }

    @GetMapping("/checkings")
    public String names(){
        return "SuccessFully Created it ";
    }

    @PostMapping("/addEmployee")
    public EmployeeResponseDto addEmployee( @RequestBody EmployeeRequestDto requestDto) {
        return employeeservices.createEmployee(requestDto);
    }

    @GetMapping("/AllCustomers")
    public List<Customers> AllCustomers(){
        return customerservices.Allcustomer();
    }

    @PostMapping("/AddCustomer")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        return customerservices.CreateCustomer(customerRequestDto);
    }

}
