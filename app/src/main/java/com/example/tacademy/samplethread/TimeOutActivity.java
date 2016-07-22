package com.example.tacademy.samplethread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class TimeOutActivity extends AppCompatActivity {
    public static final int MESSAGE_BACK_KEY_TIMEOUT = 1;
    public static final int TIME_DEL = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_out);

    }
    boolean isBackPressed = false;

    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_BACK_KEY_TIMEOUT:
                    isBackPressed=false;
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if(!isBackPressed){
            Toast.makeText(this,"finish" , Toast.LENGTH_SHORT).show();
            isBackPressed = true;
            handler.sendEmptyMessageDelayed(MESSAGE_BACK_KEY_TIMEOUT,TIME_DEL);
        }else{
            handler.removeMessages(MESSAGE_BACK_KEY_TIMEOUT);
            finish();
        }
    }
}
