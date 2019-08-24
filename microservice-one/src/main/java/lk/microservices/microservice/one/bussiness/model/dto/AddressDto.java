package lk.microservices.microservice.one.bussiness.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AddressDto {
    private String address;
    private String country;
    private String postalCode;
}
