package com.made_suande_1811010036.regupmd;

public class Kegiatan {
 String key, namaKegiatan, lokasi, waktu;

    public Kegiatan() {
    }

    public Kegiatan(String key, String namaKegiatan, String lokasi, String waktu) {
        this.key = key;
        this.namaKegiatan = namaKegiatan;
        this.lokasi = lokasi;
        this.waktu = waktu;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
