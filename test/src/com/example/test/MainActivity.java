package com.example.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


import java.net.*;
import java.io.*;

public class MainActivity extends Activity implements OnClickListener {
	Button b;
	EditText text;
	Socket sock;
	DataOutputStream dout;
	String str;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        b = (Button) findViewById(R.id.button1);
        text = (EditText) findViewById(R.id.editText1);
       
        new sendNotification().execute();
        b.setOnClickListener(this);
        
        
    }
  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	@Override
	public void onClick(View arg0) {
	try{
		dout = new DataOutputStream(sock.getOutputStream());
		str = text.getText().toString();
		dout.writeUTF(str);
		text.setText("Successfully send");	
	}
	catch(Exception e)
	{
		
	}
	}
	class sendNotification extends AsyncTask<Void,Void,Void>{
		@Override
		protected Void doInBackground(Void... params) {
			try{
				sock =  new Socket("10.0.2.2",5558);
			}
			catch(Exception e)
			{
				Log.v("socket",e.toString());
			}
			
			return null;
		}
	}
}
