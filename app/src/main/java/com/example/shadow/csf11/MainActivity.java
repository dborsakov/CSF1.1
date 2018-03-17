package com.example.shadow.csf11;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> numberlsit = new ArrayList<>();
    ListView listView;
    Button tue, wed, thu, fri;
    int todaynumber = 1;
    int dd = 0;
    int ddn = 0;
    int sj = 0;
    boolean b;
    String ddk;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.lv);
        tue = (Button)findViewById(R.id.Tue);
        wed = (Button)findViewById(R.id.Wed);
        thu = (Button)findViewById(R.id.Thu);
        fri = (Button)findViewById(R.id.Fri);
        txt = (TextView)findViewById(R.id.textView3) ;

        tue.setOnClickListener(this);
        wed.setOnClickListener(this);
        thu.setOnClickListener(this);
        fri.setOnClickListener(this);

        get_JSON(todaynumber);
    }





    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.Tue: todaynumber = 1;
            get_JSON(todaynumber);
            break;
            case R.id.Wed: todaynumber = 2;
            get_JSON(todaynumber);
            break;
            case R.id.Thu: todaynumber = 3;
            get_JSON(todaynumber);
            break;
            case  R.id.Fri: todaynumber = 4;
            get_JSON(todaynumber);
            break;
        }
    }

    public boolean j_z (boolean jz){
        if(jz==true)
            return true;
        else
            return false;
    }

    public void get_JSON(int k){
        String json = null;
        String name = "";
        String place = "";
        String time = "";
        long day;


        try {

            Time timek = new Time();
            dd = timek.main();
            if (dd%7!=0 && b){
                do {
                    dd--;
                    ddn = dd;
                } while (dd%7==0);
                sj = ddn/7;
                if (sj%2==0){
                    txt.setText("Числитель");
                    j_z(true);
                }
                else {
                    txt.setText("Знаменатель");
                    j_z(false);
                }
            }
            else{
                if (sj%2==0){
                    txt.setText("Числитель");
                    j_z(true);
                }
                else {
                    txt.setText("Знаменатель");
                    j_z(false);
                }
            }

            if (j_z(b)==true) {
                InputStream is = getAssets().open("timetablez.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();


                json = new String(buffer, "UTF-8");
                JSONArray jsonArray = new JSONArray(json);
                List<String> listname = new ArrayList<String>();
                ArrayList<HashMap<String, String>> subjectList;
                subjectList = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject object = jsonArray.getJSONObject(i);
                    name = object.getString("name");
                    place = object.getString("place");
                    time = object.getString("startTime");
                    day = object.getLong("day");


                    if (day == k) {
                        HashMap<String, String> subject = new HashMap<>();
                        subject.put("name", name);
                        subject.put("place", place);
                        subject.put("startTime", time);
                        subjectList.add(subject);
                    }
                }

                ListAdapter adapter = new SimpleAdapter(MainActivity.this, subjectList, R.layout.listview,
                        new String[]{"name", "place", "startTime"}, new int[]{R.id.textView2, R.id.textView, R.id.textView4});
                //ArrayAdapter adapter1 = new ArrayAdapter<String>(this,R.layout.listview, R.id.textView2 ,listplace);
                ListView listView = (ListView) findViewById(R.id.lv);
                listView.setAdapter(adapter);

            }
            else {
                InputStream is = getAssets().open("timetable.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();


                json = new String(buffer, "UTF-8");
                JSONArray jsonArray = new JSONArray(json);
                List<String> listname = new ArrayList<String>();
                ArrayList<HashMap<String, String>> subjectList;
                subjectList = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject object = jsonArray.getJSONObject(i);
                    name = object.getString("name");
                    place = object.getString("place");
                    time = object.getString("startTime");
                    day = object.getLong("day");


                    if (day == k) {
                        HashMap<String, String> subject = new HashMap<>();
                        subject.put("name", name);
                        subject.put("place", place);
                        subject.put("startTime", time);
                        subjectList.add(subject);
                    }
                }

                ListAdapter adapter = new SimpleAdapter(MainActivity.this, subjectList, R.layout.listview,
                        new String[]{"name", "place", "startTime"}, new int[]{R.id.textView2, R.id.textView, R.id.textView4});
                //ArrayAdapter adapter1 = new ArrayAdapter<String>(this,R.layout.listview, R.id.textView2 ,listplace);
                ListView listView = (ListView) findViewById(R.id.lv);
                listView.setAdapter(adapter);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }



    }



}


