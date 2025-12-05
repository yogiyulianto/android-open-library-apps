package com.example.openlibraryapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.openlibraryapps.R;
import com.example.openlibraryapps.model.Work;
import com.example.openlibraryapps.ui.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Work> bookList = new ArrayList<>();
    private Context context;

    public BookAdapter(Context ctx) {
        this.context = ctx;
    }

    public void setData(List<Work> list) {
        this.bookList = list;
        notifyDataSetChanged();
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Work book = bookList.get(position);

        holder.tvTitle.setText(book.title);
        holder.tvAuthor.setText(
                book.authors != null && book.authors.size() > 0 ? book.authors.get(0).name : "Unknown"
        );
        holder.tvYear.setText(String.valueOf(book.first_publish_year));

        if (book.cover_id > 0) {
            String coverUrl = "https://covers.openlibrary.org/b/id/" + book.cover_id + "-M.jpg";
            Picasso.get().load(coverUrl).into(holder.imgCover);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, DetailActivity.class);
            i.putExtra("title", book.title);
            i.putExtra("author", book.authors.get(0).name);
            i.putExtra("year", book.first_publish_year);
            i.putExtra("cover_id", book.cover_id);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvAuthor, tvYear;
        ImageView imgCover;

        public BookViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvYear = itemView.findViewById(R.id.tvYear);
            imgCover = itemView.findViewById(R.id.imgCover);
        }
    }
}
