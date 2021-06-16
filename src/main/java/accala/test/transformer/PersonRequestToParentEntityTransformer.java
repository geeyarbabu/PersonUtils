package accala.test.transformer;

import accala.test.model.PersonRequest;
import accala.test.persistence.entity.PersonEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersonRequestToParentEntityTransformer {

    public PersonEntity transformForCreateRequest(final PersonRequest request) {
        return PersonEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
    }

    public PersonEntity transformForUpdateRequest(final PersonRequest request, final UUID id) {
        return PersonEntity.builder()
                .id(id)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
    }
}
