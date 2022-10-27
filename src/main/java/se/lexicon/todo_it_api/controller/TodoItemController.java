package se.lexicon.todo_it_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todo_it_api.data.service.TodoItemService;
import se.lexicon.todo_it_api.dto.forms.TodoItemForm;
import se.lexicon.todo_it_api.dto.views.TodoItemDto;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/api/v1/todo-item")
public class TodoItemController {

    private final TodoItemService todoItemService;

    @Autowired
    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<TodoItemDto>> getTodoItemList() {
        return ResponseEntity.ok(todoItemService.findAll());
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<TodoItemDto> getTodoItemById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(todoItemService.findById(id));
    }

    @GetMapping("/find-by-title/{title}")
    public ResponseEntity<List<TodoItemDto>> getTodoItemByTitle(@PathVariable("title") String title) {
        return ResponseEntity.ok(todoItemService.findByTitle(title));
    }

    @GetMapping("/find-by-person-id/{id}")
    public ResponseEntity<List<TodoItemDto>> getAllByPersonId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(todoItemService.findAllByPersonId(id));
    }

    @GetMapping("/find-by-done-status/{doneStatus}")
    public ResponseEntity<List<TodoItemDto>> getAllByDoneStatus(@PathVariable("doneStatus") Boolean doneStatus) {
        return ResponseEntity.ok(todoItemService.findByDoneStatus(doneStatus));
    }

    @GetMapping("/find-all-unassigned")
    public ResponseEntity<List<TodoItemDto>> getAllUnassigned() {
        return ResponseEntity.ok(todoItemService.findAllUnassigned());
    }

    @GetMapping("/find-all-unfinished")
    public ResponseEntity<List<TodoItemDto>> getAllUnfinished() {
        return ResponseEntity.ok(todoItemService.findAllUnfinishedAndOverdue());
    }

    @GetMapping("/find-by-deadline-between/{before}/{after}")
    public ResponseEntity<List<TodoItemDto>> getByDeadlineBetween(@PathVariable("before") String before, @PathVariable("after") String after) {
        return ResponseEntity.ok(todoItemService.findByDeadlineBetween(LocalDate.parse(before), LocalDate.parse(after)));
    }

    @GetMapping("/find-by-deadline-before/{date}")
    public ResponseEntity<List<TodoItemDto>> getByDeadlineBefore(@PathVariable("date") String date) {
        return ResponseEntity.ok(todoItemService.findByDeadlineBefore(LocalDate.parse(date)));
    }

    @GetMapping("/find-by-deadline-after/{date}")
    public ResponseEntity<List<TodoItemDto>> getByDeadlineAfter(@PathVariable("date") String date) {
        return ResponseEntity.ok(todoItemService.findByDeadlineBefore(LocalDate.parse(date)));
    }

    @PostMapping("/add")
    public ResponseEntity<TodoItemDto> addTodoItem(@Valid @RequestBody TodoItemForm todoItemForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoItemService.create(todoItemForm));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TodoItemDto> updateTodoItem(@PathVariable("id") Integer id, @Valid @RequestBody TodoItemForm todoItemForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoItemService.update(id, todoItemForm));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        todoItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
