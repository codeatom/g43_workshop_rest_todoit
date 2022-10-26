package se.lexicon.todo_it_api.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonForm {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;
}
