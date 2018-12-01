package com.example.christian.mealmaker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
   DBHelper helper;
    Cursor table;

    ImageButton mButton;
    Button sButton;
    ArrayList<String> addArray = new ArrayList<String>();
    ArrayList<String> validid= new ArrayList<>();
    ArrayList<String> validname= new ArrayList<>();
    ArrayList<String> validingredient= new ArrayList<>();
    ArrayList<String> validstep= new ArrayList<>();
    ArrayList<String> validimage= new ArrayList<>();
    ArrayList<String> validvideo= new ArrayList<>();
    ArrayList<String> validlink= new ArrayList<>();
    EditText txt;

    ListView show;
   // @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        helper = new DBHelper(this);
        table = helper.populateTable();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mButton = (ImageButton)findViewById(R.id.imagebutton);

        sButton = (Button)findViewById(R.id.searchBtn);
        ((EditText)findViewById(R.id.editText)).setText(" ");
        sButton.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    if(addArray.isEmpty()== false){
                        int count = table.getCount();
                        int counta = addArray.size();
                        int id = 0;
                        int valid = 0;

                        for (table.moveToFirst(); !table.isAfterLast(); table.moveToNext()) {
                            String rid= table.getString(0);
                            String name = table.getString(1);
                            String ingredient = table.getString(2);
                            String step = table.getString(3);
                            String image = table.getString(4);
                            String video = table.getString(5);
                            String link = table.getString(6);

                            for(int b=0;b<counta;b++){

                                if(ingredient.contains(addArray.get(b).toLowerCase())){
                                    valid = valid+1;
                                    if(validid.contains(rid)==false){
                                        if(valid>=3){
                                            validid.add(rid);validname.add(name);validingredient.add(ingredient);
                                            validstep.add(step);validimage.add(image);validvideo.add(video);
                                            validlink.add(link);
                                        }
                                    }
                                }
                            }valid=0;
                        }


                    Intent intent = new Intent(v.getContext(), Main3Activity.class);
                        intent.putExtra("validid", validid);
                        intent.putExtra("validname", validname);
                        intent.putExtra("validingredient", validingredient);
                        intent.putExtra("validstep", validstep);
                        intent.putExtra("validimage", validimage);
                        intent.putExtra("validvideo", validvideo);
                        intent.putExtra("validlink", validlink);
                        if(validid.size()>0){
                            startActivityForResult(intent, 0);
                        }
                        else{
                            Toast.makeText(getBaseContext(), "No Recipe From Given Ingredients. Atleast 3 ingredients is needed",Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(getBaseContext(), "enter ingredients first",Toast.LENGTH_LONG).show();
                    }
                }
            });

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                txt = (EditText)findViewById(R.id.editText);
                show = (ListView) findViewById(R.id.listview);
                String getInput = txt.getText().toString();
                getInput = getInput.toUpperCase();
                EditText editText = (EditText) findViewById(R.id.editText);
                editText.setFilters(new InputFilter[] {
                        new InputFilter() {
                            @Override
                            public CharSequence filter(CharSequence cs, int start,
                                                       int end, Spanned spanned, int dStart, int dEnd) {
                                // TODO Auto-generated method stub
                                if(cs.equals("")){ // for backspace
                                    return cs;
                                }
                                if(cs.toString().matches("[a-zA-Z ]+")){
                                    return cs;
                                }
                                return "";
                            }
                        }
                });
                    if(addArray.contains(getInput.trim())){
                        Toast.makeText(getBaseContext(), "ITEM ALREADY ADDED TO THE LIST",Toast.LENGTH_LONG).show();
                    }
                    else if(getInput == null || getInput.trim().equals("")){
                        Toast.makeText(getBaseContext(), "INPUT FIELD IS EMPTY",Toast.LENGTH_LONG).show();
                    }
                    else{
                        addArray.add(getInput.trim());
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main2Activity.this,android.R.layout.simple_list_item_1, addArray);
                        show.setAdapter(adapter);
                        ((EditText)findViewById(R.id.editText)).setText(" ");
                    }
            }
        });
    }
    public ArrayList<String> getArrayList(){
        return validid;
    }
}
