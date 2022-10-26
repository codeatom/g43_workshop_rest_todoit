package se.lexicon.todo_it_api.data.converter;

import se.lexicon.todo_it_api.dto.views.PersonDto;
import se.lexicon.todo_it_api.dto.views.TodoItemDto;
import se.lexicon.todo_it_api.model.entity.Person;
import se.lexicon.todo_it_api.model.entity.TodoItem;

import java.util.ArrayList;
import java.util.List;


public class ModelConverter implements Converter {

    @Override
    public PersonDto personToDto(Person entity) {
        return new PersonDto(entity.getPersonId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthDate(),
                entity.getTodoItems()
        );
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
        return new TodoItemDto(
                entity.getTodoId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getDeadLine(),
                entity.isDone(),
                entity.getAssignee()
        );
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
