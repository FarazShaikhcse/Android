package com.cs037.listviewfar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
DatePicker d1;
ListView l1;
ArrayAdapter ad;
String branch[]={"CS","EC","IS","MECH","EE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        d1=(DatePicker)findViewById(R.id.d1);
        l1=(ListView)findViewById(R.id.l1);
        ad=new ArrayAdapter(this,R.layout.layout61,branch);
        l1.setAdapter(ad);
        l1.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
String s=d1.getDayOfMonth()+" "+(d1.getMonth()+1)+" "+d1.getYear();
String s1=(String)adapterView.getItemAtPosition(i);
        Toast.makeText(getApplicationContext(),s+" "+s1,Toast.LENGTH_SHORT).show();
    }
}
