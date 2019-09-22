package com.example.pc_controller;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private connection_configuration_frag_class connection_configuration_frag;
    private basic_controls_frag_class basic_controls_frag;
    private mouse_control_frag_class mouse_control_frag;
    private Menu main_menu;
    Handler mainThreadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainThreadHandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                if(msg.what == 1)
                {
                    Toast.makeText(getApplicationContext(),
                            "Connection to " + connection_configuration_frag.ip_address_field.getText() + " : " + connection_configuration_frag.port_number_field.getText() + " successful. You now have access to the controls of the PC.",
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
        for(int i = 1 ; i < connection_establishment_class.NUMOFOPTIONS; ++i)
        {
            MenuItem current_option = menu.getItem(i);
            current_option.setEnabled(false);
        }

        MenuItem begin_connection = menu.findItem(R.id.connection_configuration_menu_item_id);
        main_menu = menu;
        connection_configuration_frag = new connection_configuration_frag_class(this.getApplicationContext(), main_menu, mainThreadHandler);
        onOptionsItemSelected(begin_connection);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        switch(item.getItemId())
        {
            case R.id.connection_configuration_menu_item_id:
                item.setChecked(true);
                trans.replace(R.id.main_activity_layout_id, connection_configuration_frag)
                        .commit();
                return true;

            case R.id.basic_screen_menu_item_id:
                basic_controls_frag = new basic_controls_frag_class(connection_configuration_frag.get_connection().get_client_socket());
                item.setChecked(true);
                trans.replace(R.id.main_activity_layout_id, basic_controls_frag)
                        .commit();
                return true;

            case R.id.mouse_control_screen_menu_item_id:
                mouse_control_frag = new mouse_control_frag_class(connection_configuration_frag.get_connection().get_client_socket());
                item.setChecked(true);
                trans.replace(R.id.main_activity_layout_id, mouse_control_frag)
                        .commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
