package com.example.ex22;

import android.os.AsyncTask;
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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnParse;
    ListView lv1;
    ArrayAdapter<String> myAdapter;
    ArrayList<String> myList;
    String URL = "https://api.androidhive.info/pizza/?format=xml";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnParse = (Button) findViewById(R.id.btnParse);
        lv1 = (ListView) findViewById(R.id.lv1);
        myList = new ArrayList<>();
        myAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,myList);
        lv1.setAdapter(myAdapter);
        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadExampleTask task = new LoadExampleTask();
                task.execute();
            }
        });
    }
    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<String>>
    {
        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            ArrayList<String> list = new ArrayList<String>();
            try {
                XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
                XmlPullParser parser = fc.newPullParser();
                XMLParser myParser = new XMLParser();
                String xml = myParser.getXMLFromUrl(URL);
                parser.setInput(new StringReader(xml));
                int eventType = -1;
                String nodeName;
                String dataShow="";
                while (eventType!=XmlPullParser.END_DOCUMENT)
                {
                    eventType = parser.next();
                    switch (eventType)
                    {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            nodeName = parser.getName();
                            if(nodeName.equals("id")){
                                dataShow+=parser.nextText()+"-";
                            } else if (nodeName.equals("name")) {
                                dataShow+=parser.nextText();
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            nodeName=parser.getName();
                            if(nodeName.equals("item")){
                                list.add(dataShow);
                                dataShow="";
                            }
                            break;
                    }
                }
            } catch (XmlPullParserException e){
                e.printStackTrace();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myAdapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            super.onPostExecute(result);
            myAdapter.clear();
            myAdapter.addAll(result);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }
    }
}