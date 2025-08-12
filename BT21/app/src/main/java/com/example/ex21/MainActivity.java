package com.example.ex21;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnParse;
    ListView lv;
    ArrayList<String> myList;
    ArrayAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnParse = findViewById(R.id.btnParse);
        lv = findViewById(R.id.lv1);
        myList = new ArrayList<>();
        myAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,myList);
        lv.setAdapter(myAdapter);
        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseJson();
            }
            private void parseJson() {
                String json = null;
                try {
                    InputStream inputStream = getAssets().open("computer.json");
                    int size = inputStream.available();
                    byte[] buffer = new byte[size];
                    inputStream.read(buffer);
                    inputStream.close();
                    json = new String(buffer, "UTF-8");
                    JSONObject reader = new JSONObject(json);
                    myList.add(reader.getString("MaDM"));
                    myList.add(reader.getString("TenDM"));
                    JSONArray myArray = reader.getJSONArray("Sanphams");
                    for (int i = 0; i<myArray.length(); i++){
                        JSONObject myObj = myArray.getJSONObject(i);
                        myList.add(myObj.getString("MaSP") + " - " + myObj.getString("TenSP"));
                        myList.add(myObj.getString("SoLuong") + " " + myObj.getString("DonGia") + " = " + myObj.getString("ThanhTien"));
                        myList.add(myObj.getString("Hinh"));
                    }
                    myAdapter.notifyDataSetChanged();
                }catch (IOException e1){
                    e1.printStackTrace();
                }catch (JSONException e2){
                    e2.printStackTrace();
                }
            }
        });
    }
}
