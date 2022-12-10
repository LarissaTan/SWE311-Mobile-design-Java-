package com.example.funnylearning.data;

import android.content.Context;

import com.example.funnylearning.Bean.model.Cartoons;
import com.example.funnylearning.Database.CartoonsDao;

public class DataCartoons {
    public static void start(Context context){
        /***
         * 插入cartoons 数据
         */

        CartoonsDao dao=new CartoonsDao(context);
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
        c.image = "https://i.328888.xyz/img/2022/12/01/ibDft.png";

        dao.insertCartoon(c);

        c.id = 2;
        c.Name ="Alphabet letters";
        c.Duration = "28MIN";
        c.Level = 1;
        c.Url = "pFU--VNjxFw";
        c.Summary = "This video mainly talks about 26 digits character alphabet! What`s more, it is quite easy and good for everyone. ";
        c.Key1 = "The main character, the cute girl uses her cute voice to introduce the 26 digits character alphabet! ";
        c.Key2 = "This video uses the easiest words to teaching alphabet. And this is quite interesting for children to teach.";
        c.image = "https://i.328888.xyz/img/2022/12/01/ib4oX.png";

        dao.insertCartoon(c);

        c.id = 3;
        c.Name ="Coin Operated";
        c.Duration = "5MIN";
        c.Level = 1;
        c.Url = "5L4DQfVIcdg";
        c.Summary = "Coin Operated is an award-winning 5 minute animated short film that spans 70 years in the life of one naive explorer.";
        c.Key1 = "Beautifully directed, absolutely amazing output and animations. Loved the whole short.";
        c.Key2 = "This is a super cute, and heart felt short film. It deserved every last one of those awards.";
        c.image = "https://i.328888.xyz/img/2022/12/01/ibyRP.png";

        dao.insertCartoon(c);

        c.id = 4;
        c.Name ="Destiny";
        c.Duration = "5MIN";
        c.Level = 1;
        c.Url = "wEKLEeY_WeQ";
        c.Summary = "The lesson I gained from this was: there’s no need to live life perfectly to the minute. Things go wrong all the time. ";
        c.Key1 = "Moral of the story : Even those who believe in destiny, look both sides of the road before crossing";
        c.Key2 = "Easy for children to understand. And this is just a relax video. It does not have any lines.";
        c.image = "https://i.328888.xyz/img/2022/12/01/iO1j8.png";

        dao.insertCartoon(c);
    }
}
