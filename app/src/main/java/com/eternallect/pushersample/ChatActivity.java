package com.eternallect.pushersample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.eternallect.pushersample.adapter.ChatMessageListAdapter;
import com.eternallect.pushersample.model.ChatMessage;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.ChannelEventListener;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private String name;
    private Channel channel;
    private ListView mListView;
    private List<ChatMessage> mChatMessageList;
    private ChatMessageListAdapter messageListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);
        setTitle("CISCO Event 2015");
        getSupportActionBar().setSubtitle("Web Security - Ashish Mehta");

        this.name=getIntent().getStringExtra("name");

        PusherOptions options=new PusherOptions();
        options.setEncrypted(true);
        Pusher pusher = new Pusher("2b951994cf45122438c2");// //36633a811d8a0a7819f1
        pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {

             //   Toast.makeText(ChatActivity.this, "State changed to " + change.getCurrentState() +
               //         " from " + change.getPreviousState(), Toast.LENGTH_LONG).show();
                Log.e("SamplePusher","State changed to " + change.getCurrentState() +
                        " from " + change.getPreviousState());
            }

            @Override
            public void onError(String message, String code, Exception e) {
                //Toast.makeText(ChatActivity.this,"There was a problem connecting! "+e.toString(), Toast.LENGTH_LONG).show();
                Log.e("Pushre Sample","There was a problem connecting! "+e.toString()+" code "+code);
            }
        }, ConnectionState.ALL);

        channel = pusher.subscribe("test_channel");
       /* pusher.subscribe("test_channel", new ChannelEventListener() {
            @Override
            public void onSubscriptionSucceeded(String channelName) {
                Log.e("Subscription","Subscription Succeeded on channel "+channelName);
            }

            @Override
            public void onEvent(String channelName, String eventName, String data) {
                Log.e("subscription","channelName "+channelName+" eventName "+eventName+ " data "+data);

            }
        },"ashish","","test_event","event_name","pusher:subscription_error");
*/
        channel.bind("ashish", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, String data) {
                //String message=((EditText)findViewById(R.id.text)).getText().toString();
                Log.e("PusherEvent","Channel name "+channelName+" eventName "+eventName+" data "+data  );
                ChatMessage chatMessage=new ChatMessage("Ashish",data,true,42);
                //messageListAdapter.add(chatMessage);
                ((ChatMessageListAdapter)mListView.getAdapter()).add(chatMessage);
                ((ChatMessageListAdapter) mListView.getAdapter()).notifyDataSetChanged();

            }
        });





        mListView = (ListView)findViewById(R.id.lv_chat);
        mChatMessageList = new ArrayList<>();
        fillList();
    }

    private void fillList(){
        ChatMessage message;

        message = new ChatMessage("Mohit","Hi Guys! How are you?",false,18028130);
        mChatMessageList.add(message);
        message = new ChatMessage("Me","I'm good what about are you?",true,18028130);
        mChatMessageList.add(message);
        message = new ChatMessage("Charan","Hi Guys! How are you?",false,18028130);
        mChatMessageList.add(message);
        message = new ChatMessage("Malkit","Hi Guys! How are you?",false,18028130);
        mChatMessageList.add(message);
        message = new ChatMessage("Me","Hi Guys! How are you?",true,18028130);
        mChatMessageList.add(message);
        message = new ChatMessage("Ashish","Hi Guys! How are you?",false,18028130);
        mChatMessageList.add(message);
        message = new ChatMessage("Charan","Hi Guys! How are you?",false,18028130);
        mChatMessageList.add(message);
        message = new ChatMessage("Me","Hi Guys! How are you?",true,18028130);
        mChatMessageList.add(message);
        message = new ChatMessage("Mohit","Hi Guys! How are you?",false,18028130);
        mChatMessageList.add(message);
        message = new ChatMessage("Mohit","Hi Guys! How are you?",false,18028130);
        mChatMessageList.add(message);

        messageListAdapter = new ChatMessageListAdapter(this,mChatMessageList);
        mListView.setAdapter(messageListAdapter);
    }

    public void onSend(View view){
        EditText editText= (EditText)findViewById(R.id.text);
         String message=(editText).getText().toString();
         ChatMessage chatMessage=new ChatMessage("Me",message,true,42);
        ((ChatMessageListAdapter)mListView.getAdapter()).add(chatMessage);
        ((ChatMessageListAdapter) mListView.getAdapter()).notifyDataSetChanged();
        editText.setText("");
    }
}
