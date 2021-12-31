package com.made_suande_1811010036.regupmd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    private List<Kegiatan> kegiatanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_absensi);

        lv = findViewById(R.id.lv);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        String kegiatan = getIntent().getStringExtra("namaKegiatan");
//        String kegiatan = "saucam";
        absenList = new ArrayList<>();
        kegiatanList = new ArrayList<>();

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Kegiatan k = snapshot.getValue(Kegiatan.class);
                if (kegiatan.equals(k.namaKegiatan)) {
//                    Log.d("my", "true: " + k.key);
                    getAbsenByNamaKegiatan(k.key);
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

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Kegiatan kegiatan1 = snapshot.getValue(Kegiatan.class);
                    kegiatanList.add(kegiatan1);
                }
//                Log.d("my", "onDataChange: " + kegiatanList.get(0).getNamaKegiatan());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("my", "loadPost:onCancelled", databaseError.toException());
            }
        };
        mDatabase.child("kegiatan").addValueEventListener(postListener);
    }

    private void getAbsenByNamaKegiatan(String key) {

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Absen absen = dataSnapshot.getValue(Absen.class);
                    absenList.add(absen);
                }

//                Log.d("my", "absen: " + absenList.get(0).getKabid());
                AbsenAdapter adapter = new AbsenAdapter(GetAbsensiActivity.this, absenList);
                lv.setAdapter(adapter);

                lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getApplicationContext(), DetailAbsenActivity.class);
                        intent.putExtra("nama", absenList.get(i).getNama());
                        intent.putExtra("npm", absenList.get(i).getNpm());
                        intent.putExtra("kabid", absenList.get(i).getKabid());
                        intent.putExtra("waktu", absenList.get(i).getWaktu());
                        startActivity(intent);
                        Log.d("my", "onItemLongClick: " + absenList.get(i).getWaktu());
                        return false;
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("my", "error " + error.getMessage());
            }
        };

        mDatabase.child("kegiatan").child(key).child("absensi").addValueEventListener(listener);
    }
}