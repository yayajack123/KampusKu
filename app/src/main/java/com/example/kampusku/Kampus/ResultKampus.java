package com.example.kampusku.Kampus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultKampus {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("nama_univ")
    @Expose
    private String nama_univ;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("tentang")
    @Expose
    private String tentang;
    private String url;



    public  String getAlamat(){
        return lokasi;
    }

    public void SetAlamat(String lokasi){
        this.lokasi = lokasi;
    }

    public String getKampus() {
        return nama_univ;
    }

    public void setKampus(String nama_univ) {
        this.nama_univ = nama_univ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
