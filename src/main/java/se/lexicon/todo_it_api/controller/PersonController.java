package se.lexicon.todo_it_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todo_it_api.data.service.PersonService;
import se.lexicon.todo_it_api.dto.forms.PersonForm;
import se.lexicon.todo_it_api.dto.views.PersonDto;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/api/v1/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<PersonDto>> getPersonList() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<PersonDto> addPerson(@Valid @RequestBody PersonForm personForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(personForm));
    }


}
