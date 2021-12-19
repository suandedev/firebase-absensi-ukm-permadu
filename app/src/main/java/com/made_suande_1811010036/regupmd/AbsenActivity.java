package com.made_suande_1811010036.regupmd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AbsenActivity extends AppCompatActivity {

//    inisiasi
    private EditText nama, npm, jurusan;
    private Button btnAbsen;

//    get database refrense
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);

//        get from action interface
        nama = findViewById(R.id.nama);
        npm = findViewById(R.id.npm);
        jurusan = findViewById(R.id.jurusan);
        btnAbsen = findViewById(R.id.btnAbsen);

//        prepare database
        mDatabase = FirebaseDatabase.getInstance().getReference();

//        if button click
        btnAbsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                do absen method add value
                doAbsen(nama.getText().toString(),
                        npm.getText().toString(),
                        jurusan.getText().toString());
            }
        });

    }

    private void doAbsen(String nama, String npm, String jurusan) {

        getDataKegiatan("-MrFmdiV0h5Owx4XhQQt", nama, npm, jurusan);

    }

    private void getDataKegiatan(String mykey, String nama, String npm, String jurusan) {

        String key = mDatabase.push().getKey();

        Absen absen = new Absen(key, nama, npm, jurusan);

//        Log.d("my", "getDataKegiatan: " + mykey);

        mDatabase.child("kegiatan").child(mykey).child("absensi").child(key).setValue(absen);
        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
    }
}