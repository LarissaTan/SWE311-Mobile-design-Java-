package com.example.funnylearning.data;

import android.content.Context;

import com.example.funnylearning.Bean.model.CourseVideo;
import com.example.funnylearning.Bean.model.Game;
import com.example.funnylearning.Database.GameDao;
import com.example.funnylearning.R;

public class DataGame {

    public static void start(Context context){
        GameDao gameDao = new GameDao(context);
        gameDao.open();

        final int gameNum = 4;

        String[] gameName = new String[gameNum];
        int[] typeId = new int[gameNum];
        int[] rating = new int[gameNum];
        int[] image = new int[gameNum];
        String[] link = new String[gameNum];
        int[] goal = new int[gameNum];

        //game 1
        gameName[0] = "Count the sheep";
        typeId[0] = 10;
        rating[0] = 1;
        image[0] = R.drawable.math_sheep;
        link[0] = "com.example.funnylearning.register.Math_game_1";
        goal[0] = 90;

        //game 2
        gameName[1] = "Feed the cat";
        typeId[1] = 11;
        rating[1] = 3;
        image[1] = R.drawable.math_cat;
        link[1] = "com.example.funnylearning.register.Math_game_2";
        goal[1] = 80;

        //game 3
        gameName[2] = "Find the words";
        typeId[2] = 7;
        rating[2] = 4;
        image[2] = R.drawable.read_boy;
        link[2] = "com.example.funnylearning.register.Reading_game_2";
        goal[2] = 80;

        //game 4
        gameName[3] = "Deliver goods";
        typeId[3] = 7;
        rating[3] = 5;
        image[3] = R.drawable.read_car;
        link[3] = "com.example.funnylearning.register.Reading_game_1";
        goal[3] = 80;

        for(int i=0; i<gameNum; i++) {
            if (gameName[i] != null) {
                Game game = new Game();
                game.setName(gameName[i]);
                game.setTypeId(typeId[i]);
                game.setRating(rating[i]);
                game.setImage(image[i]);
                game.setLink(link[i]);
                game.setGoal(goal[i]);
                gameDao.insertGame(game);
            }
        }
    }
}
