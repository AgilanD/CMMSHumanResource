package cmms.HumanResource.util;

import cmms.HumanResource.Dto.CustomerRequestDto;
import cmms.HumanResource.Dto.CustomerResponseDto;
import cmms.HumanResource.entity.Customers;


public class CustomerMapper {

    public static Customers CustomerRequestToCustomer (CustomerRequestDto requestDto){


        return  Customers.builder()
                .customerName(requestDto.getCustomerName())
                .contactNumber(requestDto.getContactNumber())
                .email(requestDto.getEmail())
                .address(requestDto.getAddress())
                .isActive(requestDto.getIsActive())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto(Customers customer){

        if (customer == null) return null;

        return CustomerResponseDto.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .contactNumber(customer.getContactNumber())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .isActive(customer.getIsActive())
                .build();
    }

}
