package com.made_suande_1811010036.regupmd;

public class Absen {

    String key, nama, npm, jurusan;

    public Absen() {
    }

    public Absen(String key, String nama, String npm, String jurusan) {
        this.key = key;
        this.nama = nama;
        this.npm = npm;
        this.jurusan = jurusan;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}
