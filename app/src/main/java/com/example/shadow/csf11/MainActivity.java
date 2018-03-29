package com.example.shadow.csf11;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
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

    ListView listView;
    TextView txt;
    Button tue, wed, thu, fri, CW;

    String[] sTypeOfWeek = {"Числитель", "Знаменатель"};
    String[] sNameDay = {"","Понедельник","Вторник","Среда","Четверг","Пятница","Суббота"};

    String jsonChisl = "timetable.json";
    String jsonZnam = "timetable.json";

    /*
    String jsonChisl = "any_chis.json";
    String jsonZnam = "any_zn.json";
    */

    String debug = "MyLog";
    int typeOfWeek, dayOfWeek, chekDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.lv);
        tue = (Button)findViewById(R.id.Tue);
        wed = (Button)findViewById(R.id.Wed);
        thu = (Button)findViewById(R.id.Thu);
        fri = (Button)findViewById(R.id.Fri);
        CW = (Button)findViewById(R.id.CW);
        txt = (TextView)findViewById(R.id.textView3) ;

        Time time = new Time(12, 2, 2018);
        typeOfWeek = time.getTypeOfWeek();
        Log.v(debug,"tow="+typeOfWeek);

        dayOfWeek=time.getDayOfWeek();
        //dayOfWeek--;
        chekDay=dayOfWeek;
        setText();
        Log.v(debug, "dow="+dayOfWeek);

        getSubject(typeOfWeek,dayOfWeek);

        tue.setOnClickListener(this);
        wed.setOnClickListener(this);
        thu.setOnClickListener(this);
        fri.setOnClickListener(this);
        CW.setOnClickListener(this);
        //get_JSON(todaynumber);
    }

    public void setText(){
        setTitle(sNameDay[dayOfWeek]);
        txt.setText(sTypeOfWeek[typeOfWeek]);
    }

    public void setText(int day, int week){
        setTitle(sNameDay[day]);
        txt.setText(sTypeOfWeek[week]);
    }

    public void getSubject(int typOfWeek, int countDay){

        String json = null;
        String name = "";
        String place = "";
        String time = "";
        String prof="";
        long day;

        try {
            InputStream is = null;
            if(typOfWeek==0) {
                 is = getAssets().open(jsonChisl);
           }
            if(typOfWeek==1) {
                 is = getAssets().open(jsonZnam);
            }
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();


            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            //  List<String> listname = new ArrayList<String>();
            ArrayList<HashMap<String, String>> subjectList;
            subjectList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = jsonArray.getJSONObject(i);
                name = object.getString("name");
                place = object.getString("place");
                time = object.getString("startTime");
                day = object.getLong("day");


                if (day == countDay) {
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

        catch (IOException e1) {
            e1.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    /*
    public String tOfWeek(){
        if (typeOfWeek==0)
            return "Числитель";
        if (typeOfWeek==1)
            return "Знаменатель";
        return "";
    }
    */

    @Override
    public void onClick(View view){
        Resources res = getResources();
        Log.v(debug,"тип недели в обработчике="+typeOfWeek);


        if (view.getId()==R.id.CW){
            boolean b=true;
            if ((typeOfWeek==0) & (b)) {
                typeOfWeek = 1;
                getSubject(1,chekDay);
                setText(chekDay,1);
                b=!b;

            }
            else if ((typeOfWeek==1) & (b)) {
                typeOfWeek=0;
                getSubject(0,chekDay);
                setText(chekDay,1);
                b=!b;
            }

        }
        Log.v(debug,"тип недели в обработчике изменили="+typeOfWeek);


        switch (view.getId()){
            case R.id.Tue:
            setTitle(res.getString(R.string.tue));
            getSubject(typeOfWeek,2);
            chekDay=2;
            break;
            case R.id.Wed:
            setTitle(res.getString(R.string.wen));
            getSubject(typeOfWeek,3);
            chekDay=3;
            break;
            case R.id.Thu:
            setTitle(res.getString(R.string.thu));
            getSubject(typeOfWeek,4);
            chekDay=4;
            break;
            case  R.id.Fri:
            setTitle(res.getString(R.string.fri));
            getSubject(typeOfWeek,5);
            chekDay=5;
            break;
        }
    }
}


