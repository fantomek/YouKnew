package com.example.youkonwapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.youkonwapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button reg;
    private Button login;
    private EditText count;
    private EditText pwd;
    private TextView state;
    private List<User> userList;
    private List<User> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reg= (Button) findViewById(R.id.button_register);
        login= (Button) findViewById(R.id.button_load);
        count= (EditText) findViewById(R.id.editText_user);
        pwd= (EditText) findViewById(R.id.editText_password);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=count.getText().toString().trim();
                String pass=pwd.getText().toString().trim();
                int result=UserHandler.add_user(name,pass,getApplicationContext());
                if (result==1){
                    Toast.makeText(MainActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                }else  if (result==-1)
                {
                    Toast.makeText(MainActivity.this,"用户名已经存在！",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"异常 ",Toast.LENGTH_SHORT).show();
                }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=count.getText().toString().trim();
                String pass=pwd.getText().toString().trim();
                int result=UserHandler.query(name,pass,getApplicationContext());
                if (result==1)
                {
                    Toast.makeText(MainActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                }
                else if (result==0){
                    Toast.makeText(MainActivity.this,"用户名不存在！",Toast.LENGTH_SHORT).show();
                }
                else if(result==-1)
                {
                    Toast.makeText(MainActivity.this,"密码错误！",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
