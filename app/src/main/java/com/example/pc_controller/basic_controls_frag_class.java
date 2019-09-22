package com.example.pc_controller;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.net.Socket;

public class basic_controls_frag_class extends Fragment
{
    Socket client;

    public basic_controls_frag_class(Socket client_in)
    {
        client = client_in;
        Log.d("TAG", "frag constructed");
    }
    public basic_controls_frag_class() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View basic_control_frag_view = inflater.inflate(R.layout.basic_controls_frag, container, false);
        this.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        basic_key_listener_interface key_listener_setter = new basic_key_listener_interface(client);

        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_esc_id), "001");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f1_id), "059");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f2_id), "060");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f3_id), "061");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f4_id), "062");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f5_id), "063");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f6_id), "064");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f7_id), "065");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f8_id), "066");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f9_id), "067");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f10_id), "068");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f11_id), "087");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f12_id), "088");

        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_tilde_id), "041");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_1_id), "002");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_2_id), "003");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_3_id), "004");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_4_id), "005");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_5_id), "006");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_6_id), "007");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_7_id), "008");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_8_id), "009");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_9_id), "010");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_0_id), "011");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_minus_sign_id), "012");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_equal_sign_id), "013");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_back_space_id), "014");

        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_tab_id), "015");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_q_id), "016");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_w_id), "017");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_e_id), "018");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_r_id), "019");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_t_id), "020");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_y_id), "021");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_u_id), "022");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_i_id), "023");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_o_id), "024");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_p_id), "025");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_left_bracket_id), "026");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_right_bracket_id), "027");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_backslash_id), "043");

        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_caps_lock_id), "058");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_a_id), "030");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_s_id), "031");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_d_id), "032");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_f_id), "033");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_g_id), "034");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_h_id), "035");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_j_id), "036");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_k_id), "037");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_l_id), "038");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_semi_colon_id), "039");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_apostrophe_id), "040");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_enter_id), "028");

        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_shift_id), "042");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_z_id), "044");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_x_id), "045");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_c_id), "046");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_v_id), "047");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_b_id), "048");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_n_id), "049");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_m_id), "050");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_comma_id), "051");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_dot_id), "052");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_slash_id), "053");

        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_ctrl_id), "029");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_windows_id), "win");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_left_alt_id), "056");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_space_bar_id), "057");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_right_alt_id), "184");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_up_id), "200");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_down_id), "208");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_left_id), "203");
        key_listener_setter.set_basic_key_button_listener((Button)basic_control_frag_view.findViewById(R.id.basic_control_right_id), "205");

        return basic_control_frag_view;
    }

    @Override
    public void onAttach(Context context) { super.onAttach(context); }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
