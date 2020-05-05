package com.example.youknow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionContentActivity extends AppCompatActivity {

    private Button button_backthelist;
    private Button button_answer;
    private TextView qusername;
    private TextView qinfo;
    private TextView qtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_content);

        Intent intent=getIntent();
        final String q_info=intent.getStringExtra("q_info");
        final String q_title=intent.getStringExtra("q_title");
        final String q_username=intent.getStringExtra("q_username");
        final String q_id=intent.getStringExtra("q_id");

        button_backthelist =(Button)findViewById(R.id.button_backthelist);
        button_answer =(Button)findViewById(R.id.button_answer);
        qinfo=findViewById(R.id.qinfo);
        qtitle=findViewById(R.id.title);
        qusername=findViewById(R.id.qusername);

//        qusername.setText(q_username);
//        qtitle.setText(q_title);
        qinfo.setText(q_info);

        button_backthelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //跳到回答问题页面
        button_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuestionContentActivity.this,AnsActivity.class);
//                intent.putExtra("question",q_info);
//                intent.putExtra("id",q_id);
                startActivity(intent);
            }
        });
    }
}
