package se.lexicon.todo_it_api.data.converter;

import org.springframework.stereotype.Component;
import se.lexicon.todo_it_api.dto.views.PersonDto;
import se.lexicon.todo_it_api.dto.views.TodoItemDto;
import se.lexicon.todo_it_api.model.entity.Person;
import se.lexicon.todo_it_api.model.entity.TodoItem;

import java.util.ArrayList;
import java.util.List;


@Component
public class ModelConverter implements Converter {

    @Override
    public PersonDto personToDto(Person entity) {
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();

        for(TodoItem todoItem : entity.getTodoItems()){
            TodoItemDto todoItemDto = new TodoItemDto(todoItem.getTodoId(), todoItem.getTitle(), todoItem.getDescription(), todoItem.getDeadLine(), todoItem.isDone());
            todoItemDtoList.add(todoItemDto);
        }

        return new PersonDto(entity.getPersonId(), entity.getFirstName(), entity.getLastName(), entity.getBirthDate(), todoItemDtoList);
    }

    @Override
    public List<PersonDto> personListToDtoList(List<Person> entities) {
        List<PersonDto> personDtoList = new ArrayList<>();

        for(Person person : entities){
            personDtoList.add(personToDto(person));
        }

        return personDtoList;
    }

    @Override
    public TodoItemDto todoItemToDto(TodoItem entity) {
        PersonDto personDto = new PersonDto(entity.getAssignee().getPersonId(), entity.getAssignee().getFirstName(), entity.getAssignee().getLastName(), entity.getAssignee().getBirthDate());

        return new TodoItemDto(entity.getTodoId(), entity.getTitle(), entity.getDescription(), entity.getDeadLine(), entity.isDone(), personDto);
    }

    @Override
    public List<TodoItemDto> todoItemListToDtoList(List<TodoItem> entities) {
        List<TodoItemDto> todoItemDtoList = new ArrayList<>();

        for(TodoItem todoItem : entities){
            todoItemDtoList.add(todoItemToDto(todoItem));
        }

        return todoItemDtoList;
    }

}
