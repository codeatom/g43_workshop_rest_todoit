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

    @GetMapping("/find-idle-people")
    public ResponseEntity<List<PersonDto>> getIdlePersonList() {
        return ResponseEntity.ok(personService.findIdlePeople());
    }

    @PostMapping("/add")
    public ResponseEntity<PersonDto> addPerson(@Valid @RequestBody PersonForm personForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(personForm));
    }

    @PutMapping("/add-todo-item/{personId}/{todoItemId}")
    public ResponseEntity<PersonDto> addTodoItem(@PathVariable("personId") Integer personId, @PathVariable("todoItemId") Integer todoItemId) {
        return ResponseEntity.ok(personService.addTodoItem(personId, todoItemId));
    }

    @PutMapping("/remove-todo-item/{personId}/{todoItemId}")
    public ResponseEntity<PersonDto> removeTodoItem(@PathVariable("personId") Integer personId, @PathVariable("todoItemId") Integer todoItemId) {
        return ResponseEntity.ok(personService.removeTodoItem(personId, todoItemId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable("id") Integer id, @Valid @RequestBody PersonForm personForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.update(id, personForm));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
