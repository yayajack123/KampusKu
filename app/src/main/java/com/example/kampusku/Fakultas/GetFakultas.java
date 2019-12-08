package com.example.kampusku.Fakultas;

import com.example.kampusku.Prodi.ResultProdi;

import java.util.List;

public class GetFakultas {

    String status;
    List<ResultFakultas> result = null;
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

    public List<ResultFakultas> getResult() {
        return result;
    }
}
