package se.lexicon.todo_it_api.dto.views;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"TodoItemDtoList"})
public class PersonDto {

    private Integer personId;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private List<TodoItemDto> TodoItemDtoList;

}
