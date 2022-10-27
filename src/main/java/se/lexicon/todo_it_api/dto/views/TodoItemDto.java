package se.lexicon.todo_it_api.dto.views;

import lombok.Getter;
import lombok.Setter;
import se.lexicon.todo_it_api.model.entity.Person;

import java.time.LocalDate;


@Getter
@Setter
public class TodoItemDto {

    private Integer todoId;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private Person assignee;

    public TodoItemDto() {
    }

    public TodoItemDto(Integer todoId, String title, String description, LocalDate deadLine, boolean done) {
        this.todoId = todoId;
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
    }

    public TodoItemDto(Integer todoId, String title, String description, LocalDate deadLine, boolean done, Person assignee) {
        this(todoId, title, description, deadLine, done);
        this.assignee = assignee;
    }

}
