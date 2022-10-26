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

    List<TodoItemDto> findByDoneStatus(Boolean done);

    List<TodoItemDto> findByDeadlineBetween(LocalDate before, LocalDate after);

    List<TodoItemDto> findByDeadlineBefore(LocalDate before, LocalDate after);

    List<TodoItemDto> findByDeadlineAfter(LocalDate before, LocalDate after);

    TodoItemDto update(Integer id, TodoItemForm form);

    Boolean delete(Integer id);
}
