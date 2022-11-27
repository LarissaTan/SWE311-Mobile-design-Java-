package com.example.funnylearning.navigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.funnylearning.R;
import com.example.funnylearning.Temp_head;
import com.example.funnylearning.register.BaseActivity;
import com.example.funnylearning.register.Reading_level_2_Fragment;

import java.util.ArrayList;

public class ReadingFragment extends Fragment {

    public ReadingFragment() {
        // Required empty public constructor
    }

    public static final String EXTRA_NAME = "tag";
    public String tag = "0";

    public static final String EXTRA_NAMEr = "tagr";
    public String tagr = "0";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FrameLayout video = view.findViewById(R.id.reading_module1);
        FrameLayout findW = view.findViewById(R.id.find_the_words);
        FrameLayout deliver = view.findViewById(R.id.deliver_good);
        video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tag = "2";
                Intent it = new Intent(getContext(), Temp_head.class);
                it.putExtra(EXTRA_NAME,tag);
                startActivity(it);
            }
        });

        findW.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tagr = "2";
                System.out.println("tagr = "+tagr);
                Intent it = new Intent(getContext(), BaseActivity.class);
                it.putExtra(EXTRA_NAMEr,tagr);
                startActivity(it);
            }
        });

        deliver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tagr = "1";
                System.out.println("tagr = "+tagr);
                Intent it = new Intent(getContext(), BaseActivity.class);
                it.putExtra(EXTRA_NAMEr,tagr);
                startActivity(it);
            }
        });
    }

    public static ReadingFragment newInstance(String param1, String param2) {
        ReadingFragment fragment = new ReadingFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reading, container, false);

        return view;
    }
}