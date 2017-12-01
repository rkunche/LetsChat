package com.techsol.letschat.utils;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.techsol.letschat.MyApplication;
import com.techsol.letschat.models.Message;
import com.techsol.letschat.models.User;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;

public class Constants {

    public static String USER = "users";


    // log info
    public static String USER_SAVE = "user save";
    public static String USER_FETCH = "user fetch";

    //toast inof
    public static String USERS_FETCH_FAIL = "No users available";

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String TAG = "FcmNotificationBuilder";
    public static final String SERVER_API_KEY = "AAAAsn-OPmU:APA91bHQ65j-cVE6D-_SvFYSaozjKR5pYjUvWIOuNGAgliq-yyPVjdxNpOSP-D4B1-tai-gmIYSBQ7kwA__1-IWzBIPByAAa2170sH_nfRpl4wMJGVG3fIG2JlerNALGK_sVTZriyp61";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTH_KEY = "key=" + SERVER_API_KEY;
    public static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";
    // json related keys
    public static final String KEY_TO = "to";
    public static final String KEY_NOTIFICATION = "notification";
    public static final String KEY_TITLE = "title";
    public static final String KEY_TEXT = "text";
    public static final String KEY_DATA = "data";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_UID = "uid";
    public static final String KEY_FCM_TOKEN = "fcm_token";
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int PICK_IMAGE_REQUEST = 2;
    public static final String FETCH_USERS = "fetch_users";
    public static final String FETCH_USERS_COMPLETED = "fetch_users_completed";
    public static final String CAM_OPTION = "cam_option";
    public static final String GAL_OPTION = "gal_option";
    public static final String SHOW_DIALOGUE = "show_dialogue";
    public static final String Q_DONE = "q_done";
    public static final String Q_CANCEL = "q_cancel";

    public static List<User> getStaticUsers()
    {
        User user1 = new User();
        user1.setName("Raju");
        user1.setQuote("Time is Precious");
        User user2 = new User();
        user2.setName("Shashi");
        user2.setQuote("Be happy");
        User user3 = new User();
        user3.setName("Vazeer");
        user3.setQuote("Be patient");
        User user4 = new User();
        user4.setName("Yahswant");
        user4.setQuote("Keep going");

        User user5 = new User();
        user5.setName("Shashidhar");
        user5.setQuote("Chill dude");

        User user6 = new User();
        user6.setName("Sunil");
        user6.setQuote("Nice one");

        User user7 = new User();
        user7.setName("Ravi");
        user7.setQuote("Just do it");

        User user8 = new User();
        user8.setName("Praveen");
        user8.setQuote("Time to start");

       List<User> userList =new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);
        userList.add(user7);
        userList.add(user8);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user2);
        userList.add(user8);
        userList.add(user2);
        userList.add(user3);
       return userList;
    }
public static List<Message> getMessages()
{
    List<Message> messageList = new ArrayList<>();
    Message message = new Message();
    message.setUuId("some test message to check the positioning");
    message.setDisplayName("some test message to check the positioning some test message to check the positioning some test message to check the positioning");
    message.setTime("time");
    message.setUserId(12323l);


    Message message1 = new Message();
    message1.setUuId("some test message to check the positioning");
    message1.setDisplayName("***************************************************** to check the positioning");
    message1.setTime("time");
    message1.setUserId(2343434l);

    messageList.add(message);
    messageList.add(message1);
    messageList.add(message);
    messageList.add(message1);
    messageList.add(message);
    messageList.add(message1);
    messageList.add(message);
    messageList.add(message1);
    messageList.add(message);
    messageList.add(message1);
    return messageList;
}
    public static class ScreenNames
    {
        public static String USER_LIST = "CHATS";
        public static String FEED = "FEED";
        public static String PROFILE = "PROFILE";
        public static String TEXT = "TEXT";
        public static String IMAGE = "IMAGE";
    }

    public static class IntentParamConstant
    {
        public static String USER_ID_PARAM = "ID";
        public static String USER_UUID_PARAM = "UUID";
        public static String USER_TOKEN_PARAM = "TOKEN";
    }

    public static void saveUserValues(User user)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getmContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(IntentParamConstant.USER_TOKEN_PARAM,user.getToken());
        editor.putString(IntentParamConstant.USER_UUID_PARAM,user.getUuId());
        editor.apply();
        editor.commit();
    }

    public static String getCurrentUserToken()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getmContext());
        String name = preferences.getString(IntentParamConstant.USER_TOKEN_PARAM,"");
        return name;
    }

    public static void saveToken(String token)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getmContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(IntentParamConstant.USER_TOKEN_PARAM,token);
        editor.apply();
        editor.commit();
    }
}
