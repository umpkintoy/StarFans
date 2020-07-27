package id.umpkindev.starfans;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FantakenDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fantaken_detail);

        TextView fantakenTitle = findViewById(R.id.fantaken_title);
       TextView fantakenDescription = findViewById(R.id.fantaken_description);
        LinearLayout fantakenLayout = findViewById(R.id.fantaken_layout);

        List<String>fantakenturl = new ArrayList<>();
        fantakenturl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/BabyBeanie0323_191115.jpg?alt=media&token=2950840b-cbae-42f9-80df-601d024c5284");
        fantakenturl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/BabyBeanie0323_191116.jpg?alt=media&token=862b1c07-9514-4a49-bbf3-5a792096e320");
        fantakenturl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/BabyBeanie0323_1911162.jpg?alt=media&token=a1da9613-c598-464a-abd5-5862892a61aa");
        fantakenturl.add("https://firebasestorage.googleapis.com/v0/b/starfans-3ca51.appspot.com/o/BabyBeanie0323_1911162%3D5.jpg?alt=media&token=331d6381-5dc7-476e-a208-b518aa7def8e");


        //fungsi untuk loop gambar event yang akan dimasukan ke Horizontal view (karena jumlah gambar tidak tentu)
        for (int i = 0; i < fantakenturl.size();i++) {
            ImageView imageView = new ImageView(this);
            Picasso.get()
                    .load(fantakenturl.get(i))
                    .placeholder(R.drawable.fantaken1)
                    .into(imageView);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ScrollView.LayoutParams.WRAP_CONTENT,ScrollView.LayoutParams.WRAP_CONTENT));
            //imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            fantakenLayout.addView(imageView);
        }

        fantakenTitle.setText("IEU JUDUL");
        fantakenDescription.setText("IEU JJJJJJJ");
    }
}