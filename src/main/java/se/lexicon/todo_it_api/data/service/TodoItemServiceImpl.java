package se.lexicon.todo_it_api.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todo_it_api.data.TodoItemDAO;
import se.lexicon.todo_it_api.data.converter.Converter;
import se.lexicon.todo_it_api.dto.forms.TodoItemForm;
import se.lexicon.todo_it_api.dto.views.TodoItemDto;
import se.lexicon.todo_it_api.exception.AppResourceNotFoundException;
import se.lexicon.todo_it_api.model.entity.TodoItem;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoItemServiceImpl implements TodoItemService{

    private final TodoItemDAO todoItemDAO;
    private final Converter converter;

    @Autowired
    public TodoItemServiceImpl(TodoItemDAO todoItemDAO, Converter converter) {
        this.todoItemDAO = todoItemDAO;
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

        return converter.todoItemToDto(todoItemDAO.save(todoItem));
    }

    @Override
    public TodoItemDto findById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        if(todoItemDAO.findById(id).isPresent()){
            return converter.todoItemToDto(todoItemDAO.findById(id).get());
        }

        throw new AppResourceNotFoundException("Todo Item with id " + id + " not found.");
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
    public TodoItemDto update(Integer id, TodoItemForm todoItemForm) {
        if(todoItemForm == null || id < 1){
            throw new IllegalArgumentException ("TodoItemForm or id is invalid");
        }

        TodoItem todoItem = todoItemDAO.findById(id).isPresent() ? todoItemDAO.findById(id).get() : null;

        if(todoItem == null){
            return null;
        }

        todoItem.setTitle(todoItemForm.getTitle());
        todoItem.setDescription(todoItemForm.getDescription());
        todoItem.setDeadLine(todoItemForm.getDeadLine());
        todoItem.setDone(todoItemForm.isDone());
        todoItemDAO.save(todoItem);

        return converter.todoItemToDto(todoItem);
    }

    @Override
    public List<TodoItemDto> findAllUnassigned() {
        return converter.todoItemListToDtoList(todoItemDAO.findUnassignedTodoItems());
    }

    @Override
    public List<TodoItemDto> findAllUnfinishedAndOverdue() {
        return converter.todoItemListToDtoList(todoItemDAO.findAllUnfinishedAndOverdue());
    }

    @Override
    public List<TodoItemDto> findAllByPersonId(Integer personId) {
        return converter.todoItemListToDtoList(todoItemDAO.findByPersonId(personId));
    }

    @Override
    public List<TodoItemDto> findByDoneStatus(Boolean doneStatus) {
        return converter.todoItemListToDtoList(todoItemDAO.findByDoneStatus(doneStatus));
    }

    @Override
    public List<TodoItemDto> findByDeadlineBetween(LocalDate before, LocalDate after) {
        return converter.todoItemListToDtoList(todoItemDAO.findByDeadlineBetween(before, after));
    }

    @Override
    public List<TodoItemDto> findByDeadlineBefore(LocalDate date) {
        return converter.todoItemListToDtoList(todoItemDAO.findByDeadLineBefore(date));
    }

    @Override
    public List<TodoItemDto> findByDeadlineAfter(LocalDate date) {
        return converter.todoItemListToDtoList(todoItemDAO.findByDeadlineAfter(date));
    }

    @Override
    public Boolean delete(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        if(todoItemDAO.existsById(id)){
            removeAssociatedEntity(id);
            todoItemDAO.deleteById(id);
            return true;
        }

        return false;
    }

    private void removeAssociatedEntity(Integer id){
        todoItemDAO.findById(id).get().setAssignee(null);
    }
}
