package com.example.assignment;
import java.io.ByteArrayOutputStream;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.R.integer;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
public Button mViewinListview,mLoading,mSave;
public ImageView mImageView;
public static final int SELECTING_IMAGE=1 ;
String selectedimage;
private MyDataBase mdb=null;
private SQLiteDatabase db=null;
private Cursor c=null;
private byte[] img=null;
private static final String DATABASE_NAME = "ImageDb.db";
public static final int DATABASE_VERSION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        clickEvent();

    	
    }

public void initialize(){
	mImageView=(ImageView) findViewById(R.id.loaded_image);
	mViewinListview=(Button) findViewById(R.id.loading_imagefrom_sdcard);
	mLoading=(Button) findViewById(R.id.view);
	mSave=(Button) findViewById(R.id.save);  
	mImageView.setImageResource(0);
    mdb=new MyDataBase(getApplicationContext(), DATABASE_NAME,null, DATABASE_VERSION);
    Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    ByteArrayOutputStream bos=new ByteArrayOutputStream();
    b.compress(Bitmap.CompressFormat.PNG, 100, bos);
    img=bos.toByteArray();
    db=mdb.getWritableDatabase();
}

public void clickEvent(){
	mSave.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			 ContentValues cv=new ContentValues();
	            cv.put("image", img);
	            db.insert("tableimage", null, cv);
	            Toast.makeText(MainActivity.this, "saved successfully",Toast.LENGTH_SHORT).show();
	     
		}
	});
	mLoading.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
//			intent.putExtra(getpath(url), value/)
			startActivityForResult( Intent.createChooser(intent, "SELECTED IMAGE"),SELECTING_IMAGE);
		}
	});
	mViewinListview.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity.this,ListofImage.class);
			startActivity(intent);
		}
	});
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
if(resultCode==RESULT_OK){
	if(requestCode==SELECTING_IMAGE){
		Uri uri=data.getData();
		selectedimage=getpath(uri);
		mImageView.setVisibility(View.VISIBLE);
		
		mImageView.setImageURI(uri);
	}
}

	}
	public String getpath(Uri url){
    	String projection[]={MediaStore.Images.Media.DATA};
    	Cursor cursor=managedQuery(url, projection, null,null,null);
    	int position=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    	cursor.moveToFirst();
    	return cursor.getString(position);
    	
    }    

public class MyDataBase extends SQLiteOpenHelper{
     
    public MyDataBase(Context context, String dbname, CursorFactory factory, int dbversion) {
        super(context, dbname, factory, dbversion);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tableimage(image blob);");
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }
 
}	
}
