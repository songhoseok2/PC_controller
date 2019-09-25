package com.example.pc_controller;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.net.Socket;

public class fps_game_control_frag_class extends Fragment
{
    Socket client;

    public fps_game_control_frag_class(Socket client_in)
    {
        client = client_in;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View fps_game_control_frag_view = inflater.inflate(R.layout.fps_game_control_frag, container, false);
        this.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        FrameLayout touch_pad = fps_game_control_frag_view.findViewById(R.id.fps_control_touch_pad_id);
        GestureDetector myDetector = new GestureDetector(new gesture_detector_class());
        View.OnTouchListener my_touchListener = new swipe_listener_class(client, myDetector, (TextView)fps_game_control_frag_view.findViewById(R.id.fps_control_touch_pad_label_id));
        touch_pad.setOnTouchListener(my_touchListener);

        basic_key_listener_interface key_listener_setter = new basic_key_listener_interface(client);
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_left_click_id), "256");
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_right_click_id), "257");
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_middle_mouse_id), "258");

        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_1_id), "002");
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_2_id), "003");
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_3_id), "004");
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_4_id), "005");

        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_tab_id), "015");
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_q_id), "016");
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_w_id), "017");
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_e_id), "018");
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_r_id), "019");

        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_a_id), "030");
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_s_id), "031");
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_d_id), "032");
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_f_id), "033");

        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_shift_id), "042");
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_z_id), "044");
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_x_id), "045");
        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_c_id), "046");

        key_listener_setter.set_basic_key_button_listener((Button)fps_game_control_frag_view.findViewById(R.id.fps_control_ctrl_id), "029");

        return fps_game_control_frag_view;
    }

    @Override
    public void onAttach(Context context) { super.onAttach(context); }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}