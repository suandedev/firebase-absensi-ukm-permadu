package com.made_suande_1811010036.regupmd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AbsenActivity extends AppCompatActivity {

//    inisiasi
    private EditText nama, npm, kabid, namaK;
    private Button btnAbsen;

//    get database refrense
    private DatabaseReference mDatabase;
    private String TAG = "my";

    private List<Kegiatan> kegiatanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);

//        get from action interface
        nama = findViewById(R.id.nama);
        npm = findViewById(R.id.npm);
        kabid = findViewById(R.id.kabid);
        namaK = findViewById(R.id.namaK);
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
                        kabid.getText().toString(), String.valueOf(System.currentTimeMillis()));
            }
        });

    }

    private void doAbsen(String nama, String npm, String kabid, String waktu) {

        kegiatanList = new ArrayList<>();
        String namaKegiatan = namaK.getText().toString();

        ChildEventListener kegiatan = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Kegiatan k = snapshot.getValue(Kegiatan.class);
//                Log.d(TAG, "onChildAdded: " + k.namaKegiatan);
                if (namaKegiatan.equals(k.namaKegiatan)) {
                    Log.d(TAG, "true: " + k.key);
                    getDataKegiatan(k.key, nama, npm, kabid, waktu);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDatabase.child("kegiatan").addChildEventListener(kegiatan);

    }

    private void getDataKegiatan(String mykey, String nama, String npm, String kabid, String waktu) {

        String key = mDatabase.push().getKey();
        Absen absen = new Absen(key, nama, npm, kabid, waktu);
        if (!nama.equals("") && !npm.equals("") && !kabid.equals("")) {
            mDatabase.child("kegiatan").child(mykey).child("absensi").child(key).setValue(absen);
            Toast.makeText(getApplicationContext(), "success absen", Toast.LENGTH_SHORT).show();
        } else  {
            Toast.makeText(getApplicationContext(), "nama, npm, kabid tidak kosong", Toast.LENGTH_SHORT).show();
        }
    }
}