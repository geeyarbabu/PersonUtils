import accala.test.model.PersonRequest;
import accala.test.persistence.AddressRepository;
import accala.test.persistence.PersonRepository;
import accala.test.persistence.entity.PersonEntity;
import accala.test.service.implementation.PersonServiceImplementation;
import accala.test.transformer.AddressRequestToPersonEntityTransformer;
import accala.test.transformer.PersonRequestToParentEntityTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {

    private PersonServiceImplementation personServiceImplementation;

    @Mock
    private PersonRepository personRepository;
    @Mock
    private AddressRepository addressRepository;

    @BeforeEach
    public void init() {
        personServiceImplementation = new PersonServiceImplementation(personRepository, addressRepository, new PersonRequestToParentEntityTransformer(),
                new AddressRequestToPersonEntityTransformer());
    }

    @Test
    public void savePersonTestSuccess(){

        PersonRequest request = PersonRequest.builder().firstName("firstName").lastName("lastName").build();

        PersonEntity personEntity = PersonEntity.builder().id(UUID.randomUUID()).firstName("firstName").lastName("lastName").build();

        Mockito.doReturn(personEntity).when(personRepository).save(any());

        ResponseEntity response = personServiceImplementation.savePerson(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(personEntity, response.getBody());

    }

    @Test
    public void fetchPersonTestSuccess(){

        UUID personId = UUID.randomUUID();

        PersonEntity personEntity = PersonEntity.builder().id(personId).firstName("firstName").lastName("lastName").build();

        Mockito.doReturn(Optional.of(personEntity)).when(personRepository).findById(personId);

        ResponseEntity response = personServiceImplementation.fetchPerson(personId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personEntity, response.getBody());

    }

    @Test
    public void updatePersonTestSuccess(){

        UUID personId = UUID.randomUUID();

        PersonRequest request = PersonRequest.builder().firstName("firstName").lastName("lastName").build();

        PersonEntity personEntity = PersonEntity.builder().id(UUID.randomUUID()).firstName("firstName").lastName("lastName").build();

        Mockito.doReturn(personEntity).when(personRepository).save(any());
        Mockito.doReturn(Optional.of(personEntity)).when(personRepository).findById(personId);

        ResponseEntity response = personServiceImplementation.updatePerson(request,personId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personEntity, response.getBody());

    }

}
