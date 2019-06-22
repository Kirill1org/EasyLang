package com.easylang;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private List<Question> questions = new ArrayList<>();
    private List<Question> rightQuestions = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getRightQuestions() {
        return rightQuestions;
    }

    public void setRightQuestions(List<Question> rightQuestions) {
        this.rightQuestions = rightQuestions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void addRightQuestion(Question question) {
        rightQuestions.add(question);
    }
}
