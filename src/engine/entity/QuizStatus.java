package engine.entity;

public class QuizStatus {
    private static final String CORRECT_ANSWER = "Congratulations, you're right!";
    private static final String WRONG_ANSWER = "Wrong answer! Please, try again.";
    private final boolean success;

    public QuizStatus(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getFeedback() {
        return success ? CORRECT_ANSWER : WRONG_ANSWER;
    }
}
