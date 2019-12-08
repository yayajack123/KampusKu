package com.example.kampusku.Kampus;

import com.google.gson.annotations.SerializedName;

public class PostPutDelKampus {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    ResultKampus mKampus;
    @SerializedName("message")
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public ResultKampus getKampus() {
        return mKampus;
    }
    public void setmKampus(ResultKampus kampus) {
        mKampus = kampus;
    }
}
