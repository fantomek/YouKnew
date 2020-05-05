package com.example.youkonwapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.youkonwapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    List<Question> questionList=new ArrayList<Question>();
    private Button ask;//提问按钮
    private Button to_question;//问题详情
    private TextView name;//用户昵称
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //得到数据question数据
        questionList=ProblemHandler.get_problem(getApplicationContext());

        Intent intent=getIntent();
        final String username=intent.getStringExtra("username");

        ll=findViewById(R.id.linearLayout2);
        ask=findViewById(R.id.button_question);
        to_question=findViewById(R.id.button_toquestion);
        name=findViewById(R.id.username);
        //显示question
       for(Question q:questionList)
        {
            TextView tv=new TextView(this);
            tv.setText(q.getProblem());
            tv.setTextSize(10);
            ll.addView(tv);}

        name.setText(username);

        //跳转到提问新问题界面
        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this,"提问",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(HomeActivity.this,AskActivity.class);
                intent1.putExtra("username",username);
                startActivity(intent1);
            }
        });
        //跳转到问题详情页面
        to_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent2= new Intent(HomeActivity.this, QuestionContentActivity.class);
                intent2.putExtra("q_title","01");
                intent2.putExtra("q_id","01");
                intent2.putExtra("q_username","01");
                intent2.putExtra("q_info","01");
                startActivity(intent2);
            }
        });


    }
}
