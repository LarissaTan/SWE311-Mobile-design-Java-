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
//
//        Cartoons c  = new Cartoons();
//
//        c.id = 4;
//        c.Name ="Destiny";
//        c.Duration = "5MIN";
//        c.Level = 1;
//        c.Url = "wEKLEeY_WeQ";
//        c.Summary = "The lesson I gained from this was: there’s no need to live life perfectly to the minute. Things go wrong all the time. ";
//        c.Key1 = "Moral of the story : Even those who believe in destiny, look both sides of the road before crossing";
//        c.Key2 = "Easy for children to understand. And this is just a relax video. It does not have any lines.";
//
//        long tmp = dao.insertCartoon(c);
//
//        System.out.println("tmp = " + tmp);

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