package com.example.pc_controller;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.OutputStream;
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

    private void set_onclick_listener_for_key_button(Button key_button, final String key_tag)
    {
        key_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) { new user_input_sender(client).execute(key_tag); }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View basic_control_frag_view = inflater.inflate(R.layout.basic_controls_frag, container, false);
        set_onclick_listener_for_key_button((Button)basic_control_frag_view.findViewById(R.id.alt_tab_button_id), "alt_tab");
        return basic_control_frag_view;
    }

    @Override
    public void onAttach(Context context) { super.onAttach(context); }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
