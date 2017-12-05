package com.example.samuel.androidlabs;

import android.support.v7.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    final ArrayList<String> chatArray = new ArrayList<>();

    private ChatDatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private String[] allColumns = { ChatDatabaseHelper.KEY_ID,
            ChatDatabaseHelper.KEY_MESSAGE };
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        dbHelper = new ChatDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
        readMessages();

        final ListView listViewChat = (ListView) findViewById(R.id.listView);
        chatAdapter = new ChatAdapter(this);
        listViewChat.setAdapter(chatAdapter);
        final EditText editTextChat = (EditText) findViewById(R.id.editText);
        Button buttonSend = (Button) findViewById(R.id.sendButton);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chatString = editTextChat.getText().toString();
                writeMessages(chatString);

                editTextChat.setText("");
            }
        });
    }

    private void readMessages() {
        Cursor cursor = database.query(ChatDatabaseHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String message = cursor.getString(cursor.getColumnIndex( ChatDatabaseHelper.KEY_MESSAGE));
            long id = cursor.getLong(cursor.getColumnIndex(ChatDatabaseHelper.KEY_ID));

            Log.i("Chat Window", "ID: " + cursor.getString( cursor.getColumnIndex(ChatDatabaseHelper.KEY_ID)) + " SQL MESSAGE:" + cursor.getString( cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));

            String data = new String(message);

            chatArray.add(data);
            cursor.moveToNext();
        }

        Log.i("Chat Window", "Cursor’s column count =" + cursor.getColumnCount());

        for(int i = 0; i < cursor.getColumnCount(); i++) {
            Log.i("Chat Window", "Column Name: " + cursor.getColumnName(i));
        }

        // close the cursor
        cursor.close();
    }

    private void writeMessages(String message) {
        ContentValues values = new ContentValues();

        values.put(ChatDatabaseHelper.KEY_MESSAGE, message);
        long id = database.insert(ChatDatabaseHelper.TABLE_NAME, null,
                values);

        String data = new String(message);
        chatAdapter.notifyDataSetChanged();
        chatArray.add(data);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 5) {
            Bundle extras = data.getExtras();
            int id = Integer.parseInt(extras.getString("id"));
            long dataid = Long.parseLong(extras.getString("dataID"));

            database.delete(ChatDatabaseHelper.TABLE_NAME, "_id=?",
                    new String[]{Long.toString(dataid)});

            chatArray.remove(id);
            chatAdapter.notifyDataSetChanged();
        }
    }

    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context context) {
            super(context, 0);
        }

        public int getCount() {
            return chatArray.size();
        }

        public String getItem(int position) {
            return chatArray.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if (position%2 == 0) {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            }

            TextView message = (TextView) result.findViewById((R.id.message_text));
            message.setText(getItem(position));
            return result;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}