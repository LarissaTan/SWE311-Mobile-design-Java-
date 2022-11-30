package com.example.funnylearning.temp_head;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.funnylearning.Bean.model.Cartoons;
import com.example.funnylearning.Database.CartoonsDao;
import com.example.funnylearning.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartoonsFragment extends Fragment {

    TextView title;
    TextView sum;
    TextView key1;
    TextView key2;
    TextView time;
    RatingBar level;

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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cartoons, container, false);

        Bundle bundle = new Bundle();
        bundle = this.getArguments();
        Integer index = Integer.valueOf(bundle.getString("NoItem"));


        YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_player_view_cartoon);
        getLifecycle().addObserver(youTubePlayerView);


        title = view.findViewById(R.id.cartoon_video_title);
        sum = view.findViewById(R.id.cartoon_summary);
        key1  = view.findViewById(R.id.cartoon_key1);
        key2 = view.findViewById(R.id.cartoon_key2);
        time = view.findViewById(R.id.cartoon_duration);
        level = view.findViewById(R.id.cartoon_level_detail);



        CartoonsDao dao=new CartoonsDao(view.getContext());
        //打开数据库
        dao.open();

        ArrayList<Cartoons> cartoonData=dao.getAllCartoons();


        title.setText(cartoonData.get(index).Name);
        sum.setText(cartoonData.get(index).Summary);
        key1.setText(cartoonData.get(index).Key1);
        key2.setText(cartoonData.get(index).Key2);
        time.setText(cartoonData.get(index).Duration);
        level.setRating(cartoonData.get(index).Level);


        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer){
                String videoId = cartoonData.get(index).Url;
                youTubePlayer.loadVideo(videoId,0);
            }
        });
        return view;
    }

}
