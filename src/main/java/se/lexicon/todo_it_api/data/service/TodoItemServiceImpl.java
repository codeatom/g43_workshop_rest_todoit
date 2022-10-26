package se.lexicon.todo_it_api.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todo_it_api.data.PersonDAO;
import se.lexicon.todo_it_api.data.TodoItemDAO;
import se.lexicon.todo_it_api.data.converter.Converter;
import se.lexicon.todo_it_api.dto.forms.TodoItemForm;
import se.lexicon.todo_it_api.dto.views.TodoItemDto;
import se.lexicon.todo_it_api.model.entity.TodoItem;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoItemServiceImpl implements TodoItemService{

    private final TodoItemDAO todoItemDAO;
    private final PersonDAO personDAO;
    private final Converter converter;

    @Autowired
    public TodoItemServiceImpl(TodoItemDAO todoItemDAO, PersonDAO personDAO, Converter converter) {
        this.todoItemDAO = todoItemDAO;
        this.personDAO = personDAO;
        this.converter = converter;
    }

    @Override
    public TodoItemDto create(TodoItemForm todoItemForm) {
        if(todoItemForm == null ){
            throw new IllegalArgumentException ("todoItemForm is null");
        }

        TodoItem todoItem = new TodoItem();
        todoItem.setTitle(todoItemForm.getTitle());
        todoItem.setDescription(todoItemForm.getDescription());
        todoItem.setDeadLine(todoItemForm.getDeadLine());
        todoItem.setDone(todoItemForm.isDone());

        return converter.todoItemToDto(todoItem);
    }

    @Override
    public TodoItemDto findById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        return todoItemDAO.findById(id).isPresent() ?
                converter.todoItemToDto(todoItemDAO.findById(id).get()) :
                null;
    }

    @Override
    public List<TodoItemDto> findAll() {
        return converter.todoItemListToDtoList(todoItemDAO.findAll());
    }

    @Override
    public List<TodoItemDto> findByTitle(String title) {
        return converter.todoItemListToDtoList(todoItemDAO.findByTitleContains(title));
    }

    @Override
    public List<TodoItemDto> findAllUnassigned() {
        return null;
    }

    @Override
    public List<TodoItemDto> findAllUnfinishedAndOverdue() {
        return null;
    }

    @Override
    public List<TodoItemDto> findAllByPersonId(Integer personId) {
        return null;
    }

    @Override
    public List<TodoItemDto> findByDoneStatus(Boolean done) {
        return null;
    }

    @Override
    public List<TodoItemDto> findByDeadlineBetween(LocalDate before, LocalDate after) {
        return null;
    }

    @Override
    public List<TodoItemDto> findByDeadlineBefore(LocalDate before, LocalDate after) {
        return null;
    }

    @Override
    public List<TodoItemDto> findByDeadlineAfter(LocalDate before, LocalDate after) {
        return null;
    }

    @Override
    public TodoItemDto update(Integer id, TodoItemForm form) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return false;
    }
}
