package com.example.christian.mealmaker;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getActionBar().hide();
       // Intent i = new Intent(this, MyService.class);
       // startService(i)
    }
    public void process(View v) {
        Intent i = null, chooser = null;
      if(v.getId() == R.id.start) {
            i = new Intent(this, Main2Activity.class);
            startActivity(i);
        }
    }

}
