package accala.test.service;

import accala.test.model.AddressRequest;
import accala.test.model.PersonRequest;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface PersonService {

    ResponseEntity savePerson(PersonRequest request);
    ResponseEntity updatePerson(PersonRequest request, UUID uuid);
    ResponseEntity fetchPerson(UUID id);
    ResponseEntity deletePerson(UUID id);
    ResponseEntity getPersonsCount();
    ResponseEntity getAllPersons();
    ResponseEntity updatePersonAddress(AddressRequest request, UUID personId);
    ResponseEntity editPersonAddress(AddressRequest request, UUID personId, UUID addressId);
}
