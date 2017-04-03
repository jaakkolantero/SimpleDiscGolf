package com.example.ufox.simplediscgolf;

import com.google.gson.annotations.Expose;

import java.util.UUID;

/**
 * Created by ufox on 2.3.2017.
 */

public class PlayerObject {

    private String mPlayer;

    private String mMail;

    private boolean mSelected;

    private String mPlayerId;

    public PlayerObject() {
        mPlayer = "";
        mMail = "";
        mSelected = false;
        mPlayerId = UUID.randomUUID().toString();
    }

    public PlayerObject(String player, String mail) {
        mPlayer = player;
        mMail = mail;
        mSelected = false;
        mPlayerId = UUID.randomUUID().toString();
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

    String getId() { return mPlayerId; }

    void setPlayer(String player) {
        mPlayer = player;
    }

    void setMail(String mail) {
        mMail = mail;
    }

    void setSelected(boolean selected) {
        mSelected = selected;
    }

    void setId (String playerId) {
        mPlayerId = playerId;
    }

}
