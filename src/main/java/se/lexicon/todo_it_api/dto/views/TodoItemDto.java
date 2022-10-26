package se.lexicon.todo_it_api.dto.views;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import se.lexicon.todo_it_api.model.entity.Person;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"assignee"})
public class TodoItemDto {

    private Integer todoId;

    private String title;

    private String description;

    private LocalDate deadLine;

    private boolean done;

    private Person assignee;

}
