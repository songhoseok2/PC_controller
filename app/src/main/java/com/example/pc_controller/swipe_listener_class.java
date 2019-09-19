package com.example.pc_controller;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import java.net.Socket;
//import java.lang.instrument.Instrumentation;

public class swipe_listener_class implements View.OnTouchListener
{
    private VelocityTracker mVelocityTracker = null;
    GestureDetector myDetector;
    private Socket client;
    public swipe_listener_class(Socket client_in, GestureDetector myDetector_in)
    {
        client = client_in;
        myDetector = myDetector_in;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        //code from https://developer.android.com/training/gestures/movement
        // pass the events to the gesture detector
        // a return value of true means the detector is handling it
        // a return value of false means the detector didn't
        // recognize the event
        //return myDetector.onTouchEvent(event);

        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                if (mVelocityTracker == null)
                {
                    // Retrieve a new VelocityTracker object to watch the
                    // velocity of a motion.
                    mVelocityTracker = VelocityTracker.obtain();
                }
                else
                {
                    // Reset the velocity tracker back to its initial state.
                    mVelocityTracker.clear();
                }
                // Add a user's movement to the tracker.
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(event);
                // When you want to determine the velocity, call
                // computeCurrentVelocity(). Then call getXVelocity()
                // and getYVelocity() to retrieve the velocity for each pointer ID.
                mVelocityTracker.computeCurrentVelocity(10);
                // Log velocity of pixels per second
                // Best practice to use VelocityTrackerCompat where possible.
//                double x = (mVelocityTracker.getXVelocity(pointerId));
//                double y = (mVelocityTracker.getYVelocity(pointerId));
//                String x_movement = String.format("%.3f", x);
//                String y_movement = String.format("%.3f", y);
                String x_movement = Float.toString(mVelocityTracker.getXVelocity(pointerId));
                String y_movement = Float.toString(mVelocityTracker.getYVelocity(pointerId));

                //Log.d("TAG", Long.toString(ObjectSizeCalculator.getObjectSize(data_to_send)));
                Log.d("TAG", "X velocity: " + x_movement);
                Log.d("TAG", "Y velocity: " + y_movement);
                new user_input_sender(client).execute("s", x_movement, y_movement);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // Return a VelocityTracker object back to be re-used by others.
                //mVelocityTracker.recycle();
                break;
        }
        return true;
    }
}