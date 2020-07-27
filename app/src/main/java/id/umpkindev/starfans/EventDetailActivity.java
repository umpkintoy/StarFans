package id.umpkindev.starfans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.umpkindev.starfans.models.EventModel;

public class EventDetailActivity extends AppCompatActivity {

    private static String TAG = "EventDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        final TextView title = findViewById(R.id.event_title);
        final TextView description = findViewById(R.id.event_description);
        final LinearLayout posterlist = findViewById(R.id.poster_layout);

        //List<String> eventurl = new ArrayList<>();
        //eventurl.add("https://cdn.discordapp.com/attachments/598894928063365149/736614118118785105/event2.jpg");
        //eventurl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/EVENT%203.jpg?alt=media&token=6cb42f65-94d8-40ba-b3bb-97e103f0326d");
        //eventurl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/EVENT%204.jpg?alt=media&token=e0fbf333-10d3-4e49-ab24-72138909f78a");
        //eventurl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/RENJUN%20EVENT%201.jpg?alt=media&token=a055b5a2-abb5-489a-87fd-c2d5debd2d9e");


        DocumentReference docRef = db.collection("events").document(id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    EventModel eventModel = new EventModel(document.getId(),document.getData());
                    //fungsi untuk loop gambar event yang akan dimasukan ke Horizontal view (karena jumlah gambar tidak tentu)
                    for (int i = 0; i < eventModel.getImages().size();i++) {
                        ImageView imageView = new ImageView(getApplicationContext());
                        Picasso.get()
                                .load(eventModel.getImages().get(i))
                                .placeholder(R.drawable.event1)
                                .into(imageView);
                        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                        posterlist.addView(imageView);
                    }

                    title.setText(eventModel.getTitle());
                    description.setText(eventModel.getDescription());

                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }
}