package com.example.pc_controller;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.net.Socket;

public class mouse_control_frag_class extends Fragment
{
    Socket client;
    Handler mainThreadHandler;
    SeekBar sensitivity_bar;

    public mouse_control_frag_class(Socket client_in, Handler mainThreadHandler_in)
    {
        client = client_in;
        mainThreadHandler = mainThreadHandler_in;
    }

    public void reconnect_client_if_needed(Socket new_client)
    {
        if(client != null && client.isClosed())
        {
            client = new_client;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View mouse_control_frag_view = inflater.inflate(R.layout.mouse_control_frag, container, false);
        this.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        FrameLayout touch_pad = mouse_control_frag_view.findViewById(R.id.fps_control_touch_pad_id);
        GestureDetector myDetector = new GestureDetector(new gesture_detector_class());
        sensitivity_bar = mouse_control_frag_view.findViewById(R.id.mouse_control_sensitivity_bar_id);
        if(savedInstanceState != null) { sensitivity_bar.setProgress(savedInstanceState.getInt("sensitivity_bar_value")); }
        TextView touch_pad_label = mouse_control_frag_view.findViewById(R.id.fps_control_touch_pad_label_id);
        View.OnTouchListener my_touchListener = new swipe_listener_class(
                client,
                myDetector,
                touch_pad_label,
                sensitivity_bar,
                mouse_control_frag_view.getContext(),
                mainThreadHandler
        );


        touch_pad.setOnTouchListener(my_touchListener);

        basic_key_listener_interface key_listener_setter = new basic_key_listener_interface(client, mainThreadHandler);
        key_listener_setter.set_basic_key_button_listener((Button)mouse_control_frag_view.findViewById(R.id.mouse_control_left_click_id), "256");
        key_listener_setter.set_basic_key_button_listener((Button)mouse_control_frag_view.findViewById(R.id.mouse_control_right_click_id), "257");
        key_listener_setter.set_hotkey_button_listener((Button)mouse_control_frag_view.findViewById(R.id.mouse_control_middle_click_id), "258");

        return mouse_control_frag_view;
    }

    @Override
    public void onAttach(Context context) { super.onAttach(context); }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        outState.putInt("sensitivity_bar_value", sensitivity_bar.getProgress());
        super.onSaveInstanceState(outState);
    }
}
