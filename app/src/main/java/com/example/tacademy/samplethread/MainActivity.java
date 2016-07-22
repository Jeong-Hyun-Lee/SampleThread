package com.example.tacademy.samplethread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progressBar;
    private static final int PROGRESS_COUNT = 1;
    private static final int PROGRESS_DONE = 2;
    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case PROGRESS_COUNT:
                    textView.setText("Progress :" + msg.arg1);
                    progressBar.setProgress(msg.arg1);
                    break;
                case PROGRESS_DONE:
                    textView.setText("Progress Done");
                    progressBar.setProgress(100);
                    break;
            }
        }
    };


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
                progressBar.setMax(100);
                textView.setText("progressBar :");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int count = 0;
                        while (count <= 100){
                            Message message = handler.obtainMessage(PROGRESS_COUNT,count,0);
                            handler.sendMessage(message);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            count +=5;
                        }
                        handler.sendEmptyMessage(PROGRESS_DONE);
                    }
                }).start();
            }
        });
    }
}
