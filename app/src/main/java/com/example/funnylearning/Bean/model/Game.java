package com.example.funnylearning.Bean.model;

public class Game {

        private int id;
        private String name;
        private int typeId;
        private int rating;
        private int image;
        private String link;
        private int goal;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getGoal() {
            return goal;
        }

        public void setGoal(int goal) {
            this.goal = goal;
        }
}
