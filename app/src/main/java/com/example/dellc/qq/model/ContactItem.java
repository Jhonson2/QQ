package com.example.dellc.qq.model;

/**
 * Created by dellc on 2017/9/16.
 */

public class ContactItem {
    private String fristLetter;
    private String imgUrl;
    private String userName;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public String getFristLetter() {
        return String.valueOf(userName.charAt(0)).toUpperCase();
    }

    public void setFristLetter(String fristLetter) {
        this.fristLetter = fristLetter;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
