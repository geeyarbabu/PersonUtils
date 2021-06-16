package accala.test.controller;

import accala.test.model.AddressRequest;
import accala.test.model.PersonRequest;
import accala.test.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
@Slf4j
public class PutRequestsController {
    private final PersonService personService;

    @PutMapping("/v1/person/{id}")
    public ResponseEntity updatePerson(@RequestBody final PersonRequest request,
                                     @PathVariable("id") final UUID id){
        log.info("Updating user with id: {}", id);
        return personService.updatePerson(request, id);
    }

    @PutMapping("/v1/person/{id}/address")
    public ResponseEntity updatePersonAddress(@RequestBody final AddressRequest request,
                                       @PathVariable("id") final UUID id){
        log.info("Updating user address with id: {}", id);
        return personService.updatePersonAddress(request, id);
    }

    @PutMapping("/v1/person/{id}/address/{address_id}")
    public ResponseEntity editAddressForPerson(@RequestBody final AddressRequest request,
                                              @PathVariable("id") final UUID id,
                                               @PathVariable("address_id") final UUID addressId){
        log.info("Updating address with id:{} associated with user id: {}",addressId, id);
        return personService.editPersonAddress(request, id, addressId);
    }
}
