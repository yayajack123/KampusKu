package com.example.kampusku.Kampus;

public class ResultDetail {

    int id_univ;
    int id_fakultas;
    int id_prodi;
    String nama_univ;
    String lokasi;
    String nama_fakultas;
    String nama_prodi;
    String tentang_prodi;
    int biaya;

    public int getId_prodi() {
        return id_prodi;
    }

    public void setId_prodi(int id_prodi) {
        this.id_prodi = id_prodi;
    }

    public int getId_univ() {
        return id_univ;
    }

    public void setId_univ(int id_univ) {
        this.id_univ = id_univ;
    }

    public int getId_fakultas() {
        return id_fakultas;
    }

    public void setId_fakultas(int id_fakultas) {
        this.id_fakultas = id_fakultas;
    }

    public String getNama_univ() {
        return nama_univ;
    }

    public void setNama_univ(String nama_univ) {
        this.nama_univ = nama_univ;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNama_fakultas() {
        return nama_fakultas;
    }

    public void setNama_fakultas(String nama_fakultas) {
        this.nama_fakultas = nama_fakultas;
    }

    public String getNama_prodi() {
        return nama_prodi;
    }

    public void setNama_prodi(String nama_prodi) {
        this.nama_prodi = nama_prodi;
    }

    public String getTentang_prodi() {
        return tentang_prodi;
    }

    public void setTentang_prodi(String tentang_prodi) {
        this.tentang_prodi = tentang_prodi;
    }

    public int getBiaya() {
        return biaya;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

}
