package com.example.youkonwapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AskActivity extends AppCompatActivity {

    private Button confirm;
    private Button cancel;
    private TextView ask;
    private EditText title;
    private EditText contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        Intent intent=getIntent();
        final String username=intent.getStringExtra("username");

        confirm =findViewById(R.id.button_confirm);
        cancel =findViewById(R.id.button_cancel);
        ask =findViewById(R.id.asking);
        title =(EditText)findViewById(R.id.et_title);//提的问题，我命名的title
        contents =(EditText)findViewById(R.id.et_contents);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thetitle = title.getText().toString().trim();
                String thecontents = contents.getText().toString().trim();
                int result=ProblemHandler.add_problem(username,thetitle,thecontents,getApplicationContext());
                if (result==1){
                    Toast.makeText(AskActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AskActivity.this,HomeActivity.class);
                    startActivity(intent);
                    //finish();
                }else  if (result==-1)
                {
                    Toast.makeText(AskActivity.this,"提问失败",Toast.LENGTH_SHORT).show();
                }

                // finish();
            }
        });//确定按钮
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });//取消按钮，返回主页面
    }
}
