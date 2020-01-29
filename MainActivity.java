package com.cs037.progressfar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar pb;
    Button b1;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb=(ProgressBar)findViewById(R.id.progressBar2);
        b1=(Button)findViewById(R.id.button);
        t1=(TextView)findViewById(R.id.textView);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        pb.setProgress(0);
        T t1=new T();
        t1.execute("100");
    }
    class T extends AsyncTask<String,Integer,String>
    {
        int i=0;

        @Override
        protected String doInBackground(String... strings) {
            int max=Integer.parseInt(strings[0]);
            while(i<max)
            {
                try{
                    Thread.sleep(100);
                    i++;
                    publishProgress(i);
                }
                catch(Exception e)
                {

                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            pb.setProgress(values[0]);
            super.onProgressUpdate(values);
            t1.setText(values[0].toString());
        }
    }
}
