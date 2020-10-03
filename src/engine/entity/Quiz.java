package engine.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import engine.validation.OptionsValidation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    @NotNull(message = "Title should not be null")
    private String title;
    @Column
    @NotNull(message = "Text should not be null")
    private String text;
    @Column
    @OptionsValidation
    //@Size(message = "Quiz should have at least 2 options", min = 2)
    @ElementCollection
    private List<String> options = new ArrayList<>();
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    private List<Integer> answer = new ArrayList<>();
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    public Quiz(long id) {
        this.id = id;
    }

    public Quiz() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public void addOption(String option) {
        options.add(option);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("id:%s title:%s text:%s options:%s answers:%s", id, title, text, options, answer);
    }
}
