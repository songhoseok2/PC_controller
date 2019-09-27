package com.example.pc_controller;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class gesture_detector_class extends GestureDetector.SimpleOnGestureListener implements GestureDetector.OnDoubleTapListener
{
    @Override
    public boolean onDown(MotionEvent event)
    {
        Log.d("TAG","onDown: ");
        // don't return false here or else none of the other
        // gestures will work
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e)
    {
        Log.i("TAG", "onSingleTapConfirmed: ");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e)
    {
        Log.i("TAG", "onLongPress: ");
    }

    @Override
    public boolean onDoubleTap(MotionEvent e)
    {
        Log.i("TAG", "onDoubleTap: ");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY)
    {
        Log.i("TAG", "onScroll: ");
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY)
    {
        Log.d("TAG", "onFling: ");
        return true;
    }
}