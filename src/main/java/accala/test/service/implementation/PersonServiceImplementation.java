package accala.test.service.implementation;

import accala.test.model.AddressRequest;
import accala.test.model.ErrorResponse;
import accala.test.model.PersonRequest;
import accala.test.persistence.AddressRepository;
import accala.test.persistence.PersonRepository;
import accala.test.persistence.entity.AddressEntity;
import accala.test.persistence.entity.PersonEntity;
import accala.test.service.PersonService;
import accala.test.transformer.AddressRequestToPersonEntityTransformer;
import accala.test.transformer.PersonRequestToParentEntityTransformer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonServiceImplementation implements PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final PersonRequestToParentEntityTransformer transformer;
    private final AddressRequestToPersonEntityTransformer addressRequestToPersonEntityTransformer;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ResponseEntity savePerson(final PersonRequest request) {

        PersonEntity savedPerson = personRepository.save(transformer.transformForCreateRequest(request));
        log.info("Person has been saved successfully with id: {}", savedPerson.getId());

        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updatePerson(final PersonRequest request, final UUID id) {

        ResponseEntity person = fetchPerson(id);

        if (person.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            return new ResponseEntity<>(ErrorResponse.builder().errorMessage("Please provide a valid user id").build(), HttpStatus.NOT_FOUND);
        }
        PersonEntity updatedUser = personRepository.save(transformer.transformForUpdateRequest(request, id));
        log.info("Person has been updated successfully.");

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Override
    public ResponseEntity fetchPerson(final UUID id) {
        Optional<PersonEntity> person = personRepository.findById(id);

        ErrorResponse errorResponse = ErrorResponse.builder().errorMessage("Provided user id not found in database").build();
        if (!person.isPresent()) {
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity deletePerson(final UUID id) {
        personRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPersonsCount() {
        return new ResponseEntity(personRepository.count(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAllPersons() {
        return new ResponseEntity(personRepository.findAll(), HttpStatus.OK);
    }

    @SneakyThrows
    @Override
    public ResponseEntity updatePersonAddress(AddressRequest request, UUID personId) {

        ResponseEntity person = fetchPerson(personId);
        if (person.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            return new ResponseEntity<>(ErrorResponse.builder().errorMessage("Please provide a valid user id").build(), HttpStatus.NOT_FOUND);
        }

        PersonEntity personToUpdate = addressRequestToPersonEntityTransformer.transform(request, objectMapper.readValue(
                objectMapper.writeValueAsString(person.getBody())
                , PersonEntity.class));

        PersonEntity updatedPerson = personRepository.save(personToUpdate);

        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @SneakyThrows
    @Override
    public ResponseEntity editPersonAddress(AddressRequest request, UUID personId, UUID addressId) {

        ResponseEntity person = fetchPerson(personId);
        if (person.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            return new ResponseEntity<>(ErrorResponse.builder().errorMessage("Please provide a valid user id").build(), HttpStatus.NOT_FOUND);
        }

        Optional<AddressEntity> addressEntity = addressRepository.findById(addressId);
        if (!addressEntity.isPresent()) {
            return new ResponseEntity<>(ErrorResponse.builder().errorMessage("Please provide a valid address id").build(), HttpStatus.NOT_FOUND);
        }

        PersonEntity personToUpdate = addressRequestToPersonEntityTransformer
                .transformForEditAddress(request, objectMapper.readValue(
                        objectMapper.writeValueAsString(person.getBody())
                        , PersonEntity.class),
                        addressId);


        PersonEntity updatedPerson = personRepository.save(personToUpdate);

        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }


}
