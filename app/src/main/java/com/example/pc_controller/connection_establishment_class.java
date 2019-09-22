package com.example.pc_controller;

import android.content.Context;
import android.net.InetAddresses;
import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;



public class connection_establishment_class extends AsyncTask<String, Void, String>
{
    public static final int NUMOFOPTIONS = 3;
    boolean is_connected;
    private LinearLayout connection_progress_label_frame;
    private LinearLayout connection_progress_image_frame;
    private TextView connection_progress_label;
    private Menu main_menu;
    private Button connect_button;
    private Context main_activity_context;
    private Handler mainThreadHandler;
    public Socket client;

    public connection_establishment_class(LinearLayout connection_progress_label_frame_in,
                                          LinearLayout connection_progress_image_frame_in,
                                          TextView connection_progress_label_in,
                                          Menu main_menu_in,
                                          Button connect_button_in,
                                          Context main_activity_context_in,
                                          Handler mainThreadHandler_in)
    {
        connection_progress_label_frame = connection_progress_label_frame_in;
        connection_progress_image_frame = connection_progress_image_frame_in;
        connection_progress_label = connection_progress_label_in;
        main_menu = main_menu_in;
        connect_button = connect_button_in;
        main_activity_context = main_activity_context_in;
        mainThreadHandler = mainThreadHandler_in;
    }

    @Override
    protected String doInBackground(String... strings)
    {
        String ip_address = strings[0];
        int port_number = Integer.parseInt(strings[1]);
        try
        {
           client = new Socket(ip_address, port_number);

            Message message = new Message();
            message.what = 1;
            message.arg1 = 1;
            mainThreadHandler.sendMessage(message);
            is_connected = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            is_connected = false;
        }
        return is_connected ? "Connected" : "Not Connected";
    }

    @Override
    protected void onPostExecute(String result)
    {
        connection_progress_image_frame.removeAllViews();
        if(is_connected)
        {
            connection_progress_label.setText(R.string.connected_label_str);
            ImageView error_image_view = new ImageView(main_activity_context);
            error_image_view.setImageResource(R.drawable.check_png);
            connection_progress_image_frame.addView(error_image_view, 75, 75);

            for(int i = 1 ; i < NUMOFOPTIONS; ++i)
            {
                MenuItem current_option = main_menu.getItem(i);
                current_option.setEnabled(true);
            }
        }
        else
        {
            connection_progress_label.setText(R.string.failed_connection_label_str);

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)connection_progress_image_frame.getLayoutParams();
            params.setMargins(0, 100, 0, 0);
            connection_progress_image_frame.setLayoutParams(params);

            ImageView error_image_view = new ImageView(main_activity_context);
            error_image_view.setImageResource(R.drawable.error_png);
            connection_progress_image_frame.addView(error_image_view, 75, 75);
        }

        connect_button.setEnabled(true);
    }

    public Socket get_client_socket()
    {
        return client;
    }
}
