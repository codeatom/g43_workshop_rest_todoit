package se.lexicon.todo_it_api.dto.forms;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoItemForm {

    private String title;

    private String description;

    private LocalDate deadLine;

    private boolean done;

}
