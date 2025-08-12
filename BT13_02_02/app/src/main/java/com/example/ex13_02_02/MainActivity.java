package com.example.ex13_02_02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String[] arrayName = {"Ảnh 1", "Ảnh 2", "Ảnh 3", "Ảnh 4", "Ảnh 5", "Ảnh 6"};
    public static int[] imageName = {R.drawable.ha, R.drawable.hn, R.drawable.halong, R.drawable.tb, R.drawable.london, R.drawable.paris};
    GridView gridViewDemo;
    MyArrayAdapter adapterDanhSach;
    ArrayList<Image> arrimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridViewDemo = (GridView) findViewById(R.id.grid1);
        arrimage = new ArrayList<Image>();
        adapterDanhSach = new MyArrayAdapter(MainActivity.this, R.layout.listitem, arrimage);
        gridViewDemo.setAdapter(adapterDanhSach);
        for (int i = 0; i < imageName.length;i++){
            Image myimage = new Image();
            myimage.setName(arrayName[i]);
            myimage.setImg(imageName[i]);
            arrimage.add(myimage);
            adapterDanhSach.notifyDataSetChanged();
        }
        gridViewDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(MainActivity.this, SubActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("TITLE", i);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });
    }
}