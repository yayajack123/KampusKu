package com.example.kampusku.Prodi;



import java.util.List;

public class GetProdi {

    String status;
    List<ResultProdi> result = null;
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

    public List<ResultProdi> getResult() {
        return result;
    }

}
