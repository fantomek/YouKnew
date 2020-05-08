package com.example.youknow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        //用于接受从问题界面传过来的问题内容**************
        Intent intent=getIntent();
        String thequestion=intent.getStringExtra("question");
        //***********************************************

        confirm =findViewById(R.id.button_confirms);
        cancel =findViewById(R.id.button_cancels);
        ans =findViewById(R.id.ansing);
        question =findViewById(R.id.ques);
        contents =(EditText)findViewById(R.id.et_content);

        question.setText(thequestion);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String thecontents = contents.getText().toString().trim();
                //传数据库


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
