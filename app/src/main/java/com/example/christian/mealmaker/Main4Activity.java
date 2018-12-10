package com.example.christian.mealmaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("ALL")
public class Main4Activity extends AppCompatActivity {


    ImageView selectedImage;
    TextView name,ingredient,step;
    ListView list;
    public ArrayList<String> validlist;
    // public ArrayList<String> away = new ArrayList<String>();
    String[] in,lis;
    String n,i,s,vi,l;
    int im;
    ImageButton closebutton,menu;
    TextView textV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main4);
        textV = (TextView) findViewById(R.id.textView3);
        menu = (ImageButton) findViewById(R.id.imageView2);
        closebutton = (ImageButton) findViewById(R.id.closebtn);
        name = (TextView) findViewById(R.id.name);
        list = (ListView) findViewById(R.id.list);
        selectedImage = (ImageView) findViewById(R.id.selectedImage); // init a ImageView
        Intent intent = getIntent(); // get Intent which we set from Previous Activity
        selectedImage.setImageResource(intent.getIntExtra("image", 0));
        name.setText(intent.getStringExtra("name"));
        im =intent.getIntExtra("image", 0);
        n =  intent.getStringExtra("name");
        i =  intent.getStringExtra("ingredient");
        s =  intent.getStringExtra("step");
        vi =  intent.getStringExtra("video");
        l =  intent.getStringExtra("link");

   //    validlist =  intent.getStringExtra("validlist");
        validlist = (ArrayList<String>) getIntent().getSerializableExtra("validlist");
       String [] lis  = new String[validlist.size()];
        for(int a=0;validlist.size()>a;a++){
            lis[a]=validlist.get(a);
        }

       // String [] liss =  lis.split("\\s*%\\s*");
        String str = intent.getStringExtra("ingredient");
        String [] aw =  str.split("\\s*%\\s*");
      //number



        ArrayList<String> away = new ArrayList<String>();
        String awayy ="";
        for(int i = 0;i<aw.length;i++){

            outerloop:
            for(int h = 0;h<validlist.size();h++){
                    if (aw[i].contains(lis[h].toLowerCase())) {
                        awayy = "☑️   " + aw[i];
                        break outerloop;
                    } else {
                        awayy = "☐     " + aw[i];
                    }
                }
            away.add(awayy);
        }


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,away );


        list.setAdapter(adapter);
        closebutton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                finish();
            }
        });

        menu.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                Intent intent = new Intent(Main4Activity.this, Main5Activity.class);
                intent.putExtra("image",im); // put image data in Intent
                intent.putExtra("name",n);
                intent.putExtra("ingredient",i);
                intent.putExtra("step",s);
                intent.putExtra("video",vi);
                intent.putExtra("link",l);
                startActivity(intent); // start Intent

            }
        });


    }
}

