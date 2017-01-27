package com.example.android.simplediscgolf;

/**
 * Created by Tero on 23.1.2017.
 */

//TODO COMMENT

public class SetGameItem {

    private String mPrimaryString;

    private String mSecondaryString;

    private int mIcon;

    private String mActivity;

    public SetGameItem() {
        mPrimaryString = "";
        mSecondaryString = "";
        mIcon = 0;
    }

    public SetGameItem(String primaryString, String secondaryString) {
        mPrimaryString = primaryString;
        mSecondaryString = secondaryString;
        mActivity = null;
    }

    public SetGameItem(String primaryString, String secondaryString, int icon) {
        mPrimaryString = primaryString;
        mSecondaryString = secondaryString;
        mIcon = icon;
        mActivity = null;
    }

    public SetGameItem(String primaryString, String secondaryString, int icon, String activity) {
        mPrimaryString = primaryString;
        mSecondaryString = secondaryString;
        mIcon = icon;
        mActivity = activity;
    }

    String getPrimaryString() {
        return mPrimaryString;
    }

    String getSecondaryString() {
        return mSecondaryString;
    }

    int getIcon() {
        return mIcon;
    }

    String getActivity() { return mActivity; }

    void setPrimaryString(String primaryString) {
        mPrimaryString = primaryString;
    }

    void setSecondaryString(String secondaryString) {
        mSecondaryString = secondaryString;
    }

    void setIcon(int icon) {
        mIcon = icon;
    }

    void setActivity(String activity) {
        mActivity = activity;
    }
}
