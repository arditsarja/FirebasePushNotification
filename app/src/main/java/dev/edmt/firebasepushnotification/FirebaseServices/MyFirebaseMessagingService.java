package dev.edmt.firebasepushnotification.FirebaseServices;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import dev.edmt.firebasepushnotification.Config.Config;

/**
 * Created by reale on 04/11/2016.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        handleNotification(remoteMessage.getNotification().getBody());
//    }

    private void handleNotification(String body) {
        Intent pushNotification = new Intent(Config.STR_PUSH);
        pushNotification.putExtra("message", body);
        LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            for (String key : remoteMessage.getData().keySet()) {
                Log.v(key, remoteMessage.getData().get(key));
            }


            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
//                scheduleJob();
            } else {
                // Handle message within 10 seconds
//                handleNow();
            }
        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//            Toast.makeText(this,remoteMessage.getNotification().getBody(),Toast.LENGTH_LONG).show();
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        handleNotification(remoteMessage.getNotification().getBody());
    }
}
