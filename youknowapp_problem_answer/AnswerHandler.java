package com.example.youkonwapp;

import android.content.Context;

import com.example.youkonwapp.model.Answer;

import java.util.List;

public class AnswerHandler {
    public static int add_problem(String name, String problem, String Answer, Context context){
        Answer answer=new Answer();
        answer.setAnswer(Answer);
        answer.setProblem(problem);
        answer.setUsername(name);
        return SqliteDB.getInstance(context).saveAnswer(answer);
    }
    public static List<Answer> get_answer(String problem, Context context){
        return SqliteDB.getInstance(context).get_answer(problem);
    }
}
