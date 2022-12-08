package com.example.funnylearning.recycle.game;

public class model_game {
    int userId;
    int gameId;
    String link;
    int drawable;
    String title;
    int rating;

    public model_game(int userId, int gameId, String title,  int drawable,  int rating, String link) {
        this.userId = userId;
        this.gameId = gameId;
        this.title = title;
        this.drawable = drawable;
        this.rating = rating;
        this.link = link;

    }

}
