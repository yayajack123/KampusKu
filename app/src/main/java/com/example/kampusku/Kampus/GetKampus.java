package com.example.kampusku.Kampus;

import android.widget.ListView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKampus {

    String status;
    List<ResultKampus> result = null;
    List<ResultDetail> test = null;
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
    public List<ResultKampus> getResult() {
        return result;
    }

}
