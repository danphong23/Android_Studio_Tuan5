package com.example.tuan05;

public interface MainCallbacks {
    public void onMsgFromFragIToMain (String sender, String in);
    public void onMsgFromFragLToMain (String sender, String[] data);
}
