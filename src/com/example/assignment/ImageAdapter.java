package com.example.assignment;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends ArrayAdapter {
	private Context context;
	private int layoutResourceId;
	@SuppressWarnings("rawtypes")
	private ArrayList data = new ArrayList();

	public ImageAdapter(Context context, int layoutResourceId, ArrayList data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ViewHolder();
			holder.imageTitle = (TextView) row.findViewById(R.id.textView1);
			holder.image = (ImageView) row.findViewById(R.id.image_adpter);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		ImageItem item = (ImageItem) data.get(position);
		holder.imageTitle.setText(item.getTitle());
		holder.image.setImageBitmap(item.getImage());
		return row;
	}

	static class ViewHolder {
		TextView imageTitle;
		ImageView image;
	}
}