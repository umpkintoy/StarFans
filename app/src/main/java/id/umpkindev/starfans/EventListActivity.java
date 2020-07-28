package id.umpkindev.starfans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import id.umpkindev.starfans.adapter.EventAdapter;
import id.umpkindev.starfans.adapter.OnEventClickListener;
import id.umpkindev.starfans.models.EventModel;

public class EventListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        final List<EventModel> eventModels = new ArrayList<>();

        //List<String> eventurl = new ArrayList<>();
        //eventurl.add("https://cdn.discordapp.com/attachments/598894928063365149/736614118118785105/event2.jpg");
        //eventurl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/EVENT%203.jpg?alt=media&token=6cb42f65-94d8-40ba-b3bb-97e103f0326d");
        //eventurl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/EVENT%204.jpg?alt=media&token=e0fbf333-10d3-4e49-ab24-72138909f78a");
        //eventurl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/RENJUN%20EVENT%201.jpg?alt=media&token=a055b5a2-abb5-489a-87fd-c2d5debd2d9e");

        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //adapter sebagai wadah event
        final EventAdapter adapter = new EventAdapter(eventModels);
        adapter.setOnEventClickListener(new OnEventClickListener() {
            @Override
            public void onEventClick(String id) {
                Intent welcome = new Intent(EventListActivity.this,EventDetailActivity.class);
                welcome.putExtra("id",id);
                startActivity(welcome);
            }
        });
        recyclerView.setAdapter(adapter);

//tambahin database collection "events", masukin data ke eventmodel
        db.collection("events")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("event", document.getId() + " => " + document.getData());
                                EventModel eventModel = new EventModel(document.getId(),document.getData()); // memasukan data dari firebase sbg eventmodel
                                eventModels.add(eventModel);
                            }
                            adapter.notifyDataSetChanged(); // notif adapter bahwa data telah berubah
                        } else {
                            Log.w("event", "Error getting documents.", task.getException());
                        }
                    }
                });

    }
}