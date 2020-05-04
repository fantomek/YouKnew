package com.example.youkonwapp;

import android.content.Context;

import com.example.youkonwapp.model.Question;

import java.util.List;

public class ProblemHandler {
    public static int add_problem(String name, String problem, String description,Context context){
        Question question=new Question();
        question.setUsername(name);
        question.setProblem(problem);
        question.setDescription(description);
        return SqliteDB.getInstance(context).saveQuestion(question);
    }
    public static List<Question> get_problem(Context context){
        return SqliteDB.getInstance(context).get_problem();
    }
}
