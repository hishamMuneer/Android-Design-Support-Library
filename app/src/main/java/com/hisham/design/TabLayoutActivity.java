package com.hisham.design;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class TabLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

//        final Button textView = (Button)(findViewById(R.id.tvHello));
//        textView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    textView.setElevation(32);
//                    textView.setTranslationZ(64);
//                } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                    textView.setElevation(2);
//                    textView.setTranslationZ(8);
//                }
//                return true;
//            }
//        });
    }
}
