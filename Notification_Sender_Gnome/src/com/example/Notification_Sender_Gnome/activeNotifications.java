package com.example.Notification_Sender_Gnome;

import android.app.Notification;
import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

/**
 * Created by knk on 2/2/14.
 */
public class activeNotifications extends NotificationListenerService {


    @Override
        public void onNotificationPosted(StatusBarNotification sbn) {
            Notification noti = sbn.getNotification();
           if(noti != null)
           {
               Intent intent = new Intent("com.example.Notification_Sender_Gnome");
               intent.putExtra("not",noti.toString());
               sendBroadcast(intent);
           }
        }


        @Override
        public void onNotificationRemoved(StatusBarNotification sbn) {

        }

    }

