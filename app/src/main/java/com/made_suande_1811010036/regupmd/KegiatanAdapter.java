package com.made_suande_1811010036.regupmd;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class KegiatanAdapter extends ArrayAdapter<Kegiatan> {

    private Activity context;
    private List<Kegiatan> kegiatanList;

    public  KegiatanAdapter (Activity context, List<Kegiatan> kegiatanList) {
        super(context, R.layout.singgle_kegiatan, kegiatanList);
        this.context = context;
        this.kegiatanList = kegiatanList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.singgle_kegiatan, null, true);
        TextView tvKegiatan = (TextView) v.findViewById(R.id.namaKegiatan);
        TextView tvLokasi = v.findViewById(R.id.lokasi);

        Kegiatan kegiatan = kegiatanList.get(position);

        tvKegiatan.setText(kegiatan.getNamaKegiatan());
        tvLokasi.setText(kegiatan.getLokasi());
        return v;
    }
}
