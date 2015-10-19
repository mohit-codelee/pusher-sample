package com.eternallect.pushersample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

   public void onEnter(View view){
       String name=((EditText)findViewById(R.id.et_name)).getText().toString();
       Intent intent=new Intent(this,ChatActivity.class);
       intent.putExtra("name",name);
       startActivity(intent);
   }
}
