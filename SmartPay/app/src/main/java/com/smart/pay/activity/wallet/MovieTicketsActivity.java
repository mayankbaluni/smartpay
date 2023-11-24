package com.smart.pay.activity.wallet;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.smart.pay.R;
import com.smart.pay.adapter.movie.MovieListAdapter;
import com.smart.pay.models.output.MovieListOutput;
import com.smart.pay.views.ChildAnimationExample;
import com.smart.pay.views.MyTextView;
import com.smart.pay.views.SliderLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieTicketsActivity extends AppCompatActivity {

    SliderLayout movies_banner;
    PagerIndicator custom_indicator_movies;
    HashMap<String, Integer> file_maps = new HashMap<String, Integer>();

    RecyclerView movies_list;

    ArrayList<MovieListOutput> movieListOutputArrayList;
    MovieListAdapter movieListAdapter;

    MyTextView movieAll, movieHindi, movieEnglish, movieMarathi, movieTelgu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.movie_tickets_activity);
        setupActionBar();

        movies_banner = (SliderLayout) findViewById(R.id.movies_banner);
        custom_indicator_movies = (PagerIndicator) findViewById(R.id.custom_indicator_movies);
        movies_list = (RecyclerView) findViewById(R.id.movies_list);


        file_maps.put("Movie 1", R.drawable.movie_banner_1);
        file_maps.put("Movie 2", R.drawable.movie_banner_2);
        file_maps.put("Movie 3", R.drawable.movie_banner_3);


        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(MovieTicketsActivity.this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterInside)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {


                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);

            movies_banner.addSlider(textSliderView);

            textSliderView.bundle(new Bundle());

            movies_banner.addSlider(textSliderView);


        }

        movies_banner.setPresetTransformer(SliderLayout.Transformer.Default);
        movies_banner.setCustomIndicator(custom_indicator_movies);
