package com.example.youknow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AnsActivity extends AppCompatActivity {

    private Button confirm;
    private Button cancel;
    private TextView ans;
    private TextView question;
    private EditText contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ans);

        Intent intent=getIntent();
        final String q_info = intent.getStringExtra("q_info");
        final String q_title = intent.getStringExtra("q_title");
        final String q_username = intent.getStringExtra("q_username");
        final String user_name = intent.getStringExtra("username");

        confirm =findViewById(R.id.button_confirms);
        cancel =findViewById(R.id.button_cancels);
        ans =findViewById(R.id.ansing);
        question =findViewById(R.id.ques);
        contents =(EditText)findViewById(R.id.et_content);

        question.setText(q_title);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String answer = contents.getText().toString().trim();
                //传数据库
                int result=AnswerHandler.add_answer(user_name,q_title.substring(2),answer,getApplicationContext());
//                Log.e("user_name:", user_name);
//                Log.e("q_title", q_title.substring(2));
//                Log.e("answer", answer );
                if (result==1){
                    Toast.makeText(AnsActivity.this,"回答成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AnsActivity.this,QuestionContentActivity.class);
                    intent.putExtra("username", user_name);
                    intent.putExtra("q_title",q_title);
                    intent.putExtra("q_info",q_info);
                    intent.putExtra("q_username",q_username);
                    startActivity(intent);
//                    finish();
                }else  if (result==-1)
                {
                    Toast.makeText(AnsActivity.this,"回答失败",Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
