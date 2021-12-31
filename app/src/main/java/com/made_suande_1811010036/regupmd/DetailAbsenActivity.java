package com.made_suande_1811010036.regupmd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DetailAbsenActivity extends AppCompatActivity {

    private TextView nama, npm, kabid, waktu;
    private Button btnKembali;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_absen);

        nama = findViewById(R.id.nama);
        npm = findViewById(R.id.npm);
        kabid = findViewById(R.id.kabid);
        waktu = findViewById(R.id.waktu);
        btnKembali = findViewById(R.id.btnKembali);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GetAbsensiActivity.class);
                startActivity(intent);
                finish();
            }
        });

        String getNama = getIntent().getStringExtra("nama");
        String getNpm = getIntent().getStringExtra("npm");
        String getKabid = getIntent().getStringExtra("kabid");
        String getWaktu = getIntent().getStringExtra("waktu");

        long gw = Long.parseLong(getWaktu);

        SimpleDateFormat date = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
//        Log.d("my", "onCreate: " );
//        Log.d("my", "onCreate: " + time.format(gw));


        nama.setText("nama : " + getNama);
        npm.setText("NPM : " + getNpm);
        kabid.setText("kabid : " + getKabid);
        waktu.setText("waktu absensi : " + date.format(gw) + " " + time.format(gw));
    }
}