package cmms.HumanResource.util;

import cmms.HumanResource.Dto.CustomerRequestDto;
import cmms.HumanResource.Dto.CustomerResponseDto;
import cmms.HumanResource.entity.Customers;
import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {

    public  Customers CustomerRequestToCustomer (CustomerRequestDto requestDto){


        return  Customers.builder()
                .customerName(requestDto.getCustomerName())
                .contactNumber(requestDto.getContactNumber())
                .email(requestDto.getEmail())
                .address(requestDto.getAddress())
                .isActive(requestDto.getIsActive())
                .build();
    }

    public  CustomerResponseDto CustomerToCustomerResponseDto(Customers customer){

        if (customer == null) return null;

        return CustomerResponseDto.builder()
                .id(customer.getId())

                .lastModifiedBy(customer.getLastModifiedBy())
                .lastModifiedAt(customer.getLastModifiedAt())
                .createdAt(customer.getCreatedAt())
                .createdBy(customer.getCreatedBy())

                .customerName(customer.getCustomerName())
                .contactNumber(customer.getContactNumber())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .isActive(customer.getIsActive())
                .build();
    }

}
