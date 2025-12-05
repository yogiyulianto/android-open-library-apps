package com.example.openlibraryapps.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.openlibraryapps.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgCoverDetail;
    private TextView tvTitleDetail, tvAuthorDetail, tvYearDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgCoverDetail = findViewById(R.id.imgCoverDetail);
        tvTitleDetail = findViewById(R.id.tvTitleDetail);
        tvAuthorDetail = findViewById(R.id.tvAuthorDetail);
        tvYearDetail = findViewById(R.id.tvYearDetail);

        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        int year = getIntent().getIntExtra("year", 0);
        int coverId = getIntent().getIntExtra("cover_id", 0);

        tvTitleDetail.setText(title);
        tvAuthorDetail.setText(author);
        tvYearDetail.setText(String.valueOf(year));

        if (coverId > 0) {
            String coverUrl = "https://covers.openlibrary.org/b/id/" + coverId + "-L.jpg";
            Picasso.get().load(coverUrl).into(imgCoverDetail);
        }
    }
}
