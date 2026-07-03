package cmms.humanresource.services;

import cmms.humanresource.dto.CustomerRequestDto;
import cmms.humanresource.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerServices {

    CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto getCustomerById(Long id);

    CustomerResponseDto updateCustomer(Long id, CustomerRequestDto customerRequestDto);

    void deleteCustomer(Long id);
}