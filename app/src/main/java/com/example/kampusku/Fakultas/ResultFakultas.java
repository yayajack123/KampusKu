package com.example.kampusku.Fakultas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultFakultas {

    @SerializedName("id")
    @Expose
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_fakultas() {
        return nama_fakultas;
    }

    public void setNama_fakultas(String nama_fakultas) {
        this.nama_fakultas = nama_fakultas;
    }

    @SerializedName("nama_fakultas")
    @Expose
    private String nama_fakultas;
}
