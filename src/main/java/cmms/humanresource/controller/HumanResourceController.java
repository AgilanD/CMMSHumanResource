package cmms.humanresource.controller;

import cmms.humanresource.dto.CustomerRequestDto;
import cmms.humanresource.dto.CustomerResponseDto;
import cmms.humanresource.dto.EmployeeRequestDto;
import cmms.humanresource.dto.EmployeeResponseDto;
import cmms.humanresource.services.CustomerServices;
import cmms.humanresource.services.EmployeeServices;
import cmms.humanresource.usercontext.RequireRole;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/human")
@RequiredArgsConstructor
public class HumanResourceController {

    private final EmployeeServices employeeservices;
    private final CustomerServices customerservices;


    @GetMapping("/allEmployee")
    @RequireRole({"ADMIN", "PLANT_MANAGER"})
    public List<EmployeeResponseDto> allEmployee() {
        return employeeservices.getAllEmployee();
    }

    @PostMapping("/addEmployee")
    @RequireRole({"ADMIN", "PLANT_MANAGER"})
    public EmployeeResponseDto addEmployee(@RequestBody EmployeeRequestDto requestDto) {
        return employeeservices.createEmployee(requestDto);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    @RequireRole({"ADMIN"})
    public String softDeleteEmployee(@PathVariable Long id) {
        employeeservices.deleteEmployee(id);
        return "Employee soft-deleted successfully with ID: " + id;
    }

    @GetMapping("/recycleBin")
    @RequireRole({"ADMIN", "PLANT_MANAGER"})
    public List<EmployeeResponseDto> getRecycleBin() {
        return employeeservices.getAllDeletedEmployees();
    }


    @PostMapping("/addCustomer")
    @RequireRole({"ADMIN", "PLANT_MANAGER"})
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto requestDto) {
        return customerservices.createCustomer(requestDto);
    }

    @GetMapping("/allCustomers")
    @RequireRole({"ADMIN", "PLANT_MANAGER"})
    public List<CustomerResponseDto> getAllCustomers() {
        return customerservices.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    @RequireRole({"ADMIN", "PLANT_MANAGER"})
    public CustomerResponseDto getCustomerById(@PathVariable Long id) {
        return customerservices.getCustomerById(id);
    }

    @PutMapping("/updateCustomer/{id}")
    @RequireRole({"ADMIN"})
    public CustomerResponseDto updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerRequestDto requestDto) {

        return customerservices.updateCustomer(id, requestDto);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    @RequireRole({"ADMIN"})
    public String deleteCustomer(@PathVariable Long id) {
        customerservices.deleteCustomer(id);
        return "Customer deleted successfully with ID: " + id;
    }

}