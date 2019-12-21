package com.example.kampusku.User;

import java.util.List;

public class ValueUser {

    String value;
    String message;
    String email;
    String name;

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    String profile_image;
    List<ResultProfile> result;

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public List<ResultProfile> getResult() {
        return result;
    }


    public String getMessage() {
        return message;
    }

    public String getValue() {
        return value;
    }
}
