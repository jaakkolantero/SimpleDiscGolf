package com.example.ufox.simplediscgolf;

/**
 * Created by ufox on 2.3.2017.
 */

public class PlayerObject {

    private String mPlayer;

    private String mMail;

    private boolean mSelected;

    public PlayerObject() {
        mPlayer = "";
        mMail = "";
        mSelected = false;
    }

    public PlayerObject(String player, String mail) {
        mPlayer = player;
        mMail = mail;
        mSelected = false;
    }

    String getPlayer() {
        return mPlayer;
    }

    String getMail() {
        return mMail;
    }

    boolean getSelected() {
        return mSelected;
    }

    void setPlayer(String player) {
        mPlayer = player;
    }

    void setMail(String mail) {
        mMail = mail;
    }

    void setSelected(boolean selected) {
        mSelected = selected;
    }

}
