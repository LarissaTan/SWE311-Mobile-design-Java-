package com.example.funnylearning.temp_head;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.funnylearning.Bean.model.Cartoons;
import com.example.funnylearning.Database.CartoonsDao;
import com.example.funnylearning.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;
import java.util.Map;

public class CartoonsFragment extends Fragment {


    public CartoonsFragment() {
        // Required empty public constructor
    }

    public static CartoonsFragment newInstance(String param1, String param2) {
        CartoonsFragment fragment = new CartoonsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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

        View view = inflater.inflate(R.layout.fragment_cartoons, container, false);


        YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_player_view_cartoon);
        getLifecycle().addObserver(youTubePlayerView);





        CartoonsDao dao=new CartoonsDao(view.getContext());
        //打开数据库
        dao.open();

        Cartoons cartoonData=dao.getAllCartoons();


        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer){
                String videoId = cartoonData.Url;
                youTubePlayer.loadVideo(videoId,0);
            }
        });
        return view;
    }
}