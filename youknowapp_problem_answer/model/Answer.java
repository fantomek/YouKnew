package com.example.youkonwapp.model;

public class Answer {
    private int id;
    private String username;
    private String problem;
    private String answer;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getProblem() {
        return problem;
    }
    public void setProblem(String problem) {
        this.problem = problem;
    }
    public void  setAnswer(String answer){
        this.answer=answer;
    }
    public String getAnswer() {
        return answer;
    }
}
