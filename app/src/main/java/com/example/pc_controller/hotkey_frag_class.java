package com.example.pc_controller;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.net.Socket;

public class hotkey_frag_class extends Fragment
{
    Socket client;
    Handler mainThreadHandler;

    public hotkey_frag_class(Socket client_in, Handler mainThreadHandler_in)
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
        final View  hotkey_frag_view = inflater.inflate(R.layout.hotkey_frag, container, false);
        this.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        basic_key_listener_interface key_listener_setter = new basic_key_listener_interface(client, mainThreadHandler);

        key_listener_setter.set_basic_key_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_mute_id), "mut");
        key_listener_setter.set_basic_key_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_decrease_volume_id), "vdo");
        key_listener_setter.set_basic_key_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_increase_volume_id), "vup");
        key_listener_setter.set_hotkey_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_alt_tab_id), "a_t");
        key_listener_setter.set_hotkey_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_task_manager_id), "tsk");
        key_listener_setter.set_hotkey_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_decrease_brightness_id), "bdo");
        key_listener_setter.set_hotkey_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_increase_brightness_id), "bup");
        key_listener_setter.set_basic_key_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_caps_lock_id), "058");
        key_listener_setter.set_basic_key_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_nums_lock_id), "069");
        key_listener_setter.set_basic_key_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_scroll_lock_id), "070");
        key_listener_setter.set_basic_key_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_up_id), "200");
        key_listener_setter.set_basic_key_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_down_id), "208");
        key_listener_setter.set_basic_key_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_left_id), "203");
        key_listener_setter.set_basic_key_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_right_id), "205");
        key_listener_setter.set_hotkey_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_ctrl_a_id), "c_a");
        key_listener_setter.set_hotkey_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_ctrl_x_id), "c_x");
        key_listener_setter.set_hotkey_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_ctrl_c_id), "c_c");
        key_listener_setter.set_hotkey_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_ctrl_v_id), "c_v");
        key_listener_setter.set_hotkey_button_listener((Button)hotkey_frag_view.findViewById(R.id.hotkey_alt_f4_id), "af4");

        Button shut_down_button = hotkey_frag_view.findViewById(R.id.hotkey_shut_down_id);
        shut_down_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        switch (which)
                        {
                            case DialogInterface.BUTTON_POSITIVE:
                                new user_input_sender(client, mainThreadHandler).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, "p", "off");
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                return;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(hotkey_frag_view.getContext());
                builder.setMessage("Are you sure you wish to shut down the computer?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });


        return hotkey_frag_view;
    }

    @Override
    public void onAttach(Context context) { super.onAttach(context); }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
