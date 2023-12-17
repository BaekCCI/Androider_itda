package com.example.androidproject;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.app.NotificationChannel;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Objects;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String CHANNEL_ID = "CHANNEL_ID";
    private static final CharSequence CHANNEL_NAME = "CHANNEL_NAME";
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

//출처: https://developer.android.com/training/notify-user/channels?hl=ko
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Log.d("createNotificationChannel","3");
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            //Log.d("createNotificationChannel","4");
        }}
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        //token을 서버로 전송
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        Log.d("메시지 수신", "메시지 수신");

        createNotificationChannel();


        //String title = remoteMessage.getNotification().getTitle();
        //String msg = remoteMessage.getNotification().getBody();

        String title2 = remoteMessage.getData().get("title2");
        String body = remoteMessage.getData().get("msg");

        if (Objects.equals(title2, "테스트") || Objects.equals(body, "테스트")) {
            Log.d("테스트 메시지 수신 완료","테스트 메시지 수신 완료");
        } else {
            final String CHANNEL_ID = "ChannerID";
            NotificationManager mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                final String CHANNEL_NAME = "ChannerName";
                final String CHANNEL_DESCRIPTION = "ChannerDescription";
                final int importance = NotificationManager.IMPORTANCE_HIGH;

                // add in API level 26
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
                mChannel.setDescription(CHANNEL_DESCRIPTION);
                mChannel.enableLights(true);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
                mChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
                mManager.createNotificationChannel(mChannel);
            }

            Intent intent = new Intent(this, alarm_center_A.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent contentIntent = PendingIntent.getActivity(this, 0
                    , new Intent(this, alarm_center_A.class)
                    , PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
            builder.setSmallIcon(R.drawable.ic_launcher_background);
            builder.setAutoCancel(true);
            builder.setDefaults(Notification.DEFAULT_ALL);
            builder.setWhen(System.currentTimeMillis());
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle(title2);
            builder.setContentText(body);
            builder.setContentIntent(contentIntent);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                builder.setVibrate(new long[]{500, 500});
            }
            //Log.d("Notification",title + " + " + msg);

            mManager.notify(0, builder.build());

        }
    }
}
