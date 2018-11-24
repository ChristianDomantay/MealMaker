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

    GridView gridView;
    public Integer[] mThumbId = {
            R.drawable.chickencurry, R.drawable.chickenadobo,
            R.drawable.chickenmacaronisalad,R.drawable.pinoybeefsteak,
            R.drawable.chickentinola, R.drawable.chickenhamonado,
            R.drawable.chickenalaking, R.drawable.buffalochickenwing,
            R.drawable.chickencordonbleu , R.drawable.chickeninasalbbq,
            R.drawable.crispypata, R.drawable.bicolexpress,
            R.drawable.longganisa, R.drawable.sisig,
            R.drawable.calderetangbaboy, R.drawable.lecheflan,
            R.drawable.ginataangmais, R.drawable.putobumbong,
            R.drawable.ubehalaya, R.drawable.yema,
            R.drawable.bibingkanggalapong, R.drawable.bananalumpia,
            R.drawable.karekare, R.drawable.nilagangbaka,
            R.drawable.bulalo, R.drawable.ginataanggulay,
            R.drawable.adobongsitaw, R.drawable.chopsuey,
            R.drawable.salmonsinigang, R.drawable.sinigangnabangus ,
            R.drawable.seafoodsinigang, R.drawable.sinigangnababoy ,
            R.drawable.sinigangnasalmonsamiso
    };
    public Integer[] fin;
    public ArrayList<String> validname,validingredient,validstep,validimage,validid;
    String [] name,ingredient,step,image,vid;
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
        validid = (ArrayList<String>) getIntent().getSerializableExtra("validid");
        validname = (ArrayList<String>) getIntent().getSerializableExtra("validname");
        validingredient = (ArrayList<String>) getIntent().getSerializableExtra("validingredient");
        validstep = (ArrayList<String>) getIntent().getSerializableExtra("validstep");
        validimage = (ArrayList<String>) getIntent().getSerializableExtra("validimage");
       int size = validid.size();

        final Integer [] mThumbIds = new Integer[validid.size()];
         name = new String[validname.size()];
        ingredient = new String[validingredient.size()];
        step  = new String[validstep.size()];
        image  = new String[validimage.size()];
        vid = new String[validid.size()];
        fin = new Integer[size];
        for(int a=0;validname.size()>a;a++){
            vid[a]=validid.get(a);
            name[a]=validname.get(a);
        ingredient[a]=validingredient.get(a);
           step[a]=validstep.get(a);
            image[a]=validid.get(a);
        }
        for(int b=0;b<size;b++){
            int num = Integer.parseInt(vid[b]);
            num = num-1;
            fin[b] = mThumbId[num];
        }

        closebutton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), Main2Activity.class);
                startActivityForResult(intent, 0);
            }
        });
        for(int b=0;b<size;b++){
           String  vname= validid.get(b);
           mThumbIds[b]=Integer.parseInt(vname);
        }

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this,mThumbIds));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getBaseContext(), "" + position,
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
                intent.putExtra("image", fin[position]); // put image data in Intent

                intent.putExtra("name", name[position]);
                intent.putExtra("ingredient",ingredient[position]);
                intent.putExtra("step",step[position]);

                startActivity(intent); // start Intent
            }
        });





    }



}
