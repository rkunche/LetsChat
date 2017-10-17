package com.techsol.letschat;

import android.app.Application;

/**
 * Created by silverlabs on 9/28/17.
 */

public class MyApplication extends Application {

    public static MyApplication mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static MyApplication getmContext()
    {
        return mContext;
    }
}
