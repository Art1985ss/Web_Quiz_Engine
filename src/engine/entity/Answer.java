package engine.entity;

import java.util.ArrayList;
import java.util.List;

public class Answer {
    private List<Integer> answer;

    public Answer(List<Integer> answer) {
        this.answer = answer == null ? new ArrayList<>() : answer;
    }

    public Answer() {
        this.answer = new ArrayList<>();
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer == null ? new ArrayList<>() : answer;
    }



    @Override
    public String toString() {
        return String.format("User answers : %s", answer);
    }
}
