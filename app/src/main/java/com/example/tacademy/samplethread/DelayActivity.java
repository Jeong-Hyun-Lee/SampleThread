package com.example.tacademy.samplethread;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DelayActivity extends AppCompatActivity {
    TextView textView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        textView = (TextView) findViewById(R.id.text_view);
        Button btn = (Button) findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = 0;
                progressBar.setMax(100);
                textView.setText("download start...");
                handler.post(runnable);
            }
        });
    }
    int progress =0;
    Handler handler = new Handler(Looper.getMainLooper());
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(progress <=100){
                textView.setText("Progress :" + progress);
                progressBar.setProgress(progress);
                progress +=5;
                handler.postDelayed(this,500);
            }else{
                textView.setText("Progress Done");
                progressBar.setProgress(100);
            }
        }
    };
}
