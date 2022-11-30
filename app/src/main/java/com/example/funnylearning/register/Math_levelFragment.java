package com.example.funnylearning.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.funnylearning.R;
import com.example.funnylearning.recycle.math_level.adapter_math_level;
import com.example.funnylearning.recycle.math_level.model_math_level;

import java.util.ArrayList;
import java.util.Random;


public class Math_levelFragment extends Fragment {

    private RecyclerView mList;
    private final ArrayList<model_math_level> mathLevelList = new ArrayList<model_math_level>();

    TextView scoreTextView;
    TextView roundsLeftTextView;
    TextView alertTextView;
    TextView finalScoreTextView;

    ProgressBar roundsLeftProgressBar;

    Button btn1;
    Button btn2;
    Button btn3;
    Button playAgainBtn;

    LinearLayout normalUI;
    ConstraintLayout finalUI;
    ConstraintLayout ansUI;

    Random random =new Random();
    int display;
    int indexOfCorrectAnswer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    ArrayList<Integer> displaysList = new ArrayList<Integer>();
    int points = 0;
    int rounds = 5;
    int totalQuestions = 5;

    Animation scaleUp,scaleDown;

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

    @SuppressLint({"NotifyDataSetChanged", "MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_math_level, container, false);

        scoreTextView = view.findViewById(R.id.scoreTextView);
        roundsLeftTextView = view.findViewById(R.id.roundsLeftTextView);
        alertTextView = view.findViewById(R.id.alertTextView);
        finalScoreTextView =view.findViewById(R.id.finalScoreTextView);

        roundsLeftProgressBar =view.findViewById(R.id.circular_progress_bar);

        btn1 = view.findViewById(R.id.math_level_btn1);
        btn2 = view.findViewById(R.id.math_level_btn2);
        btn3 = view.findViewById(R.id.math_level_btn3);
        playAgainBtn = view.findViewById(R.id.btnPlayAgain);

        normalUI = view.findViewById(R.id.normalUI);
        finalUI = view.findViewById(R.id.finalUI);
        ansUI =view.findViewById(R.id.ansUI);

        normalUI.setVisibility(View.VISIBLE);
        finalUI.setVisibility(View.INVISIBLE);
        ansUI.setVisibility(View.INVISIBLE);

        scaleUp = AnimationUtils.loadAnimation(getContext(), R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(getContext(), R.anim.scale_down);

        mList = (RecyclerView) view.findViewById(R.id.recyclerview_math_level);

        NextQuestion();

        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    btn1.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            optionSelect(view);
                        }
                    }, 500);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    btn1.startAnimation(scaleUp);
                    btn1.setTextColor(Color.WHITE);
                    btn1.setBackgroundColor(Color.parseColor("#F9D150"));

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){

                    btn1.startAnimation(scaleDown);
                    btn1.setTextColor(Color.parseColor("#F9D150"));
                    btn1.setBackgroundColor(Color.WHITE);
                }

                return true;
            }
        });

        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    btn2.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            optionSelect(view);
                        }
                    }, 500);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    btn2.startAnimation(scaleUp);
                    btn2.setTextColor(Color.WHITE);
                    btn2.setBackgroundColor(Color.parseColor("#F9D150"));

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){

                    btn2.startAnimation(scaleDown);
                    btn2.setTextColor(Color.parseColor("#F9D150"));
                    btn2.setBackgroundColor(Color.WHITE);
                }

                return true;
            }
        });

        btn3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    btn3.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            optionSelect(view);
                        }
                    }, 500);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    btn3.startAnimation(scaleUp);
                    btn3.setTextColor(Color.WHITE);
                    btn3.setBackgroundColor(Color.parseColor("#F9D150"));

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){

                    btn3.startAnimation(scaleDown);
                    btn3.setTextColor(Color.parseColor("#F9D150"));
                    btn3.setBackgroundColor(Color.WHITE);
                }

                return true;
            }
        });

        playAgainBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    playAgainBtn.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            playAgain(view);
                        }
                    }, 300);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    playAgainBtn.startAnimation(scaleUp);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){

                    playAgainBtn.startAnimation(scaleDown);

                }

                return true;
            }
        });

        return view;
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    public void NextQuestion() {

        btn1.setTextColor(Color.parseColor("#F9D150"));
        btn1.setBackgroundColor(Color.WHITE);
        btn2.setTextColor(Color.parseColor("#F9D150"));
        btn2.setBackgroundColor(Color.WHITE); btn1.setTextColor(Color.parseColor("#F9D150"));
        btn3.setBackgroundColor(Color.WHITE);
        btn3.setTextColor(Color.parseColor("#F9D150"));

        display = random.nextInt(4);

        /*displaysList.add(display);
        while(display == displaysList.get(rounds%5))
        {
            display = random.nextInt(4);
        }*/

        indexOfCorrectAnswer = random.nextInt(3);

        mathLevelList.clear();
        System.out.println("message is working");

        System.out.println("display" + display+1);
        for (int i = 0; i < display+1; i ++) {
            System.out.println("loop");
            mathLevelList.add(new model_math_level("i"));
        }
        System.out.println(display);

        //mList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter_math_level customAdapter = new adapter_math_level(mathLevelList);
        customAdapter.notifyDataSetChanged();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);

        if((display+1) % 2 != 0) {
            adjustSpan(gridLayoutManager);
        }

        mList.setLayoutManager(gridLayoutManager);
        mList.setAdapter(customAdapter);
        answers.clear();
        System.out.println("index " + indexOfCorrectAnswer + "display" + display+1);
        for (int i = 0; i < 3; i++) {

            if (i == indexOfCorrectAnswer) {
                System.out.println("button correct " + display +1);
                answers.add(display+1);
            }
            else {
                int wrongAnswer = random.nextInt(5);
                while(wrongAnswer==display+1)
                {
                    wrongAnswer = random.nextInt(5);
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

    @SuppressLint("SetTextI18n")
    public void optionSelect(View view){

        if(Integer.toString(indexOfCorrectAnswer).equals(view.getTag().toString())){
            points+=20;
            alertTextView.setText("Correct");
            alertTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,45);
        }else {
            alertTextView.setText("Correct Answer: " + (display+1));
            alertTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,35);
        }

        onAnswer();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("return answer");
                ansUI.setAlpha(0.5f);
                ansUI.setVisibility(View.INVISIBLE);
                ansUI.animate()
                        .alpha(0f)
                        .setDuration(380)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                if (rounds >= 1)
                                    normalUI.setVisibility(View.VISIBLE);
                            }
                        });
            }
        }, 1000);


        scoreTextView.setText(Integer.toString(points));
        roundsLeftTextView.setText(Integer.toString(--rounds));
        if (rounds == 4)
            roundsLeftProgressBar.setProgress(20, true);
        else if (rounds == 3)
            roundsLeftProgressBar.setProgress(40, true);
        else if (rounds == 2)
            roundsLeftProgressBar.setProgress(60, true);
        else if (rounds == 1)
            roundsLeftProgressBar.setProgress(80, true);
        else if (rounds <=0)
        {
            roundsLeftProgressBar.setProgress(100, true);
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    System.out.println("finish");
                    onFinish();
                }
            }, 1400);

        }

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("next");
                NextQuestion();
            }
        }, 1400);
    }

    public void onAnswer(){
        System.out.println("onAnswer");
        normalUI.setVisibility(View.INVISIBLE);
        ansUI.setAlpha(0f);
        ansUI.setVisibility(View.VISIBLE);
        ansUI.animate()
                .alpha(0.5f)
                .setDuration(400)
                .setListener(null);
    }

    public void onFinish() {
        finalScoreTextView.setText(Integer.toString(points));
        normalUI.setVisibility(View.INVISIBLE);
        finalUI.setVisibility(View.VISIBLE);
    }

    public void playAgain(View view){
        points=0;
        rounds=5;
        roundsLeftTextView.setText(Integer.toString(rounds));
        roundsLeftProgressBar.setProgress(0,true);
        scoreTextView.setText(Integer.toString(points));
        finalUI.setVisibility(View.INVISIBLE);
        normalUI.setVisibility(View.VISIBLE);
    }
}