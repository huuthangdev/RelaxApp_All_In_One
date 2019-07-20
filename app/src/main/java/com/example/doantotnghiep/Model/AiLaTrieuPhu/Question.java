package com.example.doantotnghiep.Model.AiLaTrieuPhu;
import java.util.Map;

public class Question {
    private Map<String, String> answer;
    private String question;
    private String rightAnswer;

    public Map<String, String> getAnswer() {
        return answer;
    }

    public void setAnswer(Map<String, String> answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Question() {

    }
}
