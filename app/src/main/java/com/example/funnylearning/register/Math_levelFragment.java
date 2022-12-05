package com.example.funnylearning.register;

import static com.example.funnylearning.navigation.MathFragment.EXTRA_NAME;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

import com.example.funnylearning.Homepage;
import com.example.funnylearning.MathLevel;
import com.example.funnylearning.R;
import com.example.funnylearning.Temp_head;
import com.example.funnylearning.navigation.MathFragment;
import com.example.funnylearning.recycle.math_level.adapter_math_level;
import com.example.funnylearning.recycle.math_level.model_math_level;

import java.util.ArrayList;
import java.util.Random;


public class Math_levelFragment extends Fragment {

    private RecyclerView mList;
    private final ArrayList<model_math_level> mathLevelList = new ArrayList<model_math_level>();

    String tagm;

    TextView scoreTextView;
    TextView roundsLeftTextView;
    TextView alertTextView;
    TextView finalScoreTextView;

    ProgressBar roundsLeftProgressBar;

    Button btn1;
    Button btn2;
    Button btn3;
    Button playAgainBtn;
    Button quitBtn;

    LinearLayout normalUI;
    ConstraintLayout finalUI;
    ConstraintLayout ansUI;

    Random random =new Random();
    int display;
    int indexOfCorrectAnswer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int points = 0;
    int rounds = 10;
    int progress = 0;

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

    @SuppressLint({"NotifyDataSetChanged", "MissingInflatedId", "ClickableViewAccessibility", "SetTextI18n"})
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
        quitBtn = view.findViewById(R.id.btnQuit);

        final MediaPlayer mp_btn = MediaPlayer.create(getContext(),R.raw.game_button_click_sound);
        mp_btn.setVolume(100,100);
        /*final MediaPlayer mp_bgm = MediaPlayer.create(getContext(),R.raw.math_level_bgm);
        mp_bgm.setVolume(50,50);
        mp_bgm.start();
        mp_bgm.setLooping(true);*/

        normalUI = view.findViewById(R.id.normalUI);
        finalUI = view.findViewById(R.id.finalUI);
        ansUI =view.findViewById(R.id.ansUI);

        normalUI.setVisibility(View.VISIBLE);
        finalUI.setVisibility(View.INVISIBLE);
        ansUI.setVisibility(View.INVISIBLE);

        scaleUp = AnimationUtils.loadAnimation(getContext(), R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(getContext(), R.anim.scale_down);

        mList = (RecyclerView) view.findViewById(R.id.recyclerview_math_level);

        scoreTextView.setText(Integer.toString(points));
        roundsLeftTextView.setText(Integer.toString(rounds));
        roundsLeftProgressBar.setProgress(progress,true);

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

                    mp_btn.start();
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

                    mp_btn.start();
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

                    mp_btn.start();
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

                    mp_btn.start();
                    playAgainBtn.startAnimation(scaleUp);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){

                    playAgainBtn.startAnimation(scaleDown);

                }

                return true;
            }
        });

        quitBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    quitBtn.startAnimation(scaleDown);
                    tagm = "0";
                    Intent it = new Intent(getContext(), Homepage.class);
                    /*it.putExtra("tagm",tagm);*/
                    startActivity(it);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    quitBtn.startAnimation(scaleUp);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){

                    quitBtn.startAnimation(scaleDown);

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

        indexOfCorrectAnswer = random.nextInt(3);

        mathLevelList.clear();
        System.out.println("message is working");

        System.out.println("display" + display+1);
        for (int i = 0; i < display+1; i ++) {
            System.out.println("loop");
            mathLevelList.add(new model_math_level(R.drawable.math_level_sheep));

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

        final MediaPlayer mp_correct = MediaPlayer.create(getContext(),R.raw.correct_answer_sound);
        mp_correct.setVolume(100,100);

        final MediaPlayer mp_wrong = MediaPlayer.create(getContext(),R.raw.wrong_answer_sound);
        mp_wrong.setVolume(100,100);

        if(Integer.toString(indexOfCorrectAnswer).equals(view.getTag().toString())){
            mp_correct.start();
            points+=10;
            alertTextView.setText("Correct");
            alertTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,45);
        }else {
            mp_wrong.start();
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
                        .setDuration(200)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                if (finalUI.getVisibility() == View.INVISIBLE)
                                    normalUI.setVisibility(View.VISIBLE);
                            }
                        });
            }
        }, 2300);


        scoreTextView.setText(Integer.toString(points));
        roundsLeftTextView.setText(Integer.toString(--rounds));
        roundsLeftTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        progress += 10;
        roundsLeftProgressBar.setProgress(progress, true);
        if (rounds <=0)
        {
            roundsLeftProgressBar.setProgress(100, true);
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    System.out.println("finish");
                    onFinish();
                }
            }, 2500);

        }

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("next");
                NextQuestion();
            }
        }, 1000);
    }

    public void onAnswer(){
        System.out.println("onAnswer");
        normalUI.setVisibility(View.INVISIBLE);
        ansUI.setAlpha(0f);
        ansUI.setVisibility(View.VISIBLE);
        ansUI.animate()
                .alpha(0.5f)
                .setDuration(500)
                .setListener(null);
    }

    @SuppressLint("SetTextI18n")
    public void onFinish() {
        final MediaPlayer mp_game_over = MediaPlayer.create(getContext(),R.raw.game_over_sound);
        mp_game_over.setVolume(100,100);

        mp_game_over.start();
        finalScoreTextView.setText(Integer.toString(points));
        normalUI.setVisibility(View.INVISIBLE);
        finalUI.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    public void playAgain(View view){
        points=0;
        rounds=10;
        progress = 0;
        roundsLeftTextView.setText(Integer.toString(rounds));
        roundsLeftProgressBar.setProgress(progress,true);
        scoreTextView.setText(Integer.toString(points));
        finalUI.setVisibility(View.INVISIBLE);
        normalUI.setVisibility(View.VISIBLE);
    }
}