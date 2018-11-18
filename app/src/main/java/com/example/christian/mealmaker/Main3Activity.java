package com.example.christian.mealmaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    ImageButton closebutton;
    TextView myText;
    ListView show;
    GridView gridView;

    //static final Integer[] mThumbIds = new Integer[]{};
    @SuppressWarnings("deprecation")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Bundle extras = getIntent().getExtras();
        if (extras == null) return;

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        closebutton = (ImageButton) findViewById(R.id.closebtn);
        ArrayList<String> validid = (ArrayList<String>) getIntent().getSerializableExtra("validid");

        show = (ListView) findViewById(R.id.listview1);
       int size = validid.size();
        if(size==0){
            Toast.makeText(getBaseContext(), "arrayempty",
                    Toast.LENGTH_LONG).show();
        }
            else{  Toast.makeText(getBaseContext(), "arrayhasvalue",
                Toast.LENGTH_LONG).show();
        }
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main3Activity.this,android.R.layout.simple_list_item_1, validid);
        show.setAdapter(adapter);
        String [] mThumbIds = new String[validid.size()];





        closebutton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                Intent intent = new Intent(v.getContext(), Main2Activity.class);
                startActivityForResult(intent, 0);
            }
        });
// references to our images



        for(int b=0;b<size;b++){
            String  name= "C:\\Users\\christian\\AndroidStudioProjects\\MealMaker\\app\\src\\main\\res\\drawable\\"+validid.get(b)+".jpg";

            //String  name= "R.drawable."+validid.get(b);
            //mThumbIds[b]=Integer.parseInt(name);
            mThumbIds[b]= name;

        }



        GridView gridview = (GridView) findViewById(R.id.gridview);






        gridview.setAdapter(new ImageAdapter(this,mThumbIds));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getBaseContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });





    }



}
