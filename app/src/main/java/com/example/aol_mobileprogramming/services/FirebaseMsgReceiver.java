package com.example.aol_mobileprogramming.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.aol_mobileprogramming.R;
import com.example.aol_mobileprogramming.ui.bottomnav.BottomNavbarActivity;
import com.example.aol_mobileprogramming.ui.login.LoginActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMsgReceiver extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        if (message.getNotification() != null){
            String title = message.getNotification().getTitle();
            String body = message.getNotification().getBody();
            int userId = getSharedPreferences("UserPrefs", MODE_PRIVATE).getInt("user_id", -1);
            if (userId != -1) {
                sendNotification(title, body, new Intent(this, BottomNavbarActivity.class));
            } else {
                sendNotification(title, body, new Intent(this, LoginActivity.class));
            }
        }
    }

    private void sendNotification(String title, String body, Intent intent){
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "NotifyChannel";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Push Notification Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            manager.createNotificationChannel(channel);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(this, channelId)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        manager.notify(0, notifBuilder.build());
    }

}
