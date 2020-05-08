package com.example.youknow;

import android.content.Context;

import com.example.youknow.model.User;

public class UserHandler {

    public static int add_user(String name, String pass, Context context){
        User user=new User();
        user.setUsername(name);
        user.setUserpwd(pass);
        return SqliteDB.getInstance(context).saveUser(user);
    }
    public static int query(String name, String pass, Context context){
        return SqliteDB.getInstance(context).Quer(pass,name);
    }
}
