package com.example.christian.mealmaker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    ImageButton mButton;
    ArrayList<String> addArray = new ArrayList<String>();
    EditText mEdit;
    EditText txt;
    TextView mText;
    ListView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mButton = (ImageButton)findViewById(R.id.imagebutton);
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
                        addArray.add(getInput);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main2Activity.this,android.R.layout.simple_list_item_1, addArray);
                        show.setAdapter(adapter);
                        ((EditText)findViewById(R.id.editText)).setText(" ");
                    }
            }
        });
    }


}
