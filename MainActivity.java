package com.cs037.fardemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener {
DatePicker d1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        d1=(DatePicker)findViewById(R.id.d1);
        d1.setOnDateChangedListener(this);
    }

    @Override
    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
String s=d1.getDayOfMonth()+" "+(d1.getMonth()+1)+" "+d1.getYear();
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}
