package accala.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressRequest {
    private String street;
    private String city;
    private String state;
    private String postalCode;
}
