package com.example.funnylearning.recycle.game;

public class model_game {
    int gameId;
    String link;
    int drawable;
    String title;
    int rating;

    public model_game(int gameId, String link, int drawable, String title, int rating) {
        this.gameId = gameId;
        this.link = link;
        this.drawable = drawable;
        this.title = title;
        this.rating = rating;
    }

}
