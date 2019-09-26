package com.example.pc_controller;

import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.net.Socket;

public class basic_key_listener_interface
{
    Socket client;

    basic_key_listener_interface(Socket client_in)
    {
        client = client_in;
    }

    public void set_basic_key_button_listener(Button key_button_in, final String key_tag_in)
    {
        key_button_in.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    new user_input_sender(client).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, "p", key_tag_in);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    new user_input_sender(client).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, "r", key_tag_in);
                }
                return false;
            }
        });
    }

    public void set_hotkey_button_listener(Button key_button_in, final String key_tag_in)
    {
        key_button_in.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    new user_input_sender(client).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, "p", key_tag_in);
                }
                return false;
            }
        });
    }
}