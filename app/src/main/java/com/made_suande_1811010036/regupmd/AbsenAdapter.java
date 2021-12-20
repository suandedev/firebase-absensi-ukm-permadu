package com.made_suande_1811010036.regupmd;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AbsenAdapter extends ArrayAdapter<Absen> {

    private Activity context;
    private List<Absen> absenList;

    public  AbsenAdapter (Activity context, List<Absen> absenList) {
        super(context, R.layout.singgle_absen, absenList);
        this.context = context;
        this.absenList = absenList;

    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.singgle_absen, null, true);
        TextView tvName = (TextView) v.findViewById(R.id.tvnama);
        TextView tvNpm = v.findViewById(R.id.tvnpm);

        Absen absen = absenList.get(position);

        tvName.setText(absen.getNama());
        tvNpm.setText(absen.getNpm());
        return v;
    }
}
