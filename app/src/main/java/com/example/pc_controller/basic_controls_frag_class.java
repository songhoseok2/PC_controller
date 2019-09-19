package com.example.pc_controller;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
    public basic_controls_frag_class() { }

    public void set_basic_key_button_listener(Button key_button, final String key_tag)
    {
        key_button.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    new user_input_sender(client).execute("p", key_tag);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    new user_input_sender(client).execute("r", key_tag);
                }


                return false;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View basic_control_frag_view = inflater.inflate(R.layout.basic_controls_frag, container, false);
        this.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_esc_id), "001");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f1_id), "059");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f2_id), "060");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f3_id), "061");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f4_id), "062");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f5_id), "063");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f6_id), "064");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f7_id), "065");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f8_id), "066");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f9_id), "067");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f10_id), "068");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f11_id), "087");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f12_id), "088");

        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_tilde_id), "041");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_1_id), "002");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_2_id), "003");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_3_id), "004");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_4_id), "005");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_5_id), "006");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_6_id), "007");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_7_id), "008");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_8_id), "009");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_9_id), "010");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_0_id), "011");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_minus_sign_id), "012");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_equal_sign_id), "013");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_back_space_id), "014");

        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_tab_id), "015");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_q_id), "016");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_w_id), "017");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_e_id), "018");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_r_id), "019");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_t_id), "020");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_y_id), "021");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_u_id), "022");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_i_id), "023");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_o_id), "024");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_p_id), "025");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_left_bracket_id), "026");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_right_bracket_id), "027");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_backslash_id), "043");

        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_caps_lock_id), "058");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_a_id), "030");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_s_id), "031");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_d_id), "032");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f_id), "033");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_g_id), "034");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_h_id), "035");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_j_id), "036");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_k_id), "037");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_l_id), "038");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_semi_colon_id), "039");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_apostrophe_id), "040");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_enter_id), "028");

        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_shift_id), "042");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_z_id), "044");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_x_id), "045");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_c_id), "046");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_v_id), "047");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_b_id), "048");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_n_id), "049");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_m_id), "050");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_comma_id), "051");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_dot_id), "052");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_slash_id), "053");

        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_ctrl_id), "029");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_windows_id), "win");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_left_alt_id), "056");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_space_bar_id), "057");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_right_alt_id), "184");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_up_id), "200");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_down_id), "208");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_left_id), "203");
        set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_right_id), "205");

//        FrameLayout swipe_area = basic_control_frag_view.findViewById(R.id.swipe_area_id);
//        GestureDetector myDetector = new GestureDetector(new gesture_detector_class());
//        View.OnTouchListener my_touchListener = new swipe_listener_class(client, myDetector);
//        swipe_area.setOnTouchListener(my_touchListener);

        return basic_control_frag_view;
    }

    @Override
    public void onAttach(Context context) { super.onAttach(context); }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
