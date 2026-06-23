package cmms.HumanResource.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDto {

    private Long id;
    private String customerName;
    private String contactNumber;
    private String email;
    private String address;
    private Boolean isActive;

}
