package com.smart.pay.adapter.movie;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.pay.R;
import com.smart.pay.activity.wallet.MovieDetailView;
import com.smart.pay.models.output.MovieListOutput;
import com.smart.pay.views.MyTextView;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    ArrayList<MovieListOutput> movieListOutputArrayList;
    AppCompatActivity appCompatActivity;

    public MovieListAdapter(ArrayList<MovieListOutput> movieListOutputArrayList, AppCompatActivity appCompatActivity) {
        this.movieListOutputArrayList = movieListOutputArrayList;
        this.appCompatActivity = appCompatActivity;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout movieImage;
        public MyTextView movieName;
        public MyTextView movieType;
        public MyTextView btnMovieBookNow;

        public MyViewHolder(View view) {
            super(view);

            movieImage = (RelativeLayout) view.findViewById(R.id.movieImage);
            movieName = (MyTextView) view.findViewById(R.id.movieName);
            movieType = (MyTextView) view.findViewById(R.id.movieType);
            btnMovieBookNow = (MyTextView) view.findViewById(R.id.btnMovieBookNow);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movies_list_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        MovieListOutput movieListOutput = movieListOutputArrayList.get(position);


        holder.movieName.setText(movieListOutput.getMovieName());
        holder.movieType.setText(movieListOutput.getMovieType());
        holder.movieImage.setBackgroundResource(movieListOutput.getMovieImage());

        holder.btnMovieBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newIntent = new Intent(appCompatActivity, MovieDetailView.class);
                appCompatActivity.startActivity(newIntent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return movieListOutputArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}
