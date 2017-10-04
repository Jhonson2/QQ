package com.example.dellc.qq.event;

/**
 * Created by dellc on 2017/10/4.
 */

public class AddFriendEvent {

    public String userName;

    public String reason;//理由

    public AddFriendEvent(String userName,String reason){
        this.userName=userName;
        this.reason=reason;

    }
}
