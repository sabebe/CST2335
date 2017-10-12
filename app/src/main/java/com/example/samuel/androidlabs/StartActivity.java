package com.example.samuel.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "StartActivity";
    private Button startButton;
    private Button chatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        setContentView(R.layout.activity_start);

        startButton =(Button) findViewById(R.id.button);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, 10);
            }
        });

        chatButton = (Button) findViewById(R.id.button3);
        chatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(ACTIVITY_NAME, "User clicked Start Chat");
                Intent intent = new Intent(StartActivity.this, ChatWindow.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i (ACTIVITY_NAME, "In onResume()");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        if (requestCode == 10) {
            String messagePassed = data.getStringExtra("Response");
            if (messagePassed.equals("My information to share")) {
                Log.i(ACTIVITY_NAME, "Returned to StartActivity.onActivityResult");
                CharSequence text = "ListItemsActivity passed: " + messagePassed;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(StartActivity.this, text, duration);
                toast.show(); //display your message box
            }
        }
    }
}
