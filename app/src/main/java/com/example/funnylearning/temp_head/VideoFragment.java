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
import android.widget.TextView;

import com.example.funnylearning.Bean.model.CourseVideo;
import com.example.funnylearning.Database.CourseCommentDao;
import com.example.funnylearning.Database.CourseLikeDao;
import com.example.funnylearning.Database.CourseVideoDao;
import com.example.funnylearning.R;
import com.example.funnylearning.recycle.comment.adapter_comment;
import com.example.funnylearning.recycle.comment.model_comment;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class VideoFragment extends Fragment {

    private RecyclerView cmtList;
    private final ArrayList<model_comment> commentList = new ArrayList<model_comment>();
    private TextView view_no;
    private TextView like_no;
    private TextView comment_no;

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

        int profile1 = R.drawable.video_comment_picture1;
        String name1 = "Eugene Rodriquez";
        String duration1 = "2 hours ago";
        String comment1 = "This video is easy for my child to learn. She understand a lot. And it motivates her to continue learning.";

        int profile2 = R.drawable.video_comment_picture2;
        String name2 = "Mabel Hansen";
        String duration2 = "3 hours ago";
        String comment2 = "My boy can fully follow up the video. He likes the voice of the lecturer. Hope more lectures can be added.";

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        Integer userId = getArguments().getInt("userId");
        Integer courseId = getArguments().getInt("courseId");
        String videoId = getArguments().getString("videoId");

        view_no = view.findViewById(R.id.video_text_view);
        like_no = view.findViewById(R.id.video_text_like);
        comment_no = view.findViewById(R.id.video_text_comment);

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

        cmtList = (RecyclerView) view.findViewById(R.id.recyclerview_video_comments);

        commentList.clear();
        System.out.println("message is working");
        commentList.add(new model_comment(profile1, name1, duration1, comment1));
        commentList.add(new model_comment(profile2, name2, duration2, comment2));

        System.out.println("message is working");
        cmtList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter_comment customAdapterComment = new adapter_comment(commentList);
        System.out.println("layout manager is working");
        cmtList.setAdapter(customAdapterComment);

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
        Integer numberOfComment = courseCommentDao.getCommentNumber(courseId);
        comment_no.setText(numberOfComment.toString());
    }
}