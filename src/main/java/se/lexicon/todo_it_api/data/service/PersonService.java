package se.lexicon.todo_it_api.data.service;

import se.lexicon.todo_it_api.dto.forms.PersonForm;
import se.lexicon.todo_it_api.dto.views.PersonDto;

import java.util.List;

public interface PersonService {

    PersonDto create(PersonForm personForm);

    PersonDto findById (Integer id);

    List<PersonDto> findAll();

    List<PersonDto> findIdlePeople();

    PersonDto update(Integer id, PersonForm personForm);

    Boolean delete(Integer id);

    PersonDto addTodoItem(Integer personId, Integer todoItemId);

    PersonDto removeTodoItem(Integer personId, Integer todoItemId);
}
