package com.example.android.simplediscgolf;

/**
 * Created by ufox on 30.1.2017.
 */

public class PlayerItem {

    private String mPlayer;

    private String mMail;

    public PlayerItem() {
        mPlayer = "";
        mMail = "";
    }

    public PlayerItem(String player, String mail) {
        mPlayer = player;
        mMail = mail;
    }

    public PlayerItem(String player, String mail, String activity) {
        mPlayer = player;
        mMail = mail;
    }

    String getPlayer() {
        return mPlayer;
    }

    String getMail() {
        return mMail;
    }

    void setPlayer(String player) {
        mPlayer = player;
    }

    void setMail(String mail) {
        mMail = mail;
    }
}

