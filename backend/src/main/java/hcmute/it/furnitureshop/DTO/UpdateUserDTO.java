package hcmute.it.furnitureshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    private String name;
    private String apartmentNumber;
    private String image;
    private int city;
    private int district;
    private int ward;
    private String phone;
    private String password;
}
