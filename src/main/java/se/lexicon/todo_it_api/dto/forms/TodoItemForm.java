package se.lexicon.todo_it_api.dto.forms;

import lombok.*;
import se.lexicon.todo_it_api.util.ValidationMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoItemForm {

    @NotBlank(message = ValidationMessages.IS_REQUIRED)
    @Size(min = 10, max = 100, message = ValidationMessages.BETWEEN_10_AND_100_LETTERS)
    private String title;

    @NotBlank(message = ValidationMessages.IS_REQUIRED)
    @Size(min = 10, max = 1000, message = ValidationMessages.BETWEEN_10_AND_1000_LETTERS)
    private String description;

    @NotNull(message = ValidationMessages.IS_REQUIRED)
    private LocalDate deadLine;

    private boolean done;

}
