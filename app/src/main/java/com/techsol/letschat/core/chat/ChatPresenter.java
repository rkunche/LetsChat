package com.techsol.letschat.core.chat;


import android.util.Log;

import com.techsol.letschat.models.Message;
import com.techsol.letschat.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatPresenter implements ChatContract{
    Notifier notifier;
    ChatIntractor chatIntractor;
    public ChatPresenter(Notifier notifier)
    {
     this.notifier = notifier;
        chatIntractor = new ChatIntractor();
    }
    @Override
    public void sendMessage(Message message) {
       // chatIntractor.sendMessage();
        send(message);
    }


    public void send(Message message) {
        RequestBody requestBody = null;
        try {
            requestBody = RequestBody.create(Constants.MEDIA_TYPE_JSON, getValidJsonBody(message).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Request request = new Request.Builder()
                .addHeader(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON)
                .addHeader(Constants.AUTHORIZATION, Constants.AUTH_KEY)
                .url(Constants.FCM_URL)
                .post(requestBody)
                .build();

        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(Constants.TAG, "onGetAllUsersFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(Constants.TAG, "onResponse: " + response.body().string());
            }
        });
    }

    private JSONObject getValidJsonBody(Message message) throws JSONException {
       // String token = "ezGiB2t5WeQ:APA91bH4vQbCP2eZQIQ_Latz-IVgjxj-8XSg9c9QzmBQpqKz-4Ha0CZ4y-GWrGHcTyl9eehqNWJlTVZn38BEG8xlZnyA0HMLoQD-ICtOlBuHYny5p8Bxd0TWsGNabeNimblcyji3AtSC";
        Log.i("currentUserToken","currentUserToken other user Token "+message.getReceiverToken());
        Log.i("currentUserToken","currentUserToken uuid "+message.getUuId());
        Log.i("currentUserToken","currentUserToken curent user token "+Constants.getCurrentUserToken());
        JSONObject jsonObjectBody = new JSONObject();
        //jsonObjectBody.put(Constants.KEY_TO, message.getReceiverToken());
        jsonObjectBody.put(Constants.KEY_TO, message.getReceiverToken());
        JSONObject jsonObjectData = new JSONObject();
        JSONObject jsonNotificationObject = new JSONObject();
        jsonNotificationObject.put("title",message.getTitle());
        jsonNotificationObject.put("body",message.getMessage());
        jsonObjectData.put(Constants.KEY_TITLE, message.getTitle());
        jsonObjectData.put(Constants.KEY_TEXT, message.getMessage());
        jsonObjectData.put(Constants.KEY_USERNAME, "raju");
        jsonObjectData.put(Constants.KEY_UID, message.getUuId());
        jsonObjectData.put(Constants.KEY_FCM_TOKEN,Constants.getCurrentUserToken());
        jsonObjectBody.put(Constants.KEY_DATA, jsonObjectData);
        jsonObjectBody.put("notification",jsonNotificationObject);

        return jsonObjectBody;
    }
}
