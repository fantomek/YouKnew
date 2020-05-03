package com.example.youkonwapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button_load;
    private Button button_register;
    private TextView textView_title;
    private TextView textView_register;
    private EditText editText_user;
    private EditText editText_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_load =findViewById(R.id.button_load);
        button_register =findViewById(R.id.button_register);
        textView_title =findViewById(R.id.textView_title);
        textView_register =findViewById(R.id.textView_register);
        editText_user =findViewById(R.id.editText_user);
        editText_password =findViewById(R.id.editText_password);

    }
}
