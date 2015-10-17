package com.example.assignment;

import android.graphics.Bitmap;

public class Image {
public Bitmap bitmap;

public Image(Bitmap photo) {
	// TODO Auto-generated constructor stub
}
public void Image(Bitmap b) {
	bitmap = b;
}
public Bitmap getBitmap() {
	return bitmap;
}

public void setBitmap(Bitmap bitmap) {
	this.bitmap = bitmap;
}

}
