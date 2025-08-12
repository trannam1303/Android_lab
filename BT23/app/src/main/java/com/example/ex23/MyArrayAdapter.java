package com.example.ex23;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<List> {
    private Activity context;
    private ArrayList<List> arr;
    private int layoutID;

    public MyArrayAdapter(Activity context, ArrayList<List> arr, int layoutID) {
        super(context, layoutID, arr);
        this.context = context;
        this.arr = arr;
        this.layoutID = layoutID;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutID, null);
        }

        final List lst = arr.get(position);

        // Gán ảnh từ `lst`
        ImageView imgItem = convertView.findViewById(R.id.imgView);
        Bitmap imageBitmap = lst.getImage(); // Giả sử `getImage()` trả về Bitmap
        imgItem.setImageBitmap(imageBitmap);

        // Gán tiêu đề và thông tin
        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        txtTitle.setText(lst.getTitle()); // Giả sử `getTitle()` trả về tiêu đề

        TextView txtInfo = convertView.findViewById(R.id.txtInfo);
        txtInfo.setText(lst.getDescription()); // Giả sử `getDescription()` trả về mô tả

        return convertView;
    }
}
