package com.example.funnylearning.Bean.model;

import java.sql.Time;

public class UserData {
    private int userId, age, profilePicture;
    private String name, email, location;
    private Boolean gender;
    private Time learningGoal;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Time getLearningGoal() {
        return learningGoal;
    }

    public void setLearningGoal(Time learningGoal) {
        this.learningGoal = learningGoal;
    }
}
