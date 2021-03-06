package com.example.christian.mealmaker;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.IconCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;
import static android.content.Intent.getIntent;

import static java.security.AccessController.getContext;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    Integer[] validid;
    String [] name;
    int size;
    public Integer[] fin;
    public String[] vname;


    public ImageAdapter(Context c,Integer[] myArrayList, ArrayList<String> gname) {
        mContext = c;

         validid = myArrayList;
        size= myArrayList.length;
        fin = new Integer[size];
        for(int b=0;b<size;b++){
            int num = myArrayList[b];
            num = num-1;
            fin[b] = mThumbIds[num];
        }

       int a=gname.size();

        vname = new String[a];
       for(int b=0;b<a;b++){
            vname[b] = gname.get(b);
       }


    }


    public int getCount() {
        return fin.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.mylist, null);
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(vname[position]);
            imageView.setImageResource(fin[position]);
            //size of image
            grid.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 450));
           // imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 450));
          //  imageView.requestLayout();
         //   imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);


        } else {
            grid = (View) convertView;
        //    imageView = (ImageView) convertView;

        }


     //   Drawable myimage = getActivity().getResources();
          //  imageView.setImageResource(fin[position]);

     //   return imageView;
        return grid;
    }




    private Integer[] mThumbIds = {
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
    // references to our images


}
