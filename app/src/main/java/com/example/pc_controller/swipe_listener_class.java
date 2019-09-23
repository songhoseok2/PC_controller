package com.example.pc_controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.TextView;

import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;

import static java.lang.Math.abs;
//import java.lang.instrument.Instrumentation;

public class swipe_listener_class implements View.OnTouchListener
{
    TextView label;
    private VelocityTracker mVelocityTracker = null;
    GestureDetector myDetector;
    private Socket client;

    Deque<Float> x_deq = new ArrayDeque<Float>();
    Deque<Float> y_deq = new ArrayDeque<Float>();

    public swipe_listener_class(Socket client_in, GestureDetector myDetector_in, TextView label_in)
    {
        label = label_in;
        client = client_in;
        myDetector = myDetector_in;
    }

    static int median(float a[], int l, int r)
    {
        int n = r - l + 1;
        n = (n + 1) / 2 - 1;
        return n + l;
    }

    static float Q1(float[] a, int n)
    {
        return a[median(a, 0, median(a, 0, n))];
    }

    static float Q3(float[] a, int n)
    {
        return a[median(a, median(a, 0, n) + 1, n)];
    }

    boolean is_outlier(float [] a, int n, float new_value)
    {
        Arrays.sort(a);
        float Q1 = Q1(a, n);
        float Q3 = Q3(a, n);
        float IQR = Q3 - Q1;
        float inner_min_fence = Q1 - IQR * (float)1.5;
        float inner_max_fence = Q3 + IQR * (float)1.5;

        return !(inner_min_fence < new_value && new_value < inner_max_fence);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {

        //code from https://developer.android.com/training/gestures/movement

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
                Float x_movement = mVelocityTracker.getXVelocity(pointerId);
                Float y_movement = mVelocityTracker.getYVelocity(pointerId);
                String x_movement_str = Float.toString(x_movement);
                String y_movement_str = Float.toString(y_movement);
                label.setText("x_movement: " + x_movement_str + ", y_movement: " + y_movement_str);
                if(Float.valueOf(x_movement) != 0 && Float.valueOf(y_movement) != 0) {
                    x_deq.add(x_movement);
                    y_deq.add(y_movement);
                    if (x_deq.size() > 50)
                    {
                        x_deq.removeFirst();
                    }
                    if (y_deq.size() > 50)
                    {
                        y_deq.removeFirst();
                    }

                    float[] x_arr = new float[50];
                    float[] y_arr = new float[50];
                    Object[] temp_x = x_deq.toArray();
                    Object[] temp_y = y_deq.toArray();

                    for(int arr_index = 0; arr_index < temp_x.length; ++arr_index)
                    {
                        x_arr[arr_index] = (float)temp_x[arr_index];
                        y_arr[arr_index] = (float)temp_y[arr_index];
                    }


                    boolean clear_to_proceed = true;
                    if (is_outlier(x_arr, x_deq.size(), x_movement))
                    {
                        clear_to_proceed = false;
                        Log.d("velocity_tag", "x movenment: " + x_movement_str + " is an outlier. skipping.");
                    }
                    if (is_outlier(y_arr, y_deq.size(), y_movement))
                    {
                        clear_to_proceed = false;
                        Log.d("velocity_tag", "y movement: " + y_movement_str + " is an outlier. skipping.");
                    }

                    //if(clear_to_proceed) {  }
                    new user_input_sender(client).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, "s", x_movement_str, y_movement_str);
                }
                else
                {
                    Log.d("velocity_tag", "Skipping because there was no movement");
                }

                break;
            case MotionEvent.ACTION_UP:
                x_deq.clear();
                y_deq.clear();
                break;
            case MotionEvent.ACTION_CANCEL:
                // Return a VelocityTracker object back to be re-used by others.
                //mVelocityTracker.recycle();
                break;
        }

        // pass the events to the gesture detector
        // a return value of true means the detector is handling it
        // a return value of false means the detector didn't
        // recognize the event
        return myDetector.onTouchEvent(event);

    }
}