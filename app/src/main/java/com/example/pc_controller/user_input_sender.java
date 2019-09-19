package com.example.pc_controller;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
//import java.lang.instrument.Instrumentation;

public class user_input_sender extends AsyncTask<String, Void, String> {
    Socket client;

    public user_input_sender(Socket client_in)
    {
        client = client_in;
    }

    @Override
    protected String doInBackground(String... strings)
    {
        try
        {
            OutputStream output = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(output));

            byte[] msg;
            if(strings[0].equals("s"))
            {
                String x_movement = strings[1];
                String y_movement = strings[2];
                out.write(("+" + x_movement + "_" + y_movement).getBytes());
                out.flush();
//                out.write(strings[1].getBytes());
//                out.flush();
//                msg = strings[1].getBytes();
            }
            else
            {
                msg = (strings[0] + "_" + strings[1]).getBytes();
                out.write(msg);
                out.flush();
            }


            Log.d("TAG", "s sent");
            return "success";
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "fail";
        }
    }
}
