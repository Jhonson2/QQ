package com.example.dellc.qq.model;

import cn.bmob.v3.BmobUser;

/**
 * Created by dellc on 2017/9/12.
 */

public class User extends BmobUser{
    public static final String TAG="User";

    public User(String userName,String password){
        setUsername(userName);
        setPassword(password);
    }
}
