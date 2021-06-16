package accala.test.controller;

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
public class DeleteRequestController {
    private final PersonService personService;

    @DeleteMapping("/v1/person/{id}")
    public ResponseEntity deletePerson(@PathVariable("id") final UUID id){
        log.info("Deleting user with id: {}", id);
        return personService.deletePerson(id);
    }
}
