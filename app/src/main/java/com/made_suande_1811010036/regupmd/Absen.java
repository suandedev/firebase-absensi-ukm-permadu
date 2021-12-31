package com.made_suande_1811010036.regupmd;

public class Absen {

    String key, nama, npm, kabid, waktu;


    public Absen() {
    }

    public Absen(String key, String nama, String npm, String kabid, String waktu) {
        this.key = key;
        this.nama = nama;
        this.npm = npm;
        this.kabid = kabid;
        this.waktu = waktu;
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

    public String getKabid() {
        return kabid;
    }

    public void setKabid(String kabid) {
        this.kabid = kabid;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
