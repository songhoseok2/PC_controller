package com.example.pc_controller;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.net.*;
import java.io.*;



public class MainActivity extends AppCompatActivity
{
    private static final int NUMOFOPTIONS = 2;
    private connection_configuration_frag_class connection_configuration_frag;
    private basic_controls_frag_class basic_controls_frag;
    private Menu main_menu;
    boolean is_connected;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connection_configuration_frag = new connection_configuration_frag_class();
        basic_controls_frag = new basic_controls_frag_class();
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
             Button connect_button_in)
    {
        connection_establishment_class connection = new connection_establishment_class(connection_progress_label_frame_in,
        connection_progress_image_frame_in,
        connection_progress_label_in,
        main_menu,
        connect_button_in,
        this);

        connection.execute("");
    }

    public void connection_button_pressed(View view)
    {
        LinearLayout connection_progress_label_frame = findViewById(R.id.connection_progress_label_layout_id);
        LinearLayout connection_progress_image_frame = findViewById(R.id.connection_progress_image_layout_id);

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
                connect_button);

    }
}
