package se.lexicon.todo_it_api.dto.views;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import se.lexicon.todo_it_api.model.entity.TodoItem;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"todoItems"})
public class PersonDto {

    private Integer personId;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private List<TodoItem> todoItems;

}
