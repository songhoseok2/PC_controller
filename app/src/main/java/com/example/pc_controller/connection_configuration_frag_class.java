package com.example.pc_controller;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class connection_configuration_frag_class extends Fragment
{
    Handler mainThreadHandler;
    Handler connection_handler;
    connection_establishment_class connection;
    Menu main_menu;
    Context main_activity_context;
    LinearLayout connection_progress_label_frame;
    LinearLayout connection_progress_image_frame;
    TextView connection_progress_label;
    EditText ip_address_field;
    EditText port_number_field;
    Button cancel_button;
    ProgressBar connection_progress_bar;

    public connection_configuration_frag_class(Context main_activity_context_in, Menu main_menu_in, Handler mainThreadHandler_in)
    {
        main_activity_context = main_activity_context_in;
        main_menu = main_menu_in;
        mainThreadHandler = mainThreadHandler_in;
    }

    public connection_establishment_class get_connection()
    {
        return connection;
    }

    public void connection_button_pressed(View view)
    {
        boolean is_input_missing = false;

        if(String.valueOf(ip_address_field.getText()).equals(""))
        {
            ip_address_field.setError("IP address of the PC is required.");
            is_input_missing = true;
        }
        if(String.valueOf(port_number_field.getText()).equals(""))
        {
            port_number_field.setError("Port Number for the connection is required.");
            is_input_missing = true;
        }
        if(is_input_missing) { return; }
        cancel_button.setVisibility(View.VISIBLE);

        //reset all views in the frames or else the views will multiply each time button is clicked
        connection_progress_label_frame.removeAllViews();
        connection_progress_image_frame.removeAllViews();

        final Button connect_button = view.findViewById(R.id.connect_button_id);
        connect_button.setEnabled(false);

        connection_progress_label = new TextView(view.getContext());
        connection_progress_label.setText(getResources().getString(R.string.connecting_label_str));
        connection_progress_label_frame.addView(connection_progress_label);
        connection_progress_bar = new ProgressBar(view.getContext());
        connection_progress_image_frame.addView(connection_progress_bar);

        connection = new connection_establishment_class(connection_progress_image_frame,
                connection_progress_label,
                main_menu,
                connect_button,
                cancel_button,
                connection_progress_bar,
                main_activity_context,
                mainThreadHandler,
                connection_handler);

        connection.execute(ip_address_field.getText().toString(), port_number_field.getText().toString());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View connection_configuration_frag_view = inflater.inflate(R.layout.conection_configuration_frag, container, false);
        this.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        connection_progress_label_frame = connection_configuration_frag_view.findViewById(R.id.connection_progress_label_layout_id);
        connection_progress_image_frame = connection_configuration_frag_view.findViewById(R.id.connection_progress_image_layout_id);
        ip_address_field = connection_configuration_frag_view.findViewById(R.id.ip_address_field_id);
        port_number_field = connection_configuration_frag_view.findViewById(R.id.port_number_field_id);
        final Button connect_button = connection_configuration_frag_view.findViewById(R.id.connect_button_id);
        cancel_button = connection_configuration_frag_view.findViewById(R.id.cancel_button_id);
        cancel_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                connection.cancel(true);
                connect_button.setEnabled(true);
                cancel_button.setVisibility(View.INVISIBLE);
            }
        });
        cancel_button.setVisibility(View.INVISIBLE);

        if(connection_establishment_class.is_connected)
        {
            connect_button.setText("Connected to a PC");
            connect_button.setEnabled(false);
        }
        else
        {
            connect_button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) { connection_button_pressed(view); }
            });
        }


        connection_handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                if(msg.what == 1)
                {
                    cancel_button.setVisibility(View.INVISIBLE);
                }
            }
        };

        return connection_configuration_frag_view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }
}
