package com.example.funnylearning.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.Bean.model.UserGameRecord;
import com.example.funnylearning.Database.UserGameRecordDao;
import com.example.funnylearning.Database.UserGoalLevelDao;
import com.example.funnylearning.Homepage;
import com.example.funnylearning.MathLevel;
import com.example.funnylearning.R;
import com.example.funnylearning.recycle.math_level.adapter_math_level;
import com.example.funnylearning.recycle.math_level.model_math_level;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Math_game_1 extends AppCompatActivity {

    //Initialization of variables
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
    Button quitBtn;

    LinearLayout normalUI;
    ConstraintLayout finalUI;
    ConstraintLayout ansUI;

    Random random =new Random();
    int display;
    int indexOfCorrectAnswer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    ArrayList<Integer> numbers = new ArrayList<Integer>();
    int points = 0;
    int rounds = 10;
    int progress = 0;
    int achievement = 90;

    Animation scaleUp,scaleDown;

    MediaPlayer mp_btn = new MediaPlayer();
    MediaPlayer mp_correct = new MediaPlayer();
    MediaPlayer mp_wrong = new MediaPlayer();
    MediaPlayer mp_countdown = new MediaPlayer();
    MediaPlayer mp_game_over = new MediaPlayer();

    int userId = 0;
    int gameId = 0;

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game1);

        //Getting user and game id from previous page
        Bundle extra = getIntent().getExtras();
        if(extra != null){
            userId = extra.getInt("userId");
            gameId = extra.getInt("gameId");
        }else{
            Toast.makeText(this, "Id not passed", Toast.LENGTH_SHORT).show();
        }
        System.out.println("userid: " + userId);
        System.out.println("gameid" + gameId);

        //Declaration of variables
        scoreTextView = findViewById(R.id.scoreTextView);
        roundsLeftTextView = findViewById(R.id.roundsLeftTextView);
        alertTextView = findViewById(R.id.alertTextView);
        finalScoreTextView = findViewById(R.id.finalScoreTextView);

        roundsLeftProgressBar =findViewById(R.id.circular_progress_bar);

        btn1 = findViewById(R.id.math_level_btn1);
        btn2 = findViewById(R.id.math_level_btn2);
        btn3 = findViewById(R.id.math_level_btn3);
        playAgainBtn = findViewById(R.id.btnPlayAgain);
        quitBtn = findViewById(R.id.btnQuit);

        mp_btn = MediaPlayer.create(this,R.raw.game_button_click_sound);
        mp_btn.setVolume(100,100);

        normalUI = findViewById(R.id.normalUI);
        finalUI = findViewById(R.id.finalUI);
        ansUI = findViewById(R.id.ansUI);

        normalUI.setVisibility(View.VISIBLE);
        finalUI.setVisibility(View.INVISIBLE);
        ansUI.setVisibility(View.INVISIBLE);

        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        mList = (RecyclerView) findViewById(R.id.recyclerview_math_level);

        scoreTextView.setText(Integer.toString(points));
        roundsLeftTextView.setText(Integer.toString(rounds));
        roundsLeftProgressBar.setProgress(progress,true);

        NextQuestion();

        //button 1
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

        //button 2
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

        //button 3
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

        //play again button
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

        //quit button
        quitBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    quitBtn.startAnimation(scaleDown);
                    Math_game_1.this.finish();

                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    quitBtn.startAnimation(scaleUp);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){

                    quitBtn.startAnimation(scaleDown);

                }

                return true;
            }
        });
    }

    //render next question
    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    public void NextQuestion() {

        btn1.setTextColor(Color.parseColor("#F9D150"));
        btn1.setBackgroundColor(Color.WHITE);
        btn2.setTextColor(Color.parseColor("#F9D150"));
        btn2.setBackgroundColor(Color.WHITE); btn1.setTextColor(Color.parseColor("#F9D150"));
        btn3.setBackgroundColor(Color.WHITE);
        btn3.setTextColor(Color.parseColor("#F9D150"));

        //question
        display = random.nextInt(4);

        //location of answer
        indexOfCorrectAnswer = random.nextInt(3);

        mathLevelList.clear();
        System.out.println("message is working");

        System.out.println("display" + display+1);
        //add to recycler view
        for (int i = 0; i < display+1; i ++) {
            System.out.println("loop");
            mathLevelList.add(new model_math_level(R.drawable.math_level_sheep));
        }
        System.out.println(display);

        adapter_math_level customAdapter = new adapter_math_level(mathLevelList);
        customAdapter.notifyDataSetChanged();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);

        if((display+1) % 2 != 0) {
            adjustSpan(gridLayoutManager);
        }

        mList.setLayoutManager(gridLayoutManager);
        mList.setAdapter(customAdapter);

        //number list
        for (int i = 0; i < 5; i ++)
        {
            numbers.add(i);
        }

        answers.clear();
        System.out.println("index " + indexOfCorrectAnswer + "display" + display+1);
        for (int i = 0; i < 3; i++) {

            if (i == indexOfCorrectAnswer) {
                System.out.println("button correct " + display +1);
                answers.add(display+1);
            }
            //non repeated wrong answer
            else {
                int wrongAnswer = random.nextInt(5);
                while(numbers.get(wrongAnswer)==display+1 || answers.contains(numbers.get(wrongAnswer)))
                {
                    wrongAnswer = random.nextInt(5);
                }

                answers.add(numbers.get(wrongAnswer));
            }
        }

        btn1.setText(Integer.toString(answers.get(0)));
        btn2.setText(Integer.toString(answers.get(1)));
        btn3.setText(Integer.toString(answers.get(2)));
    }

    //adjust span of grid layout
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

    //on click
    @SuppressLint("SetTextI18n")
    public void optionSelect(View view){

        mp_correct = MediaPlayer.create(this,R.raw.correct_answer_sound);
        mp_correct.setVolume(100,100);

        mp_wrong = MediaPlayer.create(this,R.raw.wrong_answer_sound);
        mp_wrong.setVolume(100,100);

        //correct answer
        if(Integer.toString(indexOfCorrectAnswer).equals(view.getTag().toString())){
            mp_correct.start();
            points+=10;
            alertTextView.setText("Correct");
            alertTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,45);
        }
        //wrong answer
        else {
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


        //update UI
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

    //answer page
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

    //end page
    @SuppressLint("SetTextI18n")
    public void onFinish() {
        mp_game_over = MediaPlayer.create(this,R.raw.game_over_sound);
        mp_game_over.setVolume(100,100);

        mp_game_over.start();
        finalScoreTextView.setText(Integer.toString(points));
        normalUI.setVisibility(View.INVISIBLE);
        finalUI.setVisibility(View.VISIBLE);

        //update database
        UserGameRecord userGameRecord =  new UserGameRecord();
        Date date = new Date(System.currentTimeMillis());

        UserGameRecordDao userGameRecordDao = new UserGameRecordDao(this);
        userGameRecordDao.open();

        userGameRecord.setDate(date);
        userGameRecord.setUserId(userId);
        userGameRecord.setGameId(gameId);
        userGameRecord.setScore(points);
        userGameRecordDao.insertScore(userGameRecord);


        if(points >= achievement)
        {
            UserGoalLevelDao userGoalLevelDao = new UserGoalLevelDao(this);
            userGoalLevelDao.open();

            userGoalLevelDao.updateLevel(userId,gameId);
        }
    }

    //play again reset game
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
        if(mp_game_over.isPlaying())
        {
            mp_game_over.stop();
        }
    }

    //clean up
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        if(mp_btn.isPlaying())
        {
            mp_btn.stop();
            mp_btn.release();
        }
        if(mp_correct.isPlaying())
        {
            mp_correct.stop();
            mp_correct.release();
        }
        if(mp_wrong.isPlaying())
        {
            mp_wrong.stop();
            mp_wrong.release();
        }
        if(mp_game_over.isPlaying())
        {
            mp_game_over.stop();
            mp_game_over.release();
        }
        if(mp_countdown.isPlaying())
        {
            mp_countdown.stop();
            mp_countdown.release();
        }
    }

}
