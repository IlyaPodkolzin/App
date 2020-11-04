package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    StringBuilder sb = new StringBuilder();
    private Button btn, btn2;
    private ConstraintLayout cl;
    private TextView tv;
    private int upPI = -1;
    private int downPI = 0;
    private boolean inTouch = false;
    private String res = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.background_press2);
        tv.setOnTouchListener(this);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cl.setBackgroundColor(Color.RED);
            }
        });

        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        cl = findViewById(R.id.root);
        cl.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                Snackbar.make(findViewById(R.id.root), R.string.ura, Snackbar.LENGTH_LONG).show();
                break;
            case R.id.root:
                cl.setBackgroundColor(Color.parseColor("#cccccc"));

        }


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int actionMask = motionEvent.getActionMasked();
        int pointerIndex = motionEvent.getActionIndex();
        int pointerCount = motionEvent.getPointerCount();

        switch (actionMask) {
            case MotionEvent.ACTION_DOWN:
                inTouch = true;
            case MotionEvent.ACTION_POINTER_DOWN:
                downPI = pointerIndex;
                break;

            case MotionEvent.ACTION_UP:
                inTouch = false;
                sb.setLength(0);
                pointerCount = 0;

            case MotionEvent.ACTION_POINTER_UP:
                upPI = pointerIndex;
                break;

            case MotionEvent.ACTION_MOVE:
                sb.setLength(0);
                for (int i = 0; i < 10; i++) {
                    if (i < pointerCount) {
                        sb.append("№ пальца: " + (i + 1));
                        sb.append(", X = " + motionEvent.getX(i));
                        sb.append(", Y = " + motionEvent.getY(i));
                    }
                    sb.append("\n");
                }
                break;
        }

        res = "СТАТИСТИКА НАЖАТИЯ НА ОКНО\n(несколько пальцев):\nОпущен палец №: " + (downPI + 1) + "\n";
        if (upPI != -1) {
            res += "Поднят палец №: " + (upPI + 1) + "\n";
        }
        res += "Количество пальцев: = " + pointerCount + "\n" + sb.toString();

        tv.setText(res);
        return true;
    }
}