package com.made_suande_1811010036.regupmd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GetKegiatanActivity extends AppCompatActivity {

    private ListView lvKegiatan;
    private List<Kegiatan> kegiatanList;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_kegiatan);

        lvKegiatan = findViewById(R.id.lvKegiatan);
        kegiatanList = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Kegiatan kegiatan = snapshot.getValue(Kegiatan.class);
                    kegiatanList.add(kegiatan);
                }

                KegiatanAdapter adapter = new KegiatanAdapter(GetKegiatanActivity.this, kegiatanList);
                lvKegiatan.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d("my", "loadPost:onCancelled", databaseError.toException());
            }
        };
        mDatabase.child("kegiatan").addValueEventListener(listener);

    }
}