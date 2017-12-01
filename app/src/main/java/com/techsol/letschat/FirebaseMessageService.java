package com.techsol.letschat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.techsol.letschat.events.GlobalBus;
import com.techsol.letschat.events.PushNotificationEvent;

import static android.content.ContentValues.TAG;

public class FirebaseMessageService extends FirebaseMessagingService {
    public FirebaseMessageService() {
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        addNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());
    }
    private void addNotification(String message, String title) {
       final PushNotificationEvent pushNotificationEvent =new PushNotificationEvent();
        pushNotificationEvent.setMessage(message);
        pushNotificationEvent.setTitle(title);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                GlobalBus.getBus().post(pushNotificationEvent);
            }
        });
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.darkbg)
                        .setContentTitle(title)
                        .setContentText(message);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
