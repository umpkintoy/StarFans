package id.umpkindev.starfans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import id.umpkindev.starfans.adapter.EventAdapter;
import id.umpkindev.starfans.adapter.FantakenAdapter;
import id.umpkindev.starfans.adapter.OnEventClickListener;
import id.umpkindev.starfans.adapter.OnFantakenClickListener;
import id.umpkindev.starfans.models.EventModel;
import id.umpkindev.starfans.models.FantakenModel;

public class FantakenListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fantaken_list);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        final List<FantakenModel> fantakenModels = new ArrayList<>();

        //List<String>fantakenturl = new ArrayList<>();
        //fantakenturl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/BabyBeanie0323_191115.jpg?alt=media&token=2950840b-cbae-42f9-80df-601d024c5284");
        //fantakenturl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/BabyBeanie0323_191116.jpg?alt=media&token=862b1c07-9514-4a49-bbf3-5a792096e320");
        //fantakenturl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/BabyBeanie0323_1911162.jpg?alt=media&token=a1da9613-c598-464a-abd5-5862892a61aa");
        //fantakenturl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/BabyBeanie0323_1911162%3D5.jpg?alt=media&token=331d6381-5dc7-476e-a208-b518aa7def8e");

        RecyclerView recyclerView = findViewById(R.id.fantaken_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final FantakenAdapter adapter = new FantakenAdapter(fantakenModels);
        adapter.setFansTakenListener(new OnFantakenClickListener() {
            @Override
            public void onFantakenClick(String id) {
                Intent welcome = new Intent(FantakenListActivity.this,FantakenDetailActivity.class);
                welcome.putExtra("id",id);
                startActivity(welcome);
            }
        });
        recyclerView.setAdapter(adapter);

        db.collection("fantakens")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("fantaken", document.getId() + " => " + document.getData());
                                FantakenModel fantakenModel = new FantakenModel(document.getId(),document.getData());
                                fantakenModels.add(fantakenModel);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.w("fantaken", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}