package com.example.openlibraryapps.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.openlibraryapps.R;
import com.example.openlibraryapps.adapter.BookAdapter;
import com.example.openlibraryapps.api.BookApiService;
import com.example.openlibraryapps.model.ResponseModel;
import com.example.openlibraryapps.model.Work;
import com.example.openlibraryapps.util.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvBooks;
    private ProgressBar progressBar;
    private BookAdapter adapter;
    private BookApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvBooks = findViewById(R.id.rvBooks);
        progressBar = findViewById(R.id.progressBar);

        rvBooks.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BookAdapter(this);
        rvBooks.setAdapter(adapter);

        apiService = RetrofitClient.getClient().create(BookApiService.class);

        loadBooks();
    }

    private void loadBooks() {
        progressBar.setVisibility(View.VISIBLE);

        Call<ResponseModel> call = apiService.getSportBooks(false);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    List<Work> books = response.body().works;
                    adapter.setData(books);
                } else {
                    Toast.makeText(MainActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                    Log.e("API_ERROR", "Response tidak valid");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API_ERROR", t.getMessage());
            }
        });
    }
}
