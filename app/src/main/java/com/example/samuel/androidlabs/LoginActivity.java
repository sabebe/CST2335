package com.example.samuel.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class LoginActivity extends Activity {
    protected static final String ACTIVITY_NAME = "LoginActivity";
    private Button loginButton;
    private static final String PREFERENCE_FILE = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.button2);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE).edit();
                EditText loginName = (EditText) findViewById(R.id.EditView);
                editor.putString("DefaultEmail", loginName.getText().toString());
                editor.commit();
                // The activity will jump to the next activity which is StartActivity;
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);

            }
        });
        Log.i(ACTIVITY_NAME, "In onCreate()");
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

        String defaultEmail;
        SharedPreferences prefs = getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        defaultEmail = prefs.getString("DefaultEmail", "email@domain.com");

        EditText loginName = (EditText)findViewById(R.id.EditView);
        loginName.setText(defaultEmail);
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
}