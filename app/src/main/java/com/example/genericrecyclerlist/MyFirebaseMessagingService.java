package com.example.genericrecyclerlist;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.marketo.Marketo;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static final String TAG = "MsgFirebaseServ";
    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Marketo marketoSdk = Marketo.getInstance(this.getApplicationContext());
        marketoSdk.initializeMarketoPush("genericrecyclerlist", "MKTO");
        marketoSdk.setPushNotificationToken(token);
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Marketo marketoSdk = Marketo.getInstance(this.getApplicationContext());
        marketoSdk.showPushNotification(remoteMessage);
    }
}
