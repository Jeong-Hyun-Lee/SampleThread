package com.example.tacademy.samplethread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PostActivity extends AppCompatActivity {


    TextView textView;
    ProgressBar progressBar;

    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

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
                            handler.post(new RunProgress(count));
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            count +=5;
                        }
                    handler.post(new DoneProgress());
                    }
                }).start();
            }
        });
    }

    class RunProgress implements Runnable{
        int progress=0;

        public RunProgress(int progress) {
            this.progress = progress;
        }

        @Override
        public void run() {
            textView.setText("Progress :" + progress);
            progressBar.setProgress(progress);
        }
    }
    class DoneProgress implements Runnable{

        @Override
        public void run() {
            textView.setText("Progress Done");
            progressBar.setProgress(100);
        }
    }
}
