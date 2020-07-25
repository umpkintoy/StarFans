package id.umpkindev.starfans;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import id.umpkindev.starfans.adapter.EventAdapter;
import id.umpkindev.starfans.adapter.OnEventClickListener;

public class EventListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        List<String> eventurl = new ArrayList<>();
        eventurl.add("https://cdn.discordapp.com/attachments/598894928063365149/736614118118785105/event2.jpg");
        eventurl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/EVENT%203.jpg?alt=media&token=6cb42f65-94d8-40ba-b3bb-97e103f0326d");
        eventurl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/EVENT%204.jpg?alt=media&token=e0fbf333-10d3-4e49-ab24-72138909f78a");
        eventurl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/RENJUN%20EVENT%201.jpg?alt=media&token=a055b5a2-abb5-489a-87fd-c2d5debd2d9e");

        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        EventAdapter adapter = new EventAdapter(eventurl);
        adapter.setOnEventClickListener(new OnEventClickListener() {
            @Override
            public void onEventClick(String id) {
                Intent welcome = new Intent(EventListActivity.this,EventDetailActivity.class);
                startActivity(welcome);
            }
        });
        recyclerView.setAdapter(adapter);
    }       
}