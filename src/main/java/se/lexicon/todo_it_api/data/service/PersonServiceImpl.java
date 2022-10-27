package se.lexicon.todo_it_api.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todo_it_api.data.dao.PersonDAO;
import se.lexicon.todo_it_api.data.dao.TodoItemDAO;
import se.lexicon.todo_it_api.data.converter.Converter;
import se.lexicon.todo_it_api.dto.forms.PersonForm;
import se.lexicon.todo_it_api.dto.views.PersonDto;
import se.lexicon.todo_it_api.exception.AppResourceNotFoundException;
import se.lexicon.todo_it_api.model.entity.Person;
import se.lexicon.todo_it_api.model.entity.TodoItem;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonDAO personDAO;
    private final TodoItemDAO todoItemDAO;
    private final Converter converter;

    @Autowired
    public PersonServiceImpl(PersonDAO personDAO, TodoItemDAO todoItemDAO, Converter converter) {
        this.personDAO = personDAO;
        this.todoItemDAO = todoItemDAO;
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

        return converter.personToDto(personDAO.save(person));
    }

    @Override
    public PersonDto findById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        if(personDAO.findById(id).isPresent()){
            return converter.personToDto(personDAO.findById(id).get());
        }

        throw new AppResourceNotFoundException("Person with id " + id + " not found.");
    }

    @Override
    public List<PersonDto> findAll() {
        return converter.personListToDtoList(personDAO.findAll());
    }

    @Override
    public PersonDto update(Integer id, PersonForm personForm) {
        if(personForm == null || id < 1){
            throw new IllegalArgumentException ("personForm or id is invalid");
        }

        Person person = personDAO.findById(id).isPresent() ? personDAO.findById(id).get() : null;

        if(person == null){
            throw new AppResourceNotFoundException("Person with id " + id + " not found.");
        }

        person.setFirstName(personForm.getFirstName());
        person.setLastName(personForm.getLastName());
        person.setBirthDate(personForm.getBirthDate());
        personDAO.save(person);

        return converter.personToDto(person);
    }

    @Override
    public List<PersonDto> findIdlePeople() {
        return converter.personListToDtoList(personDAO.findIdlePeople());
    }

    @Override
    public PersonDto addTodoItem(Integer personId, Integer todoItemId) {
        TodoItem todoItem = todoItemDAO.findById(todoItemId).isPresent() ?
                todoItemDAO.findById(todoItemId).get() :
                null;

        if(todoItem == null){
            throw new AppResourceNotFoundException("Todo Item with id " + todoItemId + " not found.");
        }

        Person person = personDAO.findById(personId).isPresent() ?
                personDAO.findById(personId).get() :
                null;

        if (person == null){
            throw new AppResourceNotFoundException("Person with id " + personId + " not found.");
        }

        person.addTodoItem(todoItem);
        personDAO.save(person);
        return converter.personToDto(person);
    }

    @Override
    public PersonDto removeTodoItem(Integer personId, Integer todoItemId) {
        TodoItem todoItem = todoItemDAO.findById(todoItemId).isPresent() ?
                todoItemDAO.findById(todoItemId).get() :
                null;

        if(todoItem == null){
            throw new AppResourceNotFoundException("Todo Item with id " + todoItemId + " not found.");
        }

        Person person = personDAO.findById(personId).isPresent() ?
                personDAO.findById(personId).get() :
                null;

        if (person == null){
            throw new AppResourceNotFoundException("Person with id " + personId + " not found.");
        }

        person.removeTodoItem(todoItem);
        personDAO.save(person);
        return converter.personToDto(person);
    }

    @Override
    public Boolean delete(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        if(personDAO.existsById(id)){
            removeAssociatedEntity(id);
            personDAO.deleteById(id);
            return true;
        }

        return false;
    }

    private void removeAssociatedEntity(Integer id){
        personDAO.findById(id).get().setTodoItems(null);
    }
}
