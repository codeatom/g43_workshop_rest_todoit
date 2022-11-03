package se.lexicon.todo_it_api.dto.views;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class TodoItemDto {

    private final Integer todoId;
    private final String title;
    private final String description;
    private final LocalDate deadLine;
    private final boolean done;
    private PersonDto personDto;

    public TodoItemDto(Integer todoId, String title, String description, LocalDate deadLine, boolean done) {
        this.todoId = todoId;
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
    }

    public TodoItemDto(Integer todoId, String title, String description, LocalDate deadLine, boolean done, PersonDto assignee) {
        this(todoId, title, description, deadLine, done);
        this.personDto = assignee;
    }

}
