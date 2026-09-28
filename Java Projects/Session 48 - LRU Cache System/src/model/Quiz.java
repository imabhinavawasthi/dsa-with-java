package model;

import java.util.List;

public class Quiz {
    private final int id;
    private final String title;
    private final String topic;
    private final List<String> questions;

    public Quiz(int id, String title, String topic, List<String> questions) {
        this.id = id;
        this.title = title;
        this.topic = topic;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTopic() {
        return topic;
    }

    public List<String> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return String.format("[Quiz #%d] '%s' (%s) with %d questions", id, title, topic, questions.size());
    }
}
