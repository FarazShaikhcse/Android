package com.cs037.radfar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RadioGroup rg1;
    RadioButton r1;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg1=(RadioGroup)findViewById(R.id.rg);
        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
int slid=rg1.getCheckedRadioButtonId();
r1=(RadioButton)findViewById(slid);
if(slid==-1)
{
    Toast.makeText(getApplicationContext(),"Please select",Toast.LENGTH_SHORT).show();
}
else
{
    Toast.makeText(getApplicationContext(),r1.getText(),Toast.LENGTH_SHORT).show();
}
    }
}
