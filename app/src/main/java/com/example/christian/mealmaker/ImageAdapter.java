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
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;
import static android.content.Intent.getIntent;

import static java.security.AccessController.getContext;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    String[] validid;
    int size;
  //  ArrayList<String> validid = (ArrayList<String>) getIntent().getSerializableExtra("validid");
  //  ArrayList<String> names = new ArrayList<String>();
 //   private String[] mthumbsid ={};



    public ImageAdapter(Context c,String[] myArrayList) {
        mContext = c;
         mThumbIds= myArrayList;
        size= myArrayList.length;


    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

     //   int size = validid.size();

     //   for(int b=0;b<size;b++){
      //      String  name= "  R.drawable."+ validid.get(b);
      //      mThumbIds[b]=Integer.parseInt(name);

     //   }

        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            //size of image
            imageView.setLayoutParams(new ViewGroup.LayoutParams(670, 670));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }


     //   Drawable myimage = getActivity().getResources();
          //  imageView.toString(mThumbIds);






        return imageView;
    }




    private String[] mThumbIds = {

    };
    private Integer[] mThumbId = {

    };
    // references to our images


}
