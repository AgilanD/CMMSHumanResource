package cmms.humanresource.services;

import cmms.humanresource.dto.CustomerRequestDto;
import cmms.humanresource.dto.CustomerResponseDto;
import cmms.humanresource.entity.Customers;
import cmms.humanresource.exception.CustomerNotFoundException;
import cmms.humanresource.repository.CustomerRepository;
import cmms.humanresource.util.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServicesImpl implements CustomerServices {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {

        Customers customer = customerMapper.customerRequestToCustomer(customerRequestDto);

        Customers savedCustomer = customerRepository.save(customer);

        return customerMapper.customerToCustomerResponseDto(savedCustomer);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerResponseDto)
                .toList();
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {

        Customers customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return customerMapper.customerToCustomerResponseDto(customer);
    }

    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto customerRequestDto) {

        Customers existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        existingCustomer.setCustomerName(customerRequestDto.getCustomerName());
        existingCustomer.setContactNumber(customerRequestDto.getContactNumber());
        existingCustomer.setEmail(customerRequestDto.getEmail());
        existingCustomer.setAddress(customerRequestDto.getAddress());

        Customers updatedCustomer = customerRepository.save(existingCustomer);

        return customerMapper.customerToCustomerResponseDto(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {

        Customers customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        customerRepository.delete(customer);
    }
}