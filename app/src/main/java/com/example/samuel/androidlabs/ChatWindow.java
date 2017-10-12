package com.example.samuel.androidlabs;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
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

    private ListView list;
    private EditText editText;
    private Button send;
    public ArrayList<String> chatList = new ArrayList<>();
    protected static final String ACTIVITY_NAME = "ChatActivity";
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        list = (ListView) findViewById(R.id.listView1);
        editText = (EditText) findViewById(R.id.editText3);
        send = (Button) findViewById(R.id.button4e);

        // set the data source of the listView to be a new ChatAdapter object
        // in this case, “this” is the ChatWindow, which is-A Context object ChatAdapter
        final ChatAdapter messageAdapter = new ChatAdapter(this);
        list.setAdapter(messageAdapter);

        // add a callback handler for your Send button so that whenever the user clicks it,
        // you get the text in the EditText field, and add it to your array list variable.
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(ACTIVITY_NAME, editText.getText().toString());
                chatList.add(editText.getText().toString());
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView() in the case
                // someone types something and presses send
                editText.setText("");// clear the textview so EditText is ready for a new message to be sent
            }
        });
    }

    class ChatAdapter extends ArrayAdapter<String> {

        // constructor
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        // this returns the number of rows that will be in your listView.
        // In your case, it should be the number of strings in the array list object
        public int getCount() {
            return chatList.size();
        }

        // this returns the item to show in the list at the specified position
        public String getItem(int position) {
            return chatList.get(position);
        }

        // this returns the layout that will be positioned at the specified position in the list
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

            // this will recreate your View that you made in the resource file. If the position is an odd number
            // then inflate chat_row_incoming, else inflate chat_row_outgoing
            View result = null;
            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }
            //​ from the resulting view, get the TextView which holds the string message:
            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }
    }
}