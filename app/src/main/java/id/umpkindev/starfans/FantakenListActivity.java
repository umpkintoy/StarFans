package id.umpkindev.starfans;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import id.umpkindev.starfans.adapter.EventAdapter;
import id.umpkindev.starfans.adapter.FantakenAdapter;
import id.umpkindev.starfans.adapter.OnEventClickListener;
import id.umpkindev.starfans.adapter.OnFantakenClickListener;

public class FantakenListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fantaken_list);

        List<String>fantakenturl = new ArrayList<>();
        fantakenturl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/BabyBeanie0323_191115.jpg?alt=media&token=2950840b-cbae-42f9-80df-601d024c5284");
        fantakenturl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/BabyBeanie0323_191116.jpg?alt=media&token=862b1c07-9514-4a49-bbf3-5a792096e320");
        fantakenturl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/BabyBeanie0323_1911162.jpg?alt=media&token=a1da9613-c598-464a-abd5-5862892a61aa");
        fantakenturl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/BabyBeanie0323_1911162%3D5.jpg?alt=media&token=331d6381-5dc7-476e-a208-b518aa7def8e");

        RecyclerView recyclerView = findViewById(R.id.fantaken_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        FantakenAdapter adapter = new FantakenAdapter(fantakenturl);
        adapter.setFansTakenListener(new OnFantakenClickListener() {
            @Override
            public void onFantakenClick(String id) {
                Intent welcome = new Intent(FantakenListActivity.this,EventDetailActivity.class);
                startActivity(welcome);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}