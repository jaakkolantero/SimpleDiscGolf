package com.example.android.simplediscgolf;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ufox on 30.1.2017.
 */

public class PlayerItem {

    @SerializedName("player")
    private String mPlayer;

    @SerializedName("mail")
    private String mMail;

    @SerializedName("selected")
    private boolean mSelected;

    public PlayerItem() {
        mPlayer = "";
        mMail = "";
        mSelected = false;
    }

    public PlayerItem(String player, String mail) {
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

    void setPlayer(String player) {
        mPlayer = player;
    }

    void setMail(String mail) {
        mMail = mail;
    }

    boolean getSelected() {
        return mSelected;
    }
    void setSelected(boolean selected) {
        mSelected = selected;
    }
}

