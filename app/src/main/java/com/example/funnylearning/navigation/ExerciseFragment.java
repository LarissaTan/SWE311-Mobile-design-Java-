package com.example.funnylearning.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.funnylearning.R;
import com.example.funnylearning.recycle.chatbox.adapter_chatbox;
import com.example.funnylearning.recycle.exercise.adapter_exercise;
import com.example.funnylearning.recycle.exercise.model_exercise;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExerciseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExerciseFragment extends Fragment {

    private RecyclerView execList;
    private final ArrayList<model_exercise> exerciseList = new ArrayList<model_exercise>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExerciseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExerciseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExerciseFragment newInstance(String param1, String param2) {
        ExerciseFragment fragment = new ExerciseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        execList = (RecyclerView) view.findViewById(R.id.recyclerview_exercise);

        exerciseList.clear();
        System.out.println("message is working");
        exerciseList.add(new model_exercise("Calf Raise"));
        exerciseList.add(new model_exercise("Bent Raise"));
        exerciseList.add(new model_exercise("Bent Raise"));
        exerciseList.add(new model_exercise("Bent Raise"));
        exerciseList.add(new model_exercise("Bent Raise"));
        //image will add latter

        System.out.println("message is working");
        execList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter_exercise customAdapter = new adapter_exercise(exerciseList);
        System.out.println("layout manager is working");
        execList.setAdapter(customAdapter);

        return view;
    }
}