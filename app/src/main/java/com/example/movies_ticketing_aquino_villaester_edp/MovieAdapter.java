package com.example.movies_ticketing_aquino_villaester_edp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final List<MovieItem> movieList;
    private Context context;


    public interface OnItemClickListener {
        void onItemClick(MovieItem movieItem);
    }

    public MovieAdapter(List<MovieItem> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(v);
    }

    public void onBindViewHolder(@NonNull MovieViewHolder holder, int pos) {
        MovieItem movieItem = movieList.get(pos);

        holder.textview.setText(movieItem.getTitleAndYear());
        holder.imageview.setImageResource(movieItem.getImageId());
        holder.blurImageView.setImageResource(movieItem.getBlurImageId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SeatsActivity.class);
            intent.putExtra("title", movieItem.getTitleAndYear());
            intent.putExtra("imageID", movieItem.getImageId());
            context.startActivity(intent);
            Toast.makeText(context, movieItem.getTitleAndYear(), Toast.LENGTH_LONG).show();
            Toast.makeText(context, movieItem.getImageId(), Toast.LENGTH_LONG).show();
        });
    }

    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview;
        ImageView blurImageView;
        TextView textview;

        public MovieViewHolder(@NonNull View itemview) {
            super(itemview);
            imageview = itemview.findViewById(R.id.movie_banner);
            textview = itemview.findViewById(R.id.movie_title);
            blurImageView = itemview.findViewById(R.id.blur_image);
        }
    }
}