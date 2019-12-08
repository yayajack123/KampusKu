package com.example.kampusku.Prodi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultProdi {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("nama_prodi")
    @Expose
    private String nama_prodi;
    @SerializedName("tentang")
    @Expose
    private String tentang ;
    @SerializedName("id_fakultas")
    @Expose
    private int id_fakultas;
    @SerializedName("id_univ")
    @Expose
    private int id_univ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_prodi() {
        return nama_prodi;
    }

    public void setNama_prodi(String nama_prodi) {
        this.nama_prodi = nama_prodi;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public int getId_fakultas() {
        return id_fakultas;
    }

    public void setId_fakultas(int id_fakultas) {
        this.id_fakultas = id_fakultas;
    }

    public int getId_univ() {
        return id_univ;
    }

    public void setId_univ(int id_univ) {
        this.id_univ = id_univ;
    }
}
