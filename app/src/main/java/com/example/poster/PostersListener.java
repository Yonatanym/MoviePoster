package com.example.poster;

public interface PostersListener {
     // Method to notify when a poster is selected or deselected
    // The boolean 'isSelected' indicates the selection state of the poster
    void onPosterAction(Boolean isSelected);


}
