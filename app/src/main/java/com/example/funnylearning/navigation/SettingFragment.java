package com.example.funnylearning.navigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.funnylearning.R;
import com.example.funnylearning.Setting;


public class SettingFragment extends Fragment {

    public SettingFragment() {
        // Required empty public constructor
    }


    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        int profilePicture = getArguments().getInt("profilePicture");
        String name = getArguments().getString("name");
        Integer age = getArguments().getInt("age");
        Boolean gender = getArguments().getBoolean("gender");
        String email = getArguments().getString("email");
        String location = getArguments().getString("location");

        ImageView setting;
        setting  = view.findViewById(R.id.setting_jump);

        ImageView fragment_profilePicture;
        TextView fragment_name, fragment_age, fragment_gender, fragment_email, fragment_location;

        fragment_profilePicture = view.findViewById(R.id.profile_picture);
        fragment_name = view.findViewById(R.id.profile_name);
        fragment_age = view.findViewById(R.id.profile_age);
        fragment_gender = view.findViewById(R.id.profile_gender);
        fragment_email = view.findViewById(R.id.profile_email);
        fragment_location = view.findViewById(R.id.profile_location);

        fragment_profilePicture.setImageResource(profilePicture);
        fragment_name.setText(name);
        fragment_age.setText(age.toString());
        if(gender){
            fragment_gender.setText("Male");
        }else {
            fragment_gender.setText("Female");
        }
        fragment_email.setText(email);
        if(location == null){
            fragment_location.setText("unknown");
        }else {
            fragment_location.setText(location);
        }

        setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(getContext(), Setting.class);
                startActivity(it);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}