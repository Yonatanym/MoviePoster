package com.example.poster;


import android.view.LayoutInflater;
import android. view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;


import java.util.ArrayList;
import java.util.List;


public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {
    @NonNull
    @Override

    // The method to create a new ViewHolder for the poster items
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout (poster item) and return a new PosterViewHolder
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    // Binds the data (Poster object) to the view holder at a given position
    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPosters(posterList.get(position));
    }
    // Returns the total number of items in the poster list
    @Override
    public int getItemCount() {
        return posterList.size();
    }
// List to hold the poster data
    private List<Poster> posterList;
    // Constructor to initialize the adapter with a list of posters and a listener
    public PosterAdapter(List<Poster> posterList, PostersListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }



// Listener to handle poster selection actions
    private PostersListener postersListener;
// Method to get a list of selected posters
    public List<Poster> getSelectedPosters() {

        List<Poster> selectedPosters = new ArrayList<>();

        for (Poster poster : this.posterList) {

            if (poster.isSelected) {
                selectedPosters.add(poster);
            }
        }

        return selectedPosters;
    }

// ViewHolder class to hold and manage the individual poster views
    class PosterViewHolder extends RecyclerView.ViewHolder {

// Declare UI elements that will be used to display the poster data
        ConstraintLayout layoutPosters;


        View viewBackground;

        RoundedImageView imagePoster;


        TextView TextName , TextCreatedBy , TextStory;


        RatingBar ratingBar;


        ImageView imageSelected;

// Constructor to initialize the view holder and its associated views
        public PosterViewHolder(@NonNull View itemView) {

            super(itemView);

            layoutPosters = itemView.findViewById(R.id.layoutposters);

            viewBackground = itemView.findViewById(R.id.viewBackground);

            imagePoster = itemView.findViewById(R.id.imagePosters);

            TextName = itemView.findViewById(R.id.TextName);

            TextCreatedBy = itemView.findViewById(R.id.TextCreateBy);

            TextStory = itemView.findViewById(R.id.TextStory);

            ratingBar = itemView.findViewById(R.id.ratingBar);

            imageSelected = itemView.findViewById(R.id.imageSelected);
        }
// Method to bind the data of the Poster object to the view elements
        void bindPosters (final Poster poster) {
// Set the image, text, and rating based on the current poster data
            imagePoster.setImageResource(poster.image);
            TextName.setText(poster.name);

            TextCreatedBy.setText(poster.createdBy);

            TextStory.setText(poster.story);
            ratingBar.setRating(poster.rating);
// Change the background and visibility of the selection indicator based on
            if (poster.isSelected) {

                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);


            } else {

                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);    // Hide selection indicator
            }
            //LayoutPosters
            // Set an OnClickListener to handle poster selection/deselection
            layoutPosters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // If the poster is already selected, deselect it
                    if (poster.isSelected) {
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                         // Check if any posters are selected, and update the listener
                        if (getSelectedPosters().isEmpty()) {
                            postersListener.onPosterAction( false);
                        } else
                             // Update background and indicator for selected state
                            viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = true;    // Update selection status to true

                        postersListener.onPosterAction(true);

                    }
                }
            });

        }}
}
