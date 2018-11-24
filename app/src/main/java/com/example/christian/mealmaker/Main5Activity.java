package com.example.christian.mealmaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {
    ImageView selectedImage1;
    TextView name,ingredient;
    ListView list,step;
    ArrayList<String> ing = new ArrayList<String>();
    String[] in,away,away1;
    ImageButton closebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        step = (ListView) findViewById(R.id.step);
        // ingredient = (TextView) findViewById(R.id.ingredient);
        name = (TextView) findViewById(R.id.name);
        list = (ListView) findViewById(R.id.list);
        selectedImage1 = (ImageView) findViewById(R.id.selectedImage1); // init a ImageView
        Intent intent = getIntent(); // get Intent which we set from Previous Activity
        selectedImage1.setImageResource(intent.getIntExtra("image", 0));
        name.setText(intent.getStringExtra("name"));
        // ingredient.setText(intent.getStringExtra("ingredient"));



        String str = intent.getStringExtra("ingredient");
        String [] away =  str.split("\\s*%\\s*");
        String str1 = intent.getStringExtra("step");
        String [] away1 =  str1.split("\\s*%\\s*");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,away );
        ArrayAdapter adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,away1 );
        step.setAdapter(adapter1);
        list.setAdapter(adapter);
    }
}
