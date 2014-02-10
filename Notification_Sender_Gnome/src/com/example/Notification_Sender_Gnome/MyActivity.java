package com.example.Notification_Sender_Gnome;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.PrintWriter;
import java.net.Socket;

public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */


    Button btn_start;
    Socket sock;
    PrintWriter pw;

    //StatusBarNotification stb[];
    String str = "";
    int i =0;
    TextView tv;
    receiveNotification rn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn_start = (Button) findViewById(R.id.button_start);
        tv = (TextView) findViewById((R.id.textview));
        activeNotifications actnot = new activeNotifications();
        new sendNotification().execute();
        //activeNotification act_obj = new activeNotification();
        rn = new receiveNotification();
        btn_start.setOnClickListener(this);

        //.setText("hello world");
        Intent intent = new Intent(MyActivity.this,activeNotifications.class);
       startService(intent);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.Notification_Sender_Gnome");
        registerReceiver(rn,filter);



    }

    @Override
    public void onClick(View v) {

        try{
            i++;
            Context context = getApplicationContext();
            Notification myNotification = new Notification.Builder(context)
                    .setContentTitle("TEST")
                    .setContentText("This is a test notification")
                    .setTicker("test test")
                    .setWhen(System.currentTimeMillis())
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .build();
            NotificationManager notificationManager = (NotificationManager)getSystemService(context.NOTIFICATION_SERVICE);
            notificationManager.notify(i,myNotification);

         pw = new PrintWriter(sock.getOutputStream(),true);
            str = "hello";
         pw.println(str);
        }catch(Exception e) { System.out.println(e);}


    }



    /*class activeNotification extends NotificationListenerService {
        activeNotification()
        {




            stb = getActiveNotifications();



            str = stb.toString();
 bindService(intent,mServerConn, Context.BIND_AUTO_CREATE);
        }
        @Override
        public void onNotificationPosted(StatusBarNotification sbn) {

        }

        @Override
        public void onNotificationRemoved(StatusBarNotification sbn) {

        }

    }*/
    class receiveNotification extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            String str = intent.getStringExtra("not");
            tv.setText("hello wordl"+str);
        }
    }


   class sendNotification extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try{
                sock = new Socket("192.168.1.2",1234);
            }
            catch(Exception e)
            {
                //Log.v("socket", e.toString());
            }
            return null;
        }
    }
}
