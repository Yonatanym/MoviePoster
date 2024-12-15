package com.example.poster;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PostersListener {

    // Declare a button to add posters to the watchlist
    private Button buttonAddToWatchlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable edge-to-edge display to allow the content to extend to the edges of the screen
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Set an OnApplyWindowInsetsListener to handle insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Initialize RecyclerView for displaying posters

        RecyclerView postersRecyclerView = findViewById(R.id.postersRecyclerView);
        // Initialize the button to add posters to the watchlist
        buttonAddToWatchlist = findViewById(R.id.buttonAddToWatchlist);

        //prepare data

        List<Poster> posterList = new ArrayList<>();
        Poster the100 = new Poster();
        the100.image = R.drawable.image3;
        the100.name = "vietnamMovie";
        the100.createdBy = "ABC";
        the100.rating = 5f;
        the100.story = "hhu";
        posterList.add(the100);

        // Create a Poster object for the Japanese movie
        Poster japaneseMovie = new Poster();
        japaneseMovie.image = R.drawable.image1;
        japaneseMovie.name = "j Movie";
        japaneseMovie.createdBy = "ABC";
        japaneseMovie.rating = 5f;
        japaneseMovie.story = "hhu";
        posterList.add(japaneseMovie);

        // Create a Poster object for the American movie
        Poster americanMovie = new Poster();
        americanMovie.image = R.drawable.image2;
        americanMovie.name = "A Movie";
        americanMovie.createdBy = "ABC";
        americanMovie.rating = 5f;
        americanMovie.story = "hhu";
        posterList.add(americanMovie);


    }

    //private List<Poster> posterList;
    final PosterAdapter posterAdapter = new PosterAdapter(posterList, this);

    postersRecyclerView.setAdapter(posterAdapter);
    //.setAdapter(posterAdapter);

    buttonAddToWatchlist.setOnCLickListener(new View.OnClickListener()

    {

        @Override

        public void onClick (View v){

            // Get the list of selected posters from the adapter
        List<Poster> selectPosters = posterAdapter.getSelectedPosters();

        StringBuilder posterNames = new StringBuilder();

            // Loop through the selected posters and append their names to the StringBuilder
        for (int i = 0; i < selectPosters.size(); i++) {

            if (i == 0) {

                posterNames.append(selectPosters.get(i).name);

            } else {

                posterNames.append("\n").append(selectPosters.get(i).name);
            }
        }

            // Show a Toast message with the names of the selected posters
        Toast.makeText(MainActivity.this, posterNames.toString(), Toast.LENGTH_SHORT).show();

    }
    });
}

    // Implement the onPosterAction method from the PostersListener interface

    @Override
    public void onPosterAction(Boolean isSelected) {

        // If a poster is selected, show the "Add to Watchlist" button
        if(isSelected) {

        buttonAddToWatchlist.setVisibility(View.VISIBLE);

    }
    else{
    buttonAddToWatchlist.setVisibility(View.GONE);

    }
    }
}
