package com.example.assignment;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
public Button mViewinListview,mLoading,mSave;
public ImageView mImageView;
public static final int SELECTING_IMAGE=1 ;
String selectedimage;
public SQLiteDatabase mSqLiteDatabase;
public Sqlite mSqlite;
public DATAbase DATAbase;
public ImageAdapter imageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        clickEvent();

        DATAbase = new DATAbase(this);
    		Image employee_One = new Image(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
    	
//    		DATAbase.insertEmpDetails(employee_One);
//    		DATAbase.close();
//    		employee_One = null;
    	
//    		DATAbase.close();
    		

    	
    }

public void initialize(){
	mImageView=(ImageView) findViewById(R.id.loaded_image);
	mViewinListview=(Button) findViewById(R.id.loading_imagefrom_sdcard);
	mLoading=(Button) findViewById(R.id.view);
	mSave=(Button) findViewById(R.id.save);
}
public void clickEvent(){
	mSave.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
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
    
    class Sqlite extends SQLiteOpenHelper{

		public static final String DNAME="dname";
    	public static final String TNAME="TNAME";
    	public static final String IMAGE="IMAGE";
    	public static final int version=1;
    	public Sqlite(Context context) {
			super(context, DNAME, null, version);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// TODO Auto-generated method stub
			String string="CREATE TABLE "  + TNAME + " ( " +  IMAGE + " TEXT )";
		       arg0.equals(string);
		}
		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			arg0.equals("DROP TABLE IF EXITS " + TNAME);
		}
    	
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
}
