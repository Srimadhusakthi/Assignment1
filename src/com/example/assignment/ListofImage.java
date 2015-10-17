package com.example.assignment;

import java.io.File;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class ListofImage extends Activity {

 GridView gridView;
 ImageAdapter imageAdapter;
 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_image);
        
        gridView = (GridView) findViewById(R.id.listview);
        imageAdapter = new ImageAdapter(this, R.layout.image, getData());
        gridView.setAdapter(imageAdapter);
        
       
    }
 private ArrayList<ImageItem> getData() {
     final ArrayList<ImageItem> imageItems = new ArrayList<ImageItem>();
     TypedArray imgs = getResources().obtainTypedArray(R.array.ids);
     for (int i = 0; i < imgs.length(); i++) {
         Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
         imageItems.add(new ImageItem(bitmap, "Image#" + i));
     }
     return imageItems;
 }

}