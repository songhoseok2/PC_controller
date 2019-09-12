package com.example.pc_controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity
{
    private static final int NUMOFOPTIONS = 2;
    private connection_configuration_frag_class connection_configuration_frag;
    private basic_controls_frag_class basic_controls_frag;
    private boolean is_connected = false;

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

    private boolean establish_connection()
    {

        return false;
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

        boolean is_connected = establish_connection();
        if(is_connected)
        {

        }
        else
        {
            connection_progress_label.setText(R.string.failed_connection_label_str);
            connection_progress_image_frame.removeAllViews();

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)connection_progress_image_frame.getLayoutParams();
            params.setMargins(0, 100, 0, 0);

            ImageView error_image_view = new ImageView(this);
            error_image_view.setImageResource(R.drawable.error_image);
            connection_progress_image_frame.addView(error_image_view, 75, 75);
            connection_progress_image_frame.setLayoutParams(params);
        }

        connect_button.setEnabled(true);
    }
}
