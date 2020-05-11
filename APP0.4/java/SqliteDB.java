package com.example.youknow;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

//import com.example.youknow.model.Answer;
import com.example.youknow.model.Question;
import com.example.youknow.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SqliteDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "sqlite_dbname";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static SqliteDB sqliteDB;

    private SQLiteDatabase db;

    private SqliteDB(Context context) {
        OpenHelper dbHelper = new OpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取SqliteDB实例
     *
     * @param context
     */
    public synchronized static SqliteDB getInstance(Context context) {
        if (sqliteDB == null) {
            Log.i("get_instance","NULL");
            sqliteDB = new SqliteDB(context);
        }
        Log.i("get_instance","not NULL");
        return sqliteDB;
    }

    /**
     * 将User实例存储到数据库。
     */
    public int saveUser(User user) {
        if (user != null) {
            Cursor cursor = db.rawQuery("select * from User where username=?", new String[]{user.getUsername().toString()});
            if (cursor.getCount() > 0) {
                cursor.close();
                return -1;
            } else {
                try {
                    db.execSQL("insert into User(username,userpwd) values(?,?) ", new String[]{user.getUsername().toString(), user.getUserpwd().toString()});
                    cursor.close();
                } catch (Exception e) {
                    Log.d("错误", e.getMessage().toString());
                }
                return 1;
            }
        } else {
            return 0;
        }
    }

    /**
     * 从数据库读取User信息。
     */
    public List<User> loadUser() {
        List<User> list = new ArrayList<User>();
        Cursor cursor = db
                .query("User", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setUsername(cursor.getString(cursor
                        .getColumnIndex("username")));
                user.setUserpwd(cursor.getString(cursor

                        .getColumnIndex("userpwd")));
                list.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public int Quer(String pwd, String name) {
        HashMap<String, String> hashmap = new HashMap<String, String>();
        Cursor cursor = db.rawQuery("select * from User where username=?", new String[]{name});

        // hashmap.put("name",db.rawQuery("select * from User where name=?",new String[]{name}).toString());
        if (cursor.getCount() > 0) {
            Cursor pwdcursor = db.rawQuery("select * from User where userpwd=? and username=?", new String[]{pwd, name});
            if (pwdcursor.getCount() > 0) {
                cursor.close();
                return 1;
            } else {
                cursor.close();
                return -1;
            }
        } else {
            return 0;
        }
    }
    public List<Question> get_problem() {
        List<Question> list = new ArrayList<Question>();
        Log.i("get_problem","create list");
        Cursor cursor = db
                .query("Problem", null, null, null, null, null, null);
        Log.i("get_problem","cursor");
        if (cursor.moveToFirst()) {
            Log.i("get_problem","if not null");
            do {
                Log.i("get_problem","start loop");
                Question question = new Question();
                question.setUsername(cursor.getString(cursor
                        .getColumnIndex("username")));
                Log.i("get_problem","read username");
                question.setProblem(cursor.getString(cursor
                        .getColumnIndex("problem")));
                Log.i("get_problem","read problem");
                question.setDescription(cursor.getString(cursor
                        .getColumnIndex("description")));
                Log.i("get_problem","read description");
                list.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public int saveQuestion(Question question) {
        if (question != null) {
            Cursor cursor = db.rawQuery("select * from Problem where problem=?", new String[]{question.getProblem().toString()});
            if (cursor.getCount() > 0) {
                cursor.close();
                return -1;
            } else {
                try {
                    db.execSQL("insert into Problem(username,problem,description) values(?,?,?) ", new String[]{question.getUsername().toString(),
                            question.getProblem().toString(),question.getDescription().toString()});
                    cursor.close();
                } catch (Exception e) {
                    Log.d("错误", e.getMessage().toString());
                }
                return 1;
            }
        } else {
            return 0;
        }
    }

    public int delQuestion(Question question){
        if (question != null) {
            Cursor cursor = db.rawQuery("select * from Problem where problem=? and username=?", new String[]{question.getProblem(),question.getUsername()});
            if (cursor.getCount() == 0 ) {
                cursor.close();
                return -1;
            } else {
                try {
                    db.execSQL("delete from Problem where username=? and problem=? and description=?", new String[]{question.getUsername().toString(),
                            question.getProblem().toString(),question.getDescription().toString()});
//                    db.execSQL("delete from Answer where problem=? ", new String[]{question.getProblem()});
//                    cursor.close();
                } catch (Exception e) {
                    Log.d("错误", e.getMessage().toString());
                }
                return 1;
            }
        } else {
            return 0;
        }
    }

//    public List<Answer> get_answer(String Problem) {
//        List<Answer> list = new ArrayList<Answer>();
//        Cursor cursor = db
//                .query("Answer", null, "problem=?", new String[]{Problem}, null, null, null);
//        if (cursor.moveToFirst()) {
//            do {
//                Answer answer = new Answer();
//                answer.setUsername(cursor.getString(cursor
//                        .getColumnIndex("username")));
//                answer.setProblem(cursor.getString(cursor
//                        .getColumnIndex("problem")));
//                answer.setAnswer(cursor.getString(cursor
//                        .getColumnIndex("answer")));
//                list.add(answer);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return list;
//    }
//    public int saveAnswer(Answer answer) {
//        if (answer != null) {
//            Cursor cursor = db.rawQuery("select * from Answer where answer=? and problem=?", new String[]{answer.getAnswer(),answer.getProblem()});
//            if (cursor.getCount() > 0) {
//                cursor.close();
//                return -1;
//            } else {
//                try {
//                    db.execSQL("insert into Answer(username,answer,problem) values(?,?,?) ", new String[]{answer.getUsername().toString(),
//                            answer.getAnswer().toString(),answer.getProblem().toString()});
//                    cursor.close();
//                } catch (Exception e) {
//                    Log.d("错误", e.getMessage().toString());
//                }
//                return 1;
//            }
//        } else {
//            return 0;
//        }
//    }
//    public int delAnswer(Answer answer) {
//        if (answer != null) {
//            Cursor cursor = db.rawQuery("select * from Answer where answer=? and problem=? and username=?", new String[]{answer.getAnswer(),answer.getProblem(),answer.getUsername()});
//            if (cursor.getCount() == 0) {
//                cursor.close();
//                return -1;
//            } else {
//                try {
//                    db.execSQL("delete from Answer where username=? and answer=? and problem=?", new String[]{answer.getUsername().toString(),
//                            answer.getAnswer().toString(),answer.getProblem().toString()});
//                    cursor.close();
//                } catch (Exception e) {
//                    Log.d("错误", e.getMessage().toString());
//                }
//                return 1;
//            }
//        } else {
//            return 0;
//        }
//    }
//
}
//
