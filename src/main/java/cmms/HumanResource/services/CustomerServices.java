package cmms.HumanResource.services;

import cmms.HumanResource.Dto.CustomerRequestDto;
import cmms.HumanResource.Dto.CustomerResponseDto;
import cmms.HumanResource.entity.Customers;

import java.util.List;

public interface CustomerServices {

    public Customers AddCustomer(Customers customers);

    public List<Customers> Allcustomer();
    public CustomerResponseDto CreateCustomer (CustomerRequestDto customerrequestDto);

}
