package com.cs037.database;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Button insert, select, call,delete,update;
    EditText name, usn, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Db(this).getWritableDatabase();
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
        insert = (Button) findViewById(R.id.button);
        select = (Button) findViewById(R.id.button2);
        delete=(Button)findViewById(R.id.button4);
        name = (EditText) findViewById(R.id.editText);
        usn = (EditText) findViewById(R.id.editText2);
        phone = (EditText) findViewById(R.id.editText3);
        call = (Button) findViewById(R.id.button3);
        update=(Button)findViewById(R.id.button5);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String iname = name.getText().toString();
                String iusn = usn.getText().toString();
                String iphone = phone.getText().toString();
                ContentValues values = new ContentValues();
                values.put("name", iname);
                values.put("usn", iusn);
                values.put("phone", iphone);
                db.insert("student", null, values);
                Toast.makeText(getApplicationContext(), "Insertted", Toast.LENGTH_SHORT).show();
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = db.rawQuery("select * from student", null);
                if (cursor.getCount() <= 0)
                    Toast.makeText(getApplicationContext(), "no records", Toast.LENGTH_SHORT).show();
                else {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        cursor.moveToNext();
                        String stud = "";
                        stud += "Name : " + cursor.getString(0);
                        stud += "\n USN : " + cursor.getString(1);
                        stud += "\n Phone number : " + cursor.getString(2);
                        Toast.makeText(getApplicationContext(), stud, Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {


                Cursor cursor = db.rawQuery("select * from student where usn='"+usn.getText().toString()+"'", null);
                cursor.moveToNext();
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + cursor.getString(2)));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u=usn.getText().toString();
                db.delete("student","usn"+"=?",new String[]{u});


            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String us=usn.getText().toString();
                String ph=phone.getText().toString();
                ContentValues v=new ContentValues();
                v.put("phone",ph);
                db.update("student",v,"usn"+"=?",new String[]{us});
            }
        });
    }
}
