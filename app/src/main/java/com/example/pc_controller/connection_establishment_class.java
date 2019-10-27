package com.example.pc_controller;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;



public class connection_establishment_class extends AsyncTask<String, Void, String>
{
    static boolean is_connected = false;
    boolean connection_failed = false;
    private LinearLayout connection_progress_image_frame;
    private TextView connection_progress_label;
    private Menu main_menu;
    private Button connect_button;
    private Button cancel_button;
    private Context main_activity_context;
    private Handler mainThreadHandler;
    private Handler connection_handler;
    public Socket client;
    ProgressBar connection_progress_bar;
    Thread cancel_listener_thread;

    public connection_establishment_class(LinearLayout connection_progress_image_frame_in,
                                          TextView connection_progress_label_in,
                                          Menu main_menu_in,
                                          Button connect_button_in,
                                          Button cancel_button_in,
                                          ProgressBar connection_progress_bar_in,
                                          Context main_activity_context_in,
                                          Handler mainThreadHandler_in,
                                          Handler connection_handler_in)
    {
        connection_progress_image_frame = connection_progress_image_frame_in;
        connection_progress_label = connection_progress_label_in;
        main_menu = main_menu_in;
        connect_button = connect_button_in;
        cancel_button = cancel_button_in;
        connection_progress_bar = connection_progress_bar_in;
        main_activity_context = main_activity_context_in;
        mainThreadHandler = mainThreadHandler_in;
        connection_handler = connection_handler_in;
    }

    @Override
    protected String doInBackground(String... strings)
    {
        is_connected = false;
        final String ip_address = strings[0];
        final int port_number = Integer.parseInt(strings[1]);

        //connection to a socket must be a new thread. this is because socket initialization
        //is a blocking process and while socket is not connected,
        //it will prevent doInBackground from progressing further, so there's no point in calling
        //cancel in the connect_button_pressed function.
        //therefore 2 threads are required: one for connecting the socket and the other for
        //listening for a cancel call from the connect_button_pressed function and terminating the
        //first thread when cancel call is received.
        final Thread connection_thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    client = new Socket(ip_address, port_number);

                    //socket is alive even when thread is terminated. therefore
                    //this 'dead' socket needs to notify the server that this socket isn't valid if
                    //the cancel button was pressed
                    OutputStream output = client.getOutputStream();
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(output));
                    if(isCancelled())
                    {
                        out.write("invalid".getBytes());
                        out.flush();
                        client.close();
                        is_connected = false;
                        return;
                    }
                    out.write("valid".getBytes());
                    out.flush();
                    Message message = new Message();
                    message.what = 1;
                    mainThreadHandler.sendMessage(message);
                    is_connected = true;
                    Message message2 = new Message();
                    message.what = 1;
                    connection_handler.sendMessage(message2);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    connection_failed = true;
                }
            }
        };
        connection_thread.start();

        //this thread is still required even when cancelling is handled in the above thread,
        //because otherwise there is no way to remove the progress bar and the text as soon as the
        //cancel button is pressed.
        cancel_listener_thread = new Thread()
        {
            @Override
            public void run()
            {
                while(!isCancelled() && !is_connected && !connection_failed) {}
                if(!is_connected)
                {
                    connection_thread.interrupt();
                }
            }
        };
        cancel_listener_thread.start();
        try
        {
            cancel_listener_thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return is_connected ? "Connected" : "Not Connected";
    }

    @Override
    protected void onPostExecute(String result)
    {
        connection_progress_image_frame.removeAllViews();
        if (is_connected)
        {
            connection_progress_label.setText(R.string.connected_label_str);
            connect_button.setText("Connected to a PC");
            ImageView error_image_view = new ImageView(main_activity_context);
            error_image_view.setImageResource(R.drawable.check_png);
            connection_progress_image_frame.addView(error_image_view, 75, 75);

            for (int i = 1; i < MainActivity.NUMOFOPTIONS; ++i) {
                MenuItem current_option = main_menu.getItem(i);
                current_option.setEnabled(true);
            }
        }
        else
        {
            connection_progress_label.setText(R.string.failed_connection_label_str);

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) connection_progress_image_frame.getLayoutParams();
            params.setMargins(0, 100, 0, 0);
            connection_progress_image_frame.setLayoutParams(params);

            ImageView error_image_view = new ImageView(main_activity_context);
            error_image_view.setImageResource(R.drawable.error_png);
            connection_progress_image_frame.addView(error_image_view, 75, 75);
        }

        connect_button.setEnabled(!is_connected);
        cancel_button.setVisibility(View.INVISIBLE);
    }

    public Socket get_client_socket()
    {
        return client;
    }

    @Override
    protected void onCancelled()
    {
        connection_progress_label.setText("");
        connection_progress_bar.setVisibility(View.GONE);
        super.onCancelled();
    }


}

