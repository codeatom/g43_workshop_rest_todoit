package se.lexicon.todo_it_api.dto.views;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class PersonDto {

    private final Integer personId;

    private final String firstName;

    private final String lastName;

    private final LocalDate birthDate;

    private List<TodoItemDto> TodoItemDtoList;

    public PersonDto(Integer personId, String firstName, String lastName, LocalDate birthDate) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public PersonDto(Integer personId, String firstName, String lastName, LocalDate birthDate, List<TodoItemDto> todoItemDtoList) {
        this(personId, firstName, lastName, birthDate);
        TodoItemDtoList = todoItemDtoList;
    }

}
