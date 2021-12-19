package com.made_suande_1811010036.regupmd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText namaKegiatan, lokasi, waktu;
    private Button btnSave;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namaKegiatan = findViewById(R.id.namaKegiatan);
        lokasi = findViewById(R.id.lokasi);
        waktu = findViewById(R.id.waktu);
        btnSave = findViewById(R.id.btnSave);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveKegiatan(namaKegiatan.getText().toString(),
                        lokasi.getText().toString(),
                        waktu.getText().toString());
            }
        });
    }

    private void saveKegiatan(String namaKegiatan, String lokasi, String waktu) {

        String key = mDatabase.push().getKey();
        Kegiatan kegiatan = new Kegiatan(key, namaKegiatan, lokasi, waktu);

        mDatabase.child(key).setValue(kegiatan);
        Toast.makeText(getApplicationContext(), "succes", Toast.LENGTH_SHORT).show();
    }
}