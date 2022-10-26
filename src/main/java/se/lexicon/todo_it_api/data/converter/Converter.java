package se.lexicon.todo_it_api.data.converter;

import se.lexicon.todo_it_api.dto.views.PersonDto;
import se.lexicon.todo_it_api.dto.views.TodoItemDto;
import se.lexicon.todo_it_api.model.entity.Person;
import se.lexicon.todo_it_api.model.entity.TodoItem;

import java.util.List;


public interface Converter {

    PersonDto personToDto(Person entity);

    List<PersonDto> personListToDtoList(List<Person> entities);

    TodoItemDto todoItemToDto(TodoItem entity);

    List<TodoItemDto> todoItemListToDtoList(List<TodoItem> entities);

}
