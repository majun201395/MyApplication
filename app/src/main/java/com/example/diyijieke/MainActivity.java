package com.example.diyijieke;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static android.os.Build.VERSION_CODES.O;
public class MainActivity extends AppCompatActivity {
    private TextView text;
    private int a = 3;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            a--;
            handler.postDelayed(runnable, 2000);
            if (a==0){
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        handler.postDelayed(runnable, 2000);
    }
    private void initView() {
        text = (TextView) findViewById(R.id.text);
    }
}
