package com.made_suande_1811010036.regupmd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GetAbsensiActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView lv;

    private List<Absen> absenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_absensi);

        lv = findViewById(R.id.lv);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        String kegiatan = getIntent().getStringExtra("namaKegiatan");
//        String kegiatan = "saucam";
        absenList = new ArrayList<>();

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Kegiatan k = snapshot.getValue(Kegiatan.class);
                if (kegiatan.equals(k.namaKegiatan)) {
//                    Log.d("my", "true: " + k.key);
                    getAbsenByNamaKegiatan(k.key);
                } else {
                    Toast.makeText(getApplicationContext(), "nama kegiatan tidak cocok", Toast.LENGTH_SHORT).show();
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
        mDatabase.child("kegiatan").addChildEventListener(childEventListener);
    }

    private void getAbsenByNamaKegiatan(String key) {

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Absen absen = dataSnapshot.getValue(Absen.class);
                    absenList.add(absen);
                }

                Log.d("my", "absen: " + absenList.get(4).getNama());
                AbsenAdapter adapter = new AbsenAdapter(GetAbsensiActivity.this, absenList);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("my", "error " + error.getMessage());
            }
        };

        mDatabase.child("kegiatan").child(key).child("absensi").addValueEventListener(listener);
    }
}