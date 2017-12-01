package com.techsol.letschat;

import android.app.Application;

import com.techsol.letschat.core.chat.ChatPresenter;
import com.techsol.letschat.core.chat.Notifier;
import com.techsol.letschat.models.Message;
import com.techsol.letschat.module.AppModule;
import com.techsol.letschat.module.DaggerNetComponent;
import com.techsol.letschat.module.NetComponent;
import com.techsol.letschat.module.NetworkModule;

/**
 * Created by silverlabs on 9/28/17.
 */

public class MyApplication extends Application implements Notifier {

    public static MyApplication mContext;
    private NetComponent mNetComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Message message = new Message();
        message.setTitle("this is title");
        message.setMessage("this is messsaage");
        ChatPresenter chatPresenter =new ChatPresenter(this);
        //chatPresenter.send(message);
        initDagger();

    }

    public static MyApplication getmContext()
    {
        return mContext;
    }

    @Override
    public void notify(String messageType) {

    }

    private void initDagger()
    {
        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .networkModule(new NetworkModule())
                .build();
    }

    public NetComponent getNetComponent()
    {
        return mNetComponent;
    }
}
