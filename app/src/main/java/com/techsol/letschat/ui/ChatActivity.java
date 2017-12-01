package com.techsol.letschat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.techsol.letschat.BaseActivity;
import com.techsol.letschat.R;
import com.techsol.letschat.adapters.ChatAdapter;
import com.techsol.letschat.core.chat.ChatPresenter;
import com.techsol.letschat.core.chat.Notifier;
import com.techsol.letschat.events.PushNotificationEvent;
import com.techsol.letschat.models.Message;
import com.techsol.letschat.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity implements Notifier {
    ChatPresenter chatPresenter;
    ChatAdapter chatAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.edit_text_id)
    EditText editText;

    @BindView(R.id.send)
    TextView send;

    Message message;

    public String userToken;
    public String userUuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        ButterKnife.bind(this);
        bindView();
        init();

    }
    private void init()
    {
        Intent intent = getIntent();
        userToken = intent.getStringExtra(Constants.IntentParamConstant.USER_TOKEN_PARAM);
        userUuid = intent.getStringExtra(Constants.IntentParamConstant.USER_UUID_PARAM);
        chatPresenter = new ChatPresenter(this);
        List<Message> messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messageList,this,userUuid);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);
        chatAdapter.notifyDataSetChanged();
        message = new Message();

        message.setTitle("Push otification test");
        Log.i("chat actiivty","chat activity "+userToken);
        Log.i("chat actiivty","chat userUuid "+userUuid);
    }

    private void bindView()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    public void notify(String messageType) {

    }

    @OnClick(R.id.send)
    public void onClick()
    {
     String messageText = editText.getText().toString();
       // showToast(messageText);
        Message message = new Message();
        message.setMessage(messageText);
        message.setDisplayName(messageText);
        message.setReceiverToken(userToken);
        message.setUuId(userUuid);
        chatAdapter.onMessageReceived(message);
        chatPresenter.send(message);
        editText.setText("");
    }

    @Subscribe
    public void getMessage(PushNotificationEvent event)
    {
        Log.i("eventLog","eventLog*** "+event.getMessage());
        Log.i("eventLog","eventLog*** "+event.getTitle());
        if(chatAdapter != null)
        {
            Message message = new Message();
            message.setMessage(event.getMessage());
            message.setTitle(event.getTitle());
            message.setDisplayName(event.getMessage());
            message.setTitle("12:00");
            message.setUserId(2343434l);
            chatAdapter.onMessageReceived(message);
            recyclerView.scrollToPosition(chatAdapter.getSize() - 1);
        }
    }
}
