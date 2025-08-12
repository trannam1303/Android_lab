package com.example.ex24;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView lvTigia;
    TextView txtdate;
    ArrayList<com.example.pj24.TyGia> dstygia;
    MyArrayAdapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvTigia = (ListView) findViewById(R.id.lv1);
        txtdate = (TextView) findViewById(R.id.txtdate);
        getdate();
        dstygia = new ArrayList<com.example.pj24.TyGia>();
        myadapter = new MyArrayAdapter(MainActivity.this, R.layout.layout_listview, dstygia);
        lvTigia.setAdapter(myadapter);
        TyGiaTask task = new TyGiaTask();
        task.execute();
    }

    public void getdate(){

        Date currentDate = Calendar.getInstance().getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        txtdate.setText("Hôm nay: " + simpleDateFormat.format(currentDate));
    }

    class TyGiaTask extends AsyncTask<Void, Void, ArrayList<com.example.pj24.TyGia>> {
        @Override
        protected ArrayList<com.example.pj24.TyGia> doInBackground(Void... voids) {
            ArrayList<com.example.pj24.TyGia> ds = new ArrayList<>();
            try {
                URL url = new URL("http://dongabank.com.vn/exchange/export");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json; charset=utf-8");
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible)");
                connection.setRequestProperty("Accept", "*/*");

                int responseCode = connection.getResponseCode();
                Log.d("HTTP_RESPONSE_CODE", String.valueOf(responseCode));

                if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM) {
                    String newUrl = connection.getHeaderField("Location");
                    Log.d("Redirected_URL", newUrl);
                    connection = (HttpURLConnection) new URL(newUrl).openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-type", "application/json; charset=utf-8");
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible)");
                    connection.setRequestProperty("Accept", "*/*");
                }


                InputStream is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    responseBuilder.append(line);
                }
                String json = responseBuilder.toString().trim();
                Log.d("HTTP_RESPONSE_BODY", json);

                if (json.isEmpty()) {
                    Log.e("JSON_ERROR", "Received empty JSON response");
                    return ds;
                }


                json = json.replace("(", "").replace(")", "");
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    com.example.pj24.TyGia tyGia = new com.example.pj24.TyGia();
                    tyGia.setType(item.getString("type"));
                    if (item.has("imageurl")) {
                        tyGia.setImageurl(item.getString("imageurl"));
                        URL imageUrl = new URL(tyGia.getImageurl());
                        HttpURLConnection imageConnection = (HttpURLConnection) imageUrl.openConnection();
                        imageConnection.setRequestMethod("GET");
                        imageConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible)");
                        imageConnection.setRequestProperty("Accept", "*/*");
                        Bitmap bitmap = BitmapFactory.decodeStream(imageConnection.getInputStream());
                        tyGia.setBitmap(bitmap);
                    }
                    if (item.has("muatienmat")) {
                        tyGia.setMuatienmat(item.getString("muatienmat"));
                    }
                    if (item.has("muack")) {
                        tyGia.setMuack(item.getString("muack"));
                    }
                    if (item.has("bantienmat")) {
                        tyGia.setBantienmat(item.getString("bantienmat"));
                    }
                    if (item.has("banck")) {
                        tyGia.setBanck(item.getString("banck"));
                    }
                }
                Log.d("JSON_DONGA", json);
            } catch (Exception ex) {
                Log.e("Fail!!!", ex.toString());
            }
            return ds;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myadapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<com.example.pj24.TyGia> tyGias) {
            super.onPostExecute(tyGias);
            myadapter.clear();
            myadapter.addAll(tyGias);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }
    }
}