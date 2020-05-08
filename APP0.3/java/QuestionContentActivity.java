package com.example.youknow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionContentActivity extends AppCompatActivity {

    private Button button_backthelist;
    private Button button_answer;
    private Button button_delete;

    private TextView qusername;
    private TextView qinfo;
    private TextView qtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_content);

        Intent intent = getIntent();
        final String q_info = intent.getStringExtra("q_info");
        final String q_title = intent.getStringExtra("q_title");
        final String q_username = intent.getStringExtra("q_username");
        final String q_id = intent.getStringExtra("q_id");
        final String user_name = intent.getStringExtra("username");

        button_backthelist = (Button) findViewById(R.id.button_backthelist);
        button_answer = (Button) findViewById(R.id.button_answer);
        button_delete = (Button) findViewById(R.id.button_delete);
        qinfo = findViewById(R.id.qinfo);
        qtitle = findViewById(R.id.qtitle);
        qusername = findViewById(R.id.qusername);

        qusername.setText(q_username);
        qtitle.setText(q_title);
        qinfo.setText(q_info);

        button_backthelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionContentActivity.this, HomeActivity.class);
                intent.putExtra("username", user_name);
                startActivity(intent);
                intent.putExtra("username", user_name);
            }
        });
        //跳到回答问题页面
        button_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionContentActivity.this, AnsActivity.class);
//                intent.putExtra("question",q_info);
//                intent.putExtra("id",q_id);
                startActivity(intent);
            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = ProblemHandler.del_problem(user_name, q_title.substring(2), q_info.substring(3), getApplicationContext());
                Log.e("user_name",user_name);
                Log.e("q_title",q_title);
                Log.e("uq_info",q_info);
                if (result == 1) {
                    Toast.makeText(QuestionContentActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuestionContentActivity.this, HomeActivity.class);
                    intent.putExtra("username", user_name);
                    startActivity(intent);
                    //finish();
                }
                else if (result == -1) {
                    Toast.makeText(QuestionContentActivity.this, "非提问用户，删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}