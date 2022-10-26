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

    @PostMapping("/add")
    public ResponseEntity<TodoItemDto> addTodoItem(@Valid @RequestBody TodoItemForm todoItemForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoItemService.create(todoItemForm));
    }
}
