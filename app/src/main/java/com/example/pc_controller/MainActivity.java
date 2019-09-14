package com.example.pc_controller;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.*;
import java.io.*;



public class MainActivity extends AppCompatActivity
{
    private static final int NUMOFOPTIONS = 2;
    private connection_configuration_frag_class connection_configuration_frag;
    private basic_controls_frag_class basic_controls_frag;
    EditText ip_address_field;
    EditText port_number_field;
    private Menu main_menu;
    boolean is_connected;
    Handler mainThreadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connection_configuration_frag = new connection_configuration_frag_class();
        basic_controls_frag = new basic_controls_frag_class();

        mainThreadHandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                if(msg.what == 1)
                {
                    Toast.makeText(getApplicationContext(),
                            "Connection to " + ip_address_field.getText() + ":" + port_number_field.getText() + " successful. You now have access to the controls of the PC.",
                            Toast.LENGTH_LONG).show();
                }
            }
        };

        Toast.makeText(this,
                "You need to first establish a connection to a PC in order to access the control.",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        for(int i = 1 ; i < NUMOFOPTIONS; ++i)
        {
            MenuItem current_option = menu.getItem(i);
            current_option.setEnabled(false);
        }

        MenuItem initiation = menu.findItem(R.id.connection_configuration_menu_item_id);
        onOptionsItemSelected(initiation);

        main_menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        //trans.add(R.id.main_activity_layout_id, connection_configuration_frag).commit();
        switch(item.getItemId())
        {
            case R.id.connection_configuration_menu_item_id:
                item.setChecked(true);
                trans.replace(R.id.main_activity_layout_id, connection_configuration_frag)
                        .commit();
                return true;

            case R.id.basic_screen_menu_item_id:
                item.setChecked(true);
                trans.replace(R.id.main_activity_layout_id, basic_controls_frag)
                        .commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void establish_connection(LinearLayout connection_progress_label_frame_in,
             LinearLayout connection_progress_image_frame_in,
             TextView connection_progress_label_in,
             Button connect_button_in,
             Handler mainThreadHandler_in)
    {
        connection_establishment_class connection = new connection_establishment_class(connection_progress_label_frame_in,
        connection_progress_image_frame_in,
        connection_progress_label_in,
        main_menu,
        connect_button_in,
        this,
                mainThreadHandler_in);

        connection.execute(ip_address_field.getText().toString(), port_number_field.getText().toString());
    }

    public void connection_button_pressed(View view)
    {
        LinearLayout connection_progress_label_frame = findViewById(R.id.connection_progress_label_layout_id);
        LinearLayout connection_progress_image_frame = findViewById(R.id.connection_progress_image_layout_id);
        ip_address_field = findViewById(R.id.ip_address_field_id);
        port_number_field = findViewById(R.id.port_number_field_id);

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

        //reset all views in the frames or else the views will multiply each time button is clicked
        connection_progress_label_frame.removeAllViews();
        connection_progress_image_frame.removeAllViews();

        Button connect_button = findViewById(R.id.connect_button_id);
        connect_button.setEnabled(false);

        TextView connection_progress_label = new TextView(this);
        connection_progress_label.setText(getResources().getString(R.string.connecting_label_str));
        connection_progress_label_frame.addView(connection_progress_label);
        ProgressBar connection_progress_bar = new ProgressBar( this);
        connection_progress_image_frame.addView(connection_progress_bar);

        establish_connection(connection_progress_label_frame,
                connection_progress_image_frame,
                connection_progress_label,
                connect_button,
                mainThreadHandler);
    }
}
