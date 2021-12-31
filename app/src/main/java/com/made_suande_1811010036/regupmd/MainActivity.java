package com.made_suande_1811010036.regupmd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText namaKegiatan, lokasi, waktu, cekNamaKegiatan;
    private Button btnSave, btnCek, btnCekKegiatan;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namaKegiatan = findViewById(R.id.namaKegiatan);
        lokasi = findViewById(R.id.lokasi);
        waktu = findViewById(R.id.waktu);
        cekNamaKegiatan = findViewById(R.id.cekNamaKegiatan);
        btnSave = findViewById(R.id.btnSave);
        btnCek = findViewById(R.id.btnCek);
        btnCekKegiatan = findViewById(R.id.btnCekKegiatan);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveKegiatan(namaKegiatan.getText().toString(),
                        lokasi.getText().toString(),
                        waktu.getText().toString());
            }
        });

        btnCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), GetAbsensiActivity.class);
                i.putExtra("namaKegiatan", cekNamaKegiatan.getText().toString());
                startActivity(i);
            }
        });

        btnCekKegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), GetKegiatanActivity.class);
                startActivity(i);
            }
        });
    }

    private void saveKegiatan(String namaKegiatan, String lokasi, String waktu) {

        String key = mDatabase.push().getKey();
        Kegiatan kegiatan = new Kegiatan(key, namaKegiatan, lokasi, waktu);

        mDatabase.child("kegiatan").child(key).setValue(kegiatan);
        Toast.makeText(getApplicationContext(), "succes", Toast.LENGTH_SHORT).show();
    }
}