package com.example.youknow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private Button ask;//提问按钮
    private Button question;//问题详情
    private TextView name;//用户昵称
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent=getIntent();
        String username=intent.getStringExtra("username");

        ask=findViewById(R.id.button_question);
        question=findViewById(R.id.button_toquestion);
        name=findViewById(R.id.textView_name);
        name.setText(username);
        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
