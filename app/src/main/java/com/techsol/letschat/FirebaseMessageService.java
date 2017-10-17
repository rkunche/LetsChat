package com.techsol.letschat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

public class FirebaseMessageService extends FirebaseMessagingService {
    public FirebaseMessageService() {
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

//        // TODO(developer): Handle FCM messages here.
//        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
//        Log.d(TAG, "From: " + remoteMessage.getFrom());
//        Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//
//
//        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
//        }
//        String message = remoteMessage.getNotification().getBody();
//
//        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//        }
//
//        addNotification(message);
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    private void addNotification(String message) {
        Log.d(TAG, "Message message,,,, " + message);
        Notification newMessageNotification =
                new Notification.Builder(MyApplication.getmContext())

                        .setContentTitle("Title")
                        .setContentText("message "+message)

                .build();

// Issue the notification.
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1234, newMessageNotification);

    }
}
