package com.example.pc_controller;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
{

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

        MenuItem item = menu.findItem(R.id.connection_configuration_menu_item_id);


        onOptionsItemSelected(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        CoordinatorLayout main_view = findViewById(R.id.main_activity_layout_id);
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        //trans.add(R.id.main_activity_layout_id, connection_configuration_frag).commit();
        switch(item.getItemId())
        {
            case R.id.connection_configuration_menu_item_id:
                item.setChecked(true);
                trans
                        .replace(R.id.main_activity_layout_id, connection_configuration_frag)
                        .commit();
                return true;

            case R.id.basic_screen_menu_item_id:
                item.setChecked(true);
                trans
                        .replace(R.id.main_activity_layout_id, basic_controls_frag)
                        .commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
