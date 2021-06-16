package accala.test.controller;

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
public class GetRequestsController {
    private final PersonService personService;

    @GetMapping("/v1/person/{id}")
    public ResponseEntity fetchPerson(@PathVariable("id") final UUID id){
        log.info("Fetching user with id: {}", id);
        return personService.fetchPerson(id);
    }

    @GetMapping("/v1/person/count")
    public ResponseEntity getCountOfPersons(){
        log.info("Fetching persons count");
        return personService.getPersonsCount();
    }

    @GetMapping("/v1/persons")
    public ResponseEntity getAllPersons(){
        log.info("Fetching all persons ");
        return personService.getAllPersons();
    }


}
