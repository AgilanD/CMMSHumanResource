package cmms.humanresource.controller;

import cmms.humanresource.dto.CustomerRequestDto;
import cmms.humanresource.dto.CustomerResponseDto;
import cmms.humanresource.dto.EmployeeRequestDto;
import cmms.humanresource.dto.EmployeeResponseDto;
import cmms.humanresource.exception.APIResponse;
import cmms.humanresource.services.CustomerServices;
import cmms.humanresource.services.EmployeeServices;
import cmms.humanresource.usercontext.RequireRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/human")
@RequiredArgsConstructor
public class HumanResourceController {

    private final EmployeeServices employeeservices;
    private final CustomerServices customerservices;

    @PutMapping("/updateEmployeeByIds/{id}")
    @RequireRole({"ADMIN","PLANT_MANAGER"})
    public ResponseEntity<APIResponse<EmployeeResponseDto>> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeRequestDto requestDto,
            HttpServletRequest request) {
        EmployeeResponseDto data = employeeservices.updateEmployee(id, requestDto);
        APIResponse<EmployeeResponseDto> response = new APIResponse<>(HttpStatus.OK.value(), null, "Employee updated successfully", "uri=" + request.getRequestURI(), data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/AllEmployee")
    @RequireRole({"ADMIN","PLANT_MANAGER"})
    public ResponseEntity<APIResponse<List<EmployeeResponseDto>>> allEmployee(HttpServletRequest request) {
        List<EmployeeResponseDto> data = employeeservices.getAllEmployee();
        APIResponse<List<EmployeeResponseDto>> response = new APIResponse<>(HttpStatus.OK.value(), null, "Employees retrieved successfully", "uri=" + request.getRequestURI(), data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addEmployee")
    @RequireRole({"ADMIN","PLANT_MANAGER"})
    public ResponseEntity<APIResponse<EmployeeResponseDto>> addEmployee(
            @Valid @RequestBody EmployeeRequestDto requestDto,
            HttpServletRequest request) {
        EmployeeResponseDto data = employeeservices.createEmployee(requestDto);
        APIResponse<EmployeeResponseDto> response = new APIResponse<>(HttpStatus.CREATED.value(), null, "Employee created successfully", "uri=" + request.getRequestURI(), data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    @RequireRole({"ADMIN"})
    public ResponseEntity<APIResponse<String>> softDeleteEmployee(
            @PathVariable Long id,
            HttpServletRequest request) {
        employeeservices.deleteEmployee(id);
        APIResponse<String> response = new APIResponse<>(HttpStatus.OK.value(), null, "Employee soft-deleted successfully", "uri=" + request.getRequestURI(), "ID: " + id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/recycleBin")
    @RequireRole({"ADMIN","PLANT_MANAGER"})
    public ResponseEntity<APIResponse<List<EmployeeResponseDto>>> getRecycleBin(HttpServletRequest request) {
        List<EmployeeResponseDto> data = employeeservices.getAllDeletedEmployees();
        APIResponse<List<EmployeeResponseDto>> response = new APIResponse<>(HttpStatus.OK.value(), null, "Recycle bin retrieved successfully", "uri=" + request.getRequestURI(), data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addCustomer")
    @RequireRole({"ADMIN","PLANT_MANAGER"})
    public ResponseEntity<APIResponse<CustomerResponseDto>> addCustomer(
            @Valid
            @RequestBody CustomerRequestDto requestDto,
            HttpServletRequest request) {
        CustomerResponseDto data = customerservices.createCustomer(requestDto);
        APIResponse<CustomerResponseDto> response = new APIResponse<>(HttpStatus.CREATED.value(), null, "Customer created successfully", "uri=" + request.getRequestURI(), data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/allCustomers")
    @RequireRole({"ADMIN","PLANT_MANAGER"})
    public ResponseEntity<APIResponse<List<CustomerResponseDto>>> getAllCustomers(HttpServletRequest request) {
        List<CustomerResponseDto> data = customerservices.getAllCustomers();
        APIResponse<List<CustomerResponseDto>> response = new APIResponse<>(HttpStatus.OK.value(), null, "Customers retrieved successfully", "uri=" + request.getRequestURI(), data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/{id}")
    @RequireRole({"ADMIN","PLANT_MANAGER"})
    public ResponseEntity<APIResponse<CustomerResponseDto>> getCustomerById(
            @PathVariable Long id,
            HttpServletRequest request) {
        CustomerResponseDto data = customerservices.getCustomerById(id);
        APIResponse<CustomerResponseDto> response = new APIResponse<>(HttpStatus.OK.value(), null, "Customer retrieved successfully", "uri=" + request.getRequestURI(), data);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateCustomerByIds/{id}")
    @RequireRole({"ADMIN","PLANT_MANAGER"})
    public ResponseEntity<APIResponse<CustomerResponseDto>> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerRequestDto requestDto,
            HttpServletRequest request) {
        CustomerResponseDto data = customerservices.updateCustomer(id, requestDto);
        APIResponse<CustomerResponseDto> response = new APIResponse<>(HttpStatus.OK.value(), null, "Customer updated successfully", "uri=" + request.getRequestURI(), data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    @RequireRole({"ADMIN"})
    public ResponseEntity<APIResponse<String>> deleteCustomer(
            @PathVariable Long id,
            HttpServletRequest request) {
        customerservices.deleteCustomer(id);
        APIResponse<String> response = new APIResponse<>(HttpStatus.OK.value(), null, "Customer deleted successfully", "uri=" + request.getRequestURI(), "ID: " + id);
        return ResponseEntity.ok(response);
    }
}

