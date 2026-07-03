package cmms.humanresource.util;

import cmms.humanresource.dto.CustomerRequestDto;
import cmms.humanresource.dto.CustomerResponseDto;
import cmms.humanresource.entity.Customers;
import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {

    public  Customers customerRequestToCustomer (CustomerRequestDto requestDto){


        return  Customers.builder()
                .customerName(requestDto.getCustomerName())
                .contactNumber(requestDto.getContactNumber())
                .email(requestDto.getEmail())
                .address(requestDto.getAddress())
                .isActive(requestDto.getIsActive())
                .build();
    }

    public  CustomerResponseDto customerToCustomerResponseDto(Customers customer){

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
