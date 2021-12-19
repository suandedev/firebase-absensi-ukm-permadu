package com.made_suande_1811010036.regupmd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AbsenActivity extends AppCompatActivity {

    private EditText nama, npm, jurusan;
    private Button btnAbsen;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);

        nama = findViewById(R.id.nama);
        npm = findViewById(R.id.npm);
        jurusan = findViewById(R.id.jurusan);
        btnAbsen = findViewById(R.id.btnAbsen);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnAbsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAbsen(nama.getText().toString(),
                        npm.getText().toString(),
                        jurusan.getText().toString());
            }
        });

    }

    private void doAbsen(String nama, String npm, String jurusan) {

        String key = mDatabase.push().getKey();

        Absen absen = new Absen(key, nama, npm, jurusan);

        mDatabase.child("-MrFaok6NCnfI6e_n_iW").child("absensi").child(key).setValue(absen);
        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
    }
}