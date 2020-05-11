package com.example.youknow;

import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youknow.model.Answer;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CurAnswer extends AppCompatActivity {

    List<Answer> answerList= new ArrayList<>();
    int i=0;
    private Button buttun_back;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_cur);

        Intent intent=getIntent();
//        final String problem=intent.getStringExtra("problem");
        final String q_info = intent.getStringExtra("q_info");
        final String q_title = intent.getStringExtra("q_title");
        final String q_username = intent.getStringExtra("q_username");
        final String user_name = intent.getStringExtra("username");
        answerList=AnswerHandler.get_answer(q_title.substring(2),getApplicationContext());

        buttun_back =findViewById(R.id.button_backthequestion);
        ll =findViewById(R.id.linearLayout2);

        int length =answerList.size();
        final Button button[]=new Button[length];
        final TextView answer[]=new TextView[length];
        buttun_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CurAnswer.this, QuestionContentActivity.class);
                intent.putExtra("username", user_name);
                intent.putExtra("q_title",q_title);
                intent.putExtra("q_info",q_info);
                intent.putExtra("q_username",q_username);
                startActivity(intent);
//                intent.putExtra("username", user_name);
            }
        });
        for (Answer a : answerList) {
                XmlPullParser aparser = CurAnswer.this.getResources().getXml(R.layout.anscontent_textview);
                AttributeSet aattributes = Xml.asAttributeSet(aparser);
                int type;
                try{
                    while ((type = aparser.next()) != XmlPullParser.START_TAG &&
                            type != XmlPullParser.END_DOCUMENT) {
                        // Empty
                    }
                    if (type != XmlPullParser.START_TAG) {
                        Log.e("","the xml file is error!\n");
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                LinearLayout ll2=new LinearLayout(this);
                LinearLayout.LayoutParams lparams=new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                );
                ll2.setOrientation(LinearLayout.HORIZONTAL);

                answer[i] = new TextView(this, aattributes);
                LinearLayout.LayoutParams aparams=new LinearLayout.LayoutParams(CurAnswer.this,aattributes);
                answer[i].setText(a.getAnswer());

                button[i] = new Button(this);
//                button[i].setId(i + 1);
                 button[i].setText("删除");
                 button[i].setTextSize(12);

                ll2.addView(answer[i],0,aparams);
                ll2.addView(button[i]);
                ll.addView(ll2,lparams);

//                qusername[i]=q.getUsername();
                i++;
            }
        for (int j = 0; j < length; j++) {
            final int finalJ = j;
            button[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int result = AnswerHandler.del_answer(user_name, q_title.substring(2), answer[finalJ].getText().toString().trim(), getApplicationContext());
                    if (result == 1) {
                        Toast.makeText(CurAnswer.this, "删除成功", Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(CurAnswer.this,CurAnswer.class);
                        intent1.putExtra("username", user_name);
                        intent1.putExtra("q_title",q_title);
                        intent1.putExtra("q_info",q_info);
                        intent1.putExtra("q_username",q_username);
                        startActivity(intent1);
                    }
                    else if (result == -1) {
                        Toast.makeText(CurAnswer.this, "非回答用户，删除失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
