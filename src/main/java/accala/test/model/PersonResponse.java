package accala.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {
    private UUID id;
    private String firstName;
    private String lastName;
}
