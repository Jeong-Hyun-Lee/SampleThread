package com.example.tacademy.samplethread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CountDownActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        textView = (TextView)findViewById(R.id.text_count);
        Button button = (Button)findViewById(R.id.btn_count);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                count = 10;
                startTime = -1;
                handler.removeCallbacks(runnable);
                handler.post(runnable);
            }
        });
    }

    Handler handler = new Handler(Looper.getMainLooper());
    int count = 10;
    long startTime = -1;
    Runnable runnable = new Runnable() {


        @Override
        public void run() {
            long time= SystemClock.elapsedRealtime();
            if(startTime == -1){
                startTime = time;
            }
            int gap = (int) (time - startTime);
            int count = 10 - gap/1000;
            int rest = 1000- (gap%1000);
            if(count >=0){
                textView.setText("count : "+count);
                handler.postDelayed(this,rest);
            }else {
                textView.setText("done");
            }
        }
    };
}
