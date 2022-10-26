package se.lexicon.todo_it_api.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todo_it_api.data.PersonDAO;
import se.lexicon.todo_it_api.data.converter.Converter;
import se.lexicon.todo_it_api.dto.forms.PersonForm;
import se.lexicon.todo_it_api.dto.views.PersonDto;
import se.lexicon.todo_it_api.model.entity.Person;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonDAO personDAO;
    private final Converter converter;

    @Autowired
    public PersonServiceImpl(PersonDAO personDAO, Converter converter) {
        this.personDAO = personDAO;
        this.converter = converter;
    }

    @Override
    public PersonDto create(PersonForm personForm) {
        if(personForm == null ){
            throw new IllegalArgumentException ("personForm is null");
        }

        Person person = new Person();
        person.setFirstName(personForm.getFirstName());
        person.setLastName(personForm.getLastName());
        person.setBirthDate(personForm.getBirthDate());

        return converter.personToDto(person);
    }

    @Override
    public PersonDto findById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        return personDAO.findById(id).isPresent() ?
                converter.personToDto(personDAO.findById(id).get()) :
                null;
    }

    @Override
    public List<PersonDto> findAll() {
        return converter.personListToDtoList(personDAO.findAll());
    }

    @Override
    public List<PersonDto> findIdlePeople() {
        return null;
    }

    @Override
    public PersonDto update(Integer id) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }

    @Override
    public PersonDto addTodoItem(Integer personId, Integer todoItemId) {
        return null;
    }

    @Override
    public PersonDto removeTodoItem(Integer personId, Integer todoItemId) {
        return null;
    }
}