//                            mImageSlider.setCurrentPosition(-1);
        //     mImageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        movies_banner.setPadding(0, 0, 0, 5);
        movies_banner.setCustomAnimation(new ChildAnimationExample());


        movieListOutputArrayList = new ArrayList<MovieListOutput>();

        MovieListOutput movieListOutput1 = new MovieListOutput();

        movieListOutput1.setMovieId("1");
        movieListOutput1.setMovieName("Dream Girl");
        movieListOutput1.setMovieType("Hindi| (U/A)");
        movieListOutput1.setMovieImage(R.drawable.list_movie1);


        MovieListOutput movieListOutput2 = new MovieListOutput();

        movieListOutput2.setMovieId("2");
        movieListOutput2.setMovieName("Chhichore");
        movieListOutput2.setMovieType("Hindi| (U/A)");
        movieListOutput2.setMovieImage(R.drawable.list_movie2);

        MovieListOutput movieListOutput3 = new MovieListOutput();

        movieListOutput3.setMovieId("3");
        movieListOutput3.setMovieName("Section 375");
        movieListOutput3.setMovieType("Hindi| (U/A)");
        movieListOutput3.setMovieImage(R.drawable.list_movie3);


        MovieListOutput movieListOutput4 = new MovieListOutput();

        movieListOutput4.setMovieId("4");
        movieListOutput4.setMovieName("Pehlwaan");
        movieListOutput4.setMovieType("Hindi| (U/A)");
        movieListOutput4.setMovieImage(R.drawable.list_movie4);

        MovieListOutput movieListOutput5 = new MovieListOutput();

        movieListOutput5.setMovieId("5");
        movieListOutput5.setMovieName("Saaho");
        movieListOutput5.setMovieType("Hindi| (U/A)");
        movieListOutput5.setMovieImage(R.drawable.list_movie5);


        MovieListOutput movieListOutput6 = new MovieListOutput();

        movieListOutput6.setMovieId("6");
        movieListOutput6.setMovieName("Mission Mangal");
        movieListOutput6.setMovieType("Hindi| (U/A)");
        movieListOutput6.setMovieImage(R.drawable.list_movie6);


        movieListOutputArrayList.add(movieListOutput1);
        movieListOutputArrayList.add(movieListOutput2);
        movieListOutputArrayList.add(movieListOutput3);
        movieListOutputArrayList.add(movieListOutput4);
        movieListOutputArrayList.add(movieListOutput5);
        movieListOutputArrayList.add(movieListOutput6);


        movieListAdapter = new MovieListAdapter(movieListOutputArrayList, MovieTicketsActivity.this);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(MovieTicketsActivity.this, RecyclerView.VERTICAL, false);

        movies_list.setLayoutManager(layoutManager);

        movies_list.setItemAnimator(new DefaultItemAnimator());
        movies_list.setAdapter(movieListAdapter);

        movieListAdapter.notifyDataSetChanged();


        movieAll = (MyTextView) findViewById(R.id.movieAll);
        movieEnglish = (MyTextView) findViewById(R.id.movieEnglish);
        movieHindi = (MyTextView) findViewById(R.id.movieHindi);
        movieMarathi = (MyTextView) findViewById(R.id.movieMarathi);
        movieTelgu = (MyTextView) findViewById(R.id.movieTelgu);


        movieAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                movieListAdapter = new MovieListAdapter(movieListOutputArrayList, MovieTicketsActivity.this);

                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(MovieTicketsActivity.this, RecyclerView.VERTICAL, false);

                movies_list.setLayoutManager(layoutManager);

                movies_list.setItemAnimator(new DefaultItemAnimator());
                movies_list.setAdapter(movieListAdapter);

                movieListAdapter.notifyDataSetChanged();


            }
        });

        movieEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                movieListAdapter = new MovieListAdapter(movieListOutputArrayList, MovieTicketsActivity.this);

                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(MovieTicketsActivity.this, RecyclerView.VERTICAL, false);

                movies_list.setLayoutManager(layoutManager);

                movies_list.setItemAnimator(new DefaultItemAnimator());
                movies_list.setAdapter(movieListAdapter);

                movieListAdapter.notifyDataSetChanged();

            }
        });

        movieHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                movieListAdapter = new MovieListAdapter(movieListOutputArrayList, MovieTicketsActivity.this);

                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(MovieTicketsActivity.this, RecyclerView.VERTICAL, false);

                movies_list.setLayoutManager(layoutManager);

                movies_list.setItemAnimator(new DefaultItemAnimator());
                movies_list.setAdapter(movieListAdapter);

                movieListAdapter.notifyDataSetChanged();

            }
        });

        movieMarathi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                movieListAdapter = new MovieListAdapter(movieListOutputArrayList, MovieTicketsActivity.this);

                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(MovieTicketsActivity.this, RecyclerView.VERTICAL, false);

                movies_list.setLayoutManager(layoutManager);

                movies_list.setItemAnimator(new DefaultItemAnimator());
                movies_list.setAdapter(movieListAdapter);

                movieListAdapter.notifyDataSetChanged();

            }
        });

        movieTelgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                movieListAdapter = new MovieListAdapter(movieListOutputArrayList, MovieTicketsActivity.this);

                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(MovieTicketsActivity.this, RecyclerView.VERTICAL, false);

                movies_list.setLayoutManager(layoutManager);

                movies_list.setItemAnimator(new DefaultItemAnimator());
                movies_list.setAdapter(movieListAdapter);

                movieListAdapter.notifyDataSetChanged();

            }
        });

    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView menu_icon = toolbar.findViewById(R.id.menu_icon);
        menu_icon.setVisibility(View.GONE);


        TextView toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);
        toolbarTitle.setVisibility(View.VISIBLE);

        toolbarTitle.setText("Movie Tickets");

        ActionBar bar = getSupportActionBar();
        bar.setDisplayUseLogoEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // action bar menu behaviour
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
