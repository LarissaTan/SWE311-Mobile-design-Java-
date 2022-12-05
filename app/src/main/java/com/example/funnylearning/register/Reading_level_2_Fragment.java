package com.example.funnylearning.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.funnylearning.Bean.FindWordsBean;
import com.example.funnylearning.Database.FindWordsDao;
import com.example.funnylearning.Homepage;
import com.example.funnylearning.R;
import com.example.funnylearning.recycle.exercise.model_exercise;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Reading_level_2_Fragment extends Fragment {

    TextView scoreTextView;
    TextView timeLeftTextView;
    TextView alertTextView;
    TextView finalScoreTextView;

    ProgressBar timeLeftProgressBar;

    TextView btn1TextView;
    TextView btn2TextView;
    TextView btn3TextView;

    Button startBtn;
    Button playAgainBtn;
    Button quitBtn;

    LinearLayout normalUI;
    ConstraintLayout finalUI;
    ConstraintLayout ansUI;

    CountDownTimer countDownTimer;
    Random random =new Random();
    int display;
    int indexOfCorrectAnswer;
    ArrayList<String> answers = new ArrayList<String>();
    int points = 0;
    int progress = 0;

    Animation scaleUp,scaleDown;


    public Reading_level_2_Fragment() {
        // Required empty public constructor
    }


    public static Reading_level_2_Fragment newInstance(String param1, String param2) {
        Reading_level_2_Fragment fragment = new Reading_level_2_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint({"MissingInflatedId", "SetTextI18n", "ClickableViewAccessibility"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reading_level_2_, container, false);

        scoreTextView = view.findViewById(R.id.scoreTextView);
        timeLeftTextView = view.findViewById(R.id.timeLeftTextView);
        alertTextView = view.findViewById(R.id.alertTextView);
        finalScoreTextView =view.findViewById(R.id.finalScoreTextView);

        timeLeftProgressBar = view.findViewById(R.id.circular_progress_bar);

        final MediaPlayer mp_btn = MediaPlayer.create(getContext(),R.raw.game_button_click_sound);
        /*float log1=(float)(Math.log(maxVolume-currVolume)/Math.log(maxVolume));*/
        mp_btn.setVolume(100,100);

        btn1TextView = view.findViewById(R.id.reading_level_2_text_1);
        btn2TextView = view.findViewById(R.id.reading_level_2_text_2);
        btn3TextView = view.findViewById(R.id.reading_level_2_text_3);

        startBtn = view.findViewById(R.id.start_btn);
        playAgainBtn = view.findViewById(R.id.btnPlayAgain);
        quitBtn = view.findViewById(R.id.btnQuit);

        normalUI = view.findViewById(R.id.normalUI);
        finalUI = view.findViewById(R.id.finalUI);
        ansUI =view.findViewById(R.id.ansUI);

        startBtn.setVisibility(View.VISIBLE);
        normalUI.setVisibility(View.INVISIBLE);
        finalUI.setVisibility(View.INVISIBLE);
        ansUI.setVisibility(View.INVISIBLE);

        scaleUp = AnimationUtils.loadAnimation(getContext(), R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(getContext(), R.anim.scale_down);

        scoreTextView.setText(Integer.toString(points));

        startBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    startBtn.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            start(view);
                        }
                    }, 500);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    startBtn.startAnimation(scaleUp);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    startBtn.startAnimation(scaleDown);
                }
                return true;
            }
        });

        btn1TextView.setTag(0);
        btn1TextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    btn1TextView.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            optionSelect(view);
                        }
                    }, 500);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    btn1TextView.startAnimation(scaleUp);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    btn1TextView.startAnimation(scaleDown);
                }
                return true;
            }
        });

        btn2TextView.setTag(1);
        btn2TextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    btn2TextView.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            optionSelect(view);
                        }
                    }, 500);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    btn2TextView.startAnimation(scaleUp);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    btn2TextView.startAnimation(scaleDown);
                }
                return true;
            }
        });

        btn3TextView.setTag(2);
        btn3TextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    btn3TextView.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            optionSelect(view);
                        }
                    }, 500);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    btn3TextView.startAnimation(scaleUp);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    btn3TextView.startAnimation(scaleDown);
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
                    Intent it = new Intent(getContext(), Homepage.class);
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

    @SuppressLint("SetTextI18n")
    public void NextQuestion(){

        FindWordsDao dao = new FindWordsDao(getContext());
        dao.open();

        ArrayList<FindWordsBean> words = dao.getAllWords();

        display = random.nextInt(words.size());
        indexOfCorrectAnswer = random.nextInt(3);

        answers.clear();
        for(int i = 0; i<3; i++){

            if(indexOfCorrectAnswer == i){
                answers.add(words.get(display).correct);
            }else {
                if(!answers.contains(words.get(display).wrong1))
                {
                    answers.add(words.get(display).wrong1);
                }
                else
                {
                    answers.add(words.get(display).wrong2);
                }
            }
        }

        btn1TextView.setText(answers.get(0));
        btn2TextView.setText(answers.get(1));
        btn3TextView.setText(answers.get(2));
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
            alertTextView.setText("Correct Answer: " + (answers.get(indexOfCorrectAnswer)));
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
                                if (finalUI.getVisibility() == View.INVISIBLE) {
                                    normalUI.setVisibility(View.VISIBLE);
                                }
                            }
                        });
            }
        }, 2300);


        scoreTextView.setText(Integer.toString(points));

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
    public void playAgain(View view){
        points=0;
        scoreTextView.setText(Integer.toString(points));
        timeLeftProgressBar.setProgress(0,true);
        countDownTimer.start();
        finalUI.setVisibility(View.INVISIBLE);
        normalUI.setVisibility(View.VISIBLE);
    }

    public void start(View view) {

        final MediaPlayer mp_countdown = MediaPlayer.create(getContext(),R.raw.countdown_sound);
        mp_countdown.setVolume(100,100);

        final MediaPlayer mp_game_over = MediaPlayer.create(getContext(),R.raw.game_over_sound);
        mp_game_over.setVolume(100,100);

        startBtn.setVisibility(View.INVISIBLE);
        normalUI.setVisibility(View.VISIBLE);
        NextQuestion();
        countDownTimer = new CountDownTimer(60000,1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftTextView.setText(String.valueOf(millisUntilFinished/1000));
                if (progress >= 80)
                {
                    progress ++;
                    timeLeftProgressBar.setProgress(progress, true);
                }
                else
                {
                    progress += 2;
                    timeLeftProgressBar.setProgress(progress, true);
                }

                if(progress >= 90)
                {
                    mp_countdown.start();
                }

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                finalScoreTextView.setText(Integer.toString(points));
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mp_game_over.start();
                        normalUI.setVisibility(View.INVISIBLE);
                        finalUI.setVisibility(View.VISIBLE);
                    }
                }, 1400);
            }
        }.start();
    }
}