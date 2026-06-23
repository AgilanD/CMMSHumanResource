package cmms.HumanResource.services;


import cmms.HumanResource.Dto.CustomerRequestDto;
import cmms.HumanResource.Dto.CustomerResponseDto;
import cmms.HumanResource.entity.Customers;
import cmms.HumanResource.repository.CustomerRepository;
import cmms.HumanResource.util.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServicesImpl implements CustomerServices{

    private final CustomerRepository customerRepository;

    public Customers AddCustomer(Customers customers){
        return  customerRepository.save(customers);
    }

    public List<Customers> Allcustomer(){
        return customerRepository.findAll();
    }

    public CustomerResponseDto CreateCustomer (CustomerRequestDto customerrequestDto){


         Customers customer = AddCustomer(CustomerMapper.CustomerRequestToCustomer(customerrequestDto));

         return CustomerMapper.CustomerToCustomerResponseDto(customer);

    }

}
