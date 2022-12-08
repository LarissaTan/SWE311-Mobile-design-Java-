package com.example.funnylearning.temp_head;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funnylearning.Bean.model.CourseVideo;
import com.example.funnylearning.Bean.model.CourseVideoComment;
import com.example.funnylearning.Bean.model.UserData;
import com.example.funnylearning.Database.CourseCommentDao;
import com.example.funnylearning.Database.CourseLikeDao;
import com.example.funnylearning.Database.CourseVideoDao;
import com.example.funnylearning.Database.UserDataDao;
import com.example.funnylearning.R;
import com.example.funnylearning.recycle.comment.adapter_comment;
import com.example.funnylearning.recycle.comment.model_comment;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Date;

public class VideoFragment extends Fragment {

    private RecyclerView cmtList;
    private final ArrayList<model_comment> commentList = new ArrayList<model_comment>();
    private TextView view_no;
    private TextView like_no;
    private TextView comment_no;
    private EditText commentInput;
    private ImageButton btn_enter;

    public VideoFragment() {
        // Required empty public constructor
    }


    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();

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
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        Integer userId = getArguments().getInt("userId");
        Integer courseId = getArguments().getInt("courseId");
        String videoId = getArguments().getString("videoId");

        view_no = view.findViewById(R.id.video_text_view);
        like_no = view.findViewById(R.id.video_text_like);
        comment_no = view.findViewById(R.id.video_text_comment);

        cmtList = (RecyclerView) view.findViewById(R.id.recyclerview_video_comments);

        commentInput = view.findViewById(R.id.video_edittext_comment);
        btn_enter = view.findViewById(R.id.video_edittext_comment_button);

        updateViewNumber(view.getContext(), courseId);
        updateLikeNumber(view.getContext(), courseId);
        updateCommentNumber(view.getContext(), courseId);

        YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer){
                youTubePlayer.loadVideo(videoId,0);
            }
        });

        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = new String();
                comment = commentInput.getText().toString();
                if(comment == ""){
                    Toast.makeText(view.getContext(), "Please enter comment to submit!", Toast.LENGTH_SHORT).show();
                }else {
                    CourseVideoComment courseVideoComment = new CourseVideoComment();
                    Date currentDate = new Date(System.currentTimeMillis());
                    courseVideoComment.setUserId(userId);
                    courseVideoComment.setCourseId(courseId);
                    courseVideoComment.setComment(comment);
                    courseVideoComment.setDate(currentDate);

                    insertComment(view.getContext(), courseVideoComment);
                    updateCommentNumber(view.getContext(), courseId);
                    commentInput.setText(null);
                }
            }
        });


        return view;
    }

    private void updateViewNumber(Context context, int courseId){
        CourseVideoDao courseVideoDao = new CourseVideoDao(context);
        courseVideoDao.updateViewNumber(courseId);
        Integer numberOfLike = courseVideoDao.getViewNumber(courseId);
        view_no.setText(numberOfLike.toString());
    }

    private void updateLikeNumber(Context context, int courseId){
        CourseLikeDao courseLikeDao = new CourseLikeDao(context);
        Integer numberOfLike = courseLikeDao.getLikeNumber(courseId);
        like_no.setText(numberOfLike.toString());
    }

    private void updateCommentNumber(Context context, int courseId){
        CourseCommentDao courseCommentDao = new CourseCommentDao(context);
        courseCommentDao.open();

        Integer numberOfComment = courseCommentDao.getCommentNumber(courseId);
        comment_no.setText(numberOfComment.toString());

        UserDataDao userDataDao = new UserDataDao(context);
        userDataDao.open();

        ArrayList<CourseVideoComment> comments = courseCommentDao.getAllVideoComment(courseId);
        commentList.clear();
        for (int i=0; i<comments.size(); i++){
            // get and set duration
            Date commentDate = comments.get(i).getDate();
            Date currentDate = new Date(System.currentTimeMillis());
            System.out.println("comment_date" + commentDate);
            System.out.println("current_date" + currentDate);
            long duration = currentDate.getTime() - commentDate.getTime();
            String textDuration = changeDurationText(duration);

            // set user data
            UserData userData = new UserData();
            userData = userDataDao.getUserData(comments.get(i).getUserId());
            String userName = new String();
            userName = userData.getName();
            int profile = userData.getProfilePicture();

            // add to list
            commentList.add(new model_comment(profile, userName, textDuration, comments.get(i).getComment()));
        }

        System.out.println("message is working");
        cmtList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter_comment customAdapterComment = new adapter_comment(commentList);
        System.out.println("layout manager is working");
        cmtList.setAdapter(customAdapterComment);
    }

    private String changeDurationText(long duration){
        String textDuration = new String();
        long difference_in_year = duration / (1000L * 60 * 60 * 24 * 365);
        if(difference_in_year>0){
            if(difference_in_year == 1){
                textDuration = difference_in_year + " year ago";
            }else {
                textDuration = difference_in_year + " years ago";
            }
        }else {
            long difference_in_month = (duration / (1000L * 60 * 60 * 24 * (365 /12))) % 12;
            if(difference_in_month>0) {
                if (difference_in_month == 1) {
                    textDuration = difference_in_month + " month ago";
                } else {
                    textDuration = difference_in_month + " months ago";
                }
            }else {
                long difference_in_day = (duration / (1000 * 60 * 60 * 24)) % 31;
                if(difference_in_day>0) {
                    if (difference_in_day == 1) {
                        textDuration = difference_in_day + " day ago";
                    } else {
                        textDuration = difference_in_day + " days ago";
                    }
                }else {
                    long difference_in_hour = (duration / (1000 * 60 * 60)) % 24;
                    if(difference_in_hour>0) {
                        if (difference_in_hour == 1) {
                            textDuration = difference_in_hour + " hour ago";
                        } else {
                            textDuration = difference_in_hour + " hours ago";
                        }
                    }else {
                        long difference_in_minute = (duration / (1000 * 60)) % 60;
                        if(difference_in_minute>0) {
                            if (difference_in_minute == 1) {
                                textDuration = difference_in_minute + " minute ago";
                            } else {
                                textDuration = difference_in_minute + " minutes ago";
                            }
                        }else{
                            long difference_in_second = (duration / 1000) % 60;
                            if(difference_in_second>=0) {
                                if (difference_in_second <= 1) {
                                    textDuration = difference_in_second + " second ago";
                                } else {
                                    textDuration = difference_in_second + " seconds ago";
                                }
                            }
                        }
                    }
                }
            }
        }
        return textDuration;
    }

    private void insertComment(Context context, CourseVideoComment courseVideoComment){
        CourseCommentDao courseCommentDao = new CourseCommentDao(context);
        courseCommentDao.open();
        courseCommentDao.insertComment(courseVideoComment);
    }
}