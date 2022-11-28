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

        Cartoons c  = new Cartoons();

        c.id = 1;
        c.Name ="How To Make Pancakes!";
        c.Duration = "36MIN";
        c.Level = 3;
        c.Url = "_2yM8MM9qNs";
        c.Summary = "This video mainly celebrates Pancake day with Peppa Pig and her family! However, children who like to watch this one must be able to read sentences. ";
        c.Key1 = "This video is mainly to promote the harmonious coexistence of the peppa pig family. While teaching children English, they also teach children how to get along with their families.";
        c.Key2 = "The video itself is long, but it contains many topics in it. More time can be arranged for children to watch.";

        long tmp = dao.insertCartoon(c);


        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer){
                String videoId = "_2yM8MM9qNs";
                youTubePlayer.loadVideo(videoId,0);
            }
        });
        return view;
    }
}