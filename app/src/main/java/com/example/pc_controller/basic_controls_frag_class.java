package com.example.pc_controller;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.net.Socket;

public class basic_controls_frag_class extends Fragment
{
    //user_input_sender basic_controls;
    Socket client;

    public basic_controls_frag_class(Socket client_in)
    {
        client = client_in;
        Log.d("TAG", "frag constructed");
    }

    private void set_basic_key_button_listener(Button key_button, final String key_tag)
    {
        key_button.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    new user_input_sender(client).execute("release", key_tag);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    new user_input_sender(client).execute("press", key_tag);
                }

                return false;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View basic_control_frag_view = inflater.inflate(R.layout.basic_controls_frag, container, false);
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.w_button_id), "W");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.a_button_id), "A");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.s_button_id), "S");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.d_button_id), "D");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.r_button_id), "R");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.q_button_id), "Q");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.e_button_id), "E");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.ctrl_button_id), "Ctrl");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.c_button_id), "C");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.left_click_button_id), "left_click");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.right_click_button_id), "right_click");

        FrameLayout swipe_area = basic_control_frag_view.findViewById(R.id.swipe_area_id);
        GestureDetector myDetector = new GestureDetector(new gesture_detector_class());
        View.OnTouchListener my_touchListener = new swipe_listener_class(client, myDetector);
        swipe_area.setOnTouchListener(my_touchListener);


        return basic_control_frag_view;
    }

    @Override
    public void onAttach(Context context) { super.onAttach(context); }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
