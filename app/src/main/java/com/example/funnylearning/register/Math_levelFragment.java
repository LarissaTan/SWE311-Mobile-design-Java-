package com.example.funnylearning.register;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.funnylearning.R;
import com.example.funnylearning.recycle.exercise.adapter_exercise;
import com.example.funnylearning.recycle.math_level.adapter_math_level;
import com.example.funnylearning.recycle.math_level.model_math_level;
//import com.example.funnylearning.recycle.math_level.adapter_math_level;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Math_levelFragment extends Fragment {

    private RecyclerView mList;
    private final ArrayList<model_math_level> mathLevelList = new ArrayList<model_math_level>();

    TextView scoreTextView;
    TextView roundsLeftTextView;
    TextView alertTextView;
    Button btn1;
    Button btn2;
    Button btn3;

    Random random =new Random();
    int display;
    int indexOfCorrectAnswer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int points = 0;
    int totalQuestions = 0;

    public Math_levelFragment() {
        // Required empty public constructor
    }

    public static Math_levelFragment newInstance(String param1, String param2) {
        Math_levelFragment fragment = new Math_levelFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_math_level, container, false);

        scoreTextView = view.findViewById(R.id.scoreTextView);
        roundsLeftTextView = view.findViewById(R.id.roundsLeftTextView);
        alertTextView = view.findViewById(R.id.alertTextView);
        btn1 = view.findViewById(R.id.math_level_btn1);
        btn2 = view.findViewById(R.id.math_level_btn2);
        btn3 = view.findViewById(R.id.math_level_btn3);

        mList = (RecyclerView) view.findViewById(R.id.recyclerview_math_level);

        mathLevelList.clear();
        System.out.println("message is working");

        display = random.nextInt(3);

        NextQuestion();

        System.out.println("display" + display+1);
        for (int i = 0; i < 3; i ++) {
            System.out.println("loop");
            mathLevelList.add(new model_math_level("1"));
        }
        System.out.println(display);

        //mList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter_math_level customAdapter = new adapter_math_level(mathLevelList);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);

        if(display+1 % 2 != 0) {
            adjustSpan(gridLayoutManager);
        }

        mList.setLayoutManager(gridLayoutManager);
        mList.setAdapter(customAdapter);
        return view;
    }

    @SuppressLint("SetTextI18n")
    public void NextQuestion() {

        indexOfCorrectAnswer = random.nextInt(2);

        answers.clear();

        for (int i = 0; i < 3; i++) {

            if (i == indexOfCorrectAnswer) {
                System.out.println("button correct " + display +1);
                answers.add(display+1);
            }
            else {
                int wrongAnswer = random.nextInt(4);
                while(wrongAnswer==display+1)
                {
                    wrongAnswer = random.nextInt(4);
                }
                System.out.println("Button Wrong");
                answers.add(wrongAnswer);
            }
        }

            btn1.setText(Integer.toString(answers.get(0)));
            btn2.setText(Integer.toString(answers.get(1)));
            btn3.setText(Integer.toString(answers.get(2)));
    }

    private void adjustSpan (GridLayoutManager gridLayoutManager)
    {
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //define span size for this position
                //for example, if you have 2 column per row, you can implement something like that:
                if (position == display) {
                    return 2; //item will take 2 column (full row size)
                } else {
                    return 1; //you will have 2 rolumn per row
                }
            }
        });
    }
    public void optionSelect(View view){
        totalQuestions++;
        if(Integer.toString(indexOfCorrectAnswer).equals(view.getTag().toString())){
            points++;
            alertTextView.setText("Correct");

        }else {
            alertTextView.setText("Wrong");
        }

        scoreTextView.setText(Integer.toString(points)+"/"+Integer.toString(totalQuestions * 5));

        NextQuestion();
    }


}