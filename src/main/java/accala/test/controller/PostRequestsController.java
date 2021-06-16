package accala.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import accala.test.model.PersonRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import accala.test.service.PersonService;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
@Slf4j
public class PostRequestsController {
    private final PersonService personService;

    @PostMapping("/v1/person")
    public ResponseEntity savePerson(@RequestBody PersonRequest request){
        log.info("Saving user");
        return personService.savePerson(request);
    }
}
