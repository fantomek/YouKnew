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

import androidx.appcompat.app.AppCompatActivity;

import com.example.youknow.model.Question;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    List<com.example.youknow.model.Question> questionList= new ArrayList<>();
    int i=0;
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
//        qusername=ProblemHandler.get_Username(getApplicationContext());

        Intent intent=getIntent();
        final String user_name=intent.getStringExtra("username");

        ll=findViewById(R.id.linearLayout);
        ask=findViewById(R.id.button_question);
        name=findViewById(R.id.text_name);
        name.setText(user_name);
        //跳转到提问新问题界面
        ask.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       Intent intent1 = new Intent(HomeActivity.this, AskActivity.class);
                                       intent1.putExtra("username", user_name);
                                       startActivity(intent1);
                                   }
    });
        //定义属性数组，保存problemlist值
          int length =questionList.size();
          final Button button[]=new Button[length];
          final TextView title[]=new TextView[length];
          final TextView content[]=new TextView[length];
          final String qusername[]=new String[length];

        // 显示question
          for (Question q : questionList) {
              XmlPullParser tparser = HomeActivity.this.getResources().getXml(R.layout.title_textview);
              AttributeSet tattributes = Xml.asAttributeSet(tparser);
              int type;
              try{
                  while ((type = tparser.next()) != XmlPullParser.START_TAG &&
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

              XmlPullParser cparser = HomeActivity.this.getResources().getXml(R.layout.content_textview);
              AttributeSet cattributes = Xml.asAttributeSet(cparser);
              int ctype;
              try{
                  while ((ctype = cparser.next()) != XmlPullParser.START_TAG &&
                          ctype != XmlPullParser.END_DOCUMENT) {
                      // Empty
                  }
                  if (ctype != XmlPullParser.START_TAG) {
                      Log.e("","the xml file is error!\n");
                  }
              } catch (XmlPullParserException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              }

//              LinearLayout ll=new LinearLayout(this);
//              LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
//                      ViewGroup.LayoutParams.MATCH_PARENT,
//                      ViewGroup.LayoutParams.MATCH_PARENT
//              );
//              ll.setOrientation(LinearLayout.HORIZONTAL);

              LinearLayout ll2=new LinearLayout(this);
              LinearLayout.LayoutParams lparams=new LinearLayout.LayoutParams(
                      ViewGroup.LayoutParams.MATCH_PARENT,
                      ViewGroup.LayoutParams.MATCH_PARENT
              );
              ll2.setOrientation(LinearLayout.VERTICAL);

              title[i] = new TextView(this,tattributes);
              LinearLayout.LayoutParams tparams=new LinearLayout.LayoutParams(HomeActivity.this,tattributes);
              title[i].setText((i+1)+":"+q.getProblem());

              content[i] = new TextView(this,cattributes);
              LinearLayout.LayoutParams cparams=new LinearLayout.LayoutParams(HomeActivity.this,cattributes);
              content[i].setText(" 描述："+q.getDescription());

//              button[i] = new Button(this);
//              button[i].setId(i + 1);
//              button[i].setText("详情");
//              button[i].setTextSize(12);

              ll2.addView(content[i],0,cparams);
              ll2.addView(title[i],0,tparams);
              ll.addView(ll2,lparams);

              qusername[i]=q.getUsername();
              i++;
          }

          for (int j = 0; j < length; j++) {
              final int finalj = j;
              final int finalJ = j;
              final int finalJ1 = j;
              content[j].setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent2 = new Intent(HomeActivity.this, QuestionContentActivity.class);
                      intent2.putExtra("q_title", title[finalJ].getText().toString().trim());
                      intent2.putExtra("q_id", finalj + 1);
                      intent2.putExtra("q_username", qusername[finalJ1]);
                      intent2.putExtra("q_info", content[finalJ].getText().toString().trim());
                      intent2.putExtra("username", user_name);
                      startActivity(intent2);
                  }
              });
          }


          };


      }
