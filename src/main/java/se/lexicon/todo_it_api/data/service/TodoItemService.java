package se.lexicon.todo_it_api.data.service;

import se.lexicon.todo_it_api.dto.forms.TodoItemForm;
import se.lexicon.todo_it_api.dto.views.TodoItemDto;

import java.time.LocalDate;
import java.util.List;

public interface TodoItemService {

    TodoItemDto create(TodoItemForm todoItemForm);

    TodoItemDto findById(Integer id);

    List<TodoItemDto> findAll();

    List<TodoItemDto> findByTitle(String title);

    List<TodoItemDto> findAllUnassigned();

    List<TodoItemDto> findAllUnfinishedAndOverdue();

    List<TodoItemDto> findAllByPersonId(Integer id);

    List<TodoItemDto> findByDoneStatus(Boolean doneStatus);

    List<TodoItemDto> findByDeadlineBetween(LocalDate before, LocalDate after);

    List<TodoItemDto> findByDeadlineBefore(LocalDate date);

    List<TodoItemDto> findByDeadlineAfter(LocalDate date);

    TodoItemDto update(Integer id, TodoItemForm TodoItemForm);

    Boolean delete(Integer id);
}
