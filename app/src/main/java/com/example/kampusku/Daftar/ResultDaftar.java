package com.example.kampusku.Daftar;

public class ResultDaftar {
    String nama_univ;

    public int getId_daftar() {
        return id_daftar;
    }

    public void setId_daftar(int id_daftar) {
        this.id_daftar = id_daftar;
    }

    int id_daftar;
    int biaya;
    int fee;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public String getNama_univ() {
        return nama_univ;
    }

    public void setNama_univ(String nama_univ) {
        this.nama_univ = nama_univ;
    }


    public int getBiaya() {
        return biaya;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNama_prodi() {
        return nama_prodi;
    }

    public void setNama_prodi(String nama_prodi) {
        this.nama_prodi = nama_prodi;
    }

    int id_user;
    String nama_prodi;
}
