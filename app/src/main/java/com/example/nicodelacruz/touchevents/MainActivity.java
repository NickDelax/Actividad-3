package com.example.nicodelacruz.touchevents;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.GestureDetectorCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    // Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this,this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        TextView textView = findViewById(R.id.hello_world);
        textView.setText("Coordenadas : " +
                String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        changeBackgroundColor(Color.RED);
        TextView textView = findViewById(R.id.hello_world);
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(50);
        textView.setText("Abajo");
        Log.d(DEBUG_TAG,"Abajo: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        changeBackgroundColor(Color.BLACK);
        TextView textView = findViewById(R.id.hello_world);
        textView.setTextColor(Color.GREEN);
        textView.setTextSize(40);
        textView.setText("Deslizar");
        Log.d(DEBUG_TAG, "Deslizar: " + event1.toString() + event2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        changeBackgroundColor(Color.RED);
        TextView textView = findViewById(R.id.hello_world);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(30);
        textView.setText("Toque Largo");
        Log.d(DEBUG_TAG, "Toque Largo: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                            float distanceY) {
        changeBackgroundColor(Color.LTGRAY);
        TextView textView = findViewById(R.id.hello_world);
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(60);
        textView.setText("Scroll");
        Log.d(DEBUG_TAG, "Scroll: " + event1.toString() + event2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        changeBackgroundColor(Color.CYAN);
        TextView textView = findViewById(R.id.hello_world);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(50);
        textView.setText("Mostrar toque");
        Log.d(DEBUG_TAG, "Mostrar toque: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        changeBackgroundColor(Color.YELLOW);
        TextView textView = findViewById(R.id.hello_world);
        textView.setTextColor(Color.CYAN);
        textView.setTextSize(35);
        textView.setText("Un solo toque");
        Log.d(DEBUG_TAG, "Un toque: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        changeBackgroundColor(Color.TRANSPARENT);
        TextView textView = findViewById(R.id.hello_world);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(40);
        textView.setText("Doble toque");
        Log.d(DEBUG_TAG, "Doble toque: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        changeBackgroundColor(Color.BLACK);
        TextView textView = findViewById(R.id.hello_world);
        textView.setTextColor(Color.RED);
        textView.setTextSize(45);
        textView.setText("Doble Toque evento");
        Log.d(DEBUG_TAG, "Doble toque evento: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        changeBackgroundColor(Color.MAGENTA);
        TextView textView = findViewById(R.id.hello_world);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(55);
        textView.setText("Toque Confirmado");
        Log.d(DEBUG_TAG, "Toque Confirmado: " + event.toString());
        return true;
    }

    public void changeBackgroundColor(int color) {
        RelativeLayout view  = findViewById(R.id.main_layout);
        Drawable background = view.getBackground();
        int colorFrom = Color.TRANSPARENT;
        if (background instanceof ColorDrawable)
            colorFrom = ((ColorDrawable) background).getColor();
        int colorTo = color;
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(650); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                RelativeLayout view  = findViewById(R.id.main_layout);
                view.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();


//        view.setBackgroundColor(color);
    }
}
