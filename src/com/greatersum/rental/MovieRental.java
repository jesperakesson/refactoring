package com.greatersum.rental;

class MovieRental {
    private final String movieId;
    private final int days;

    MovieRental(String movieId, int days) {
        this.movieId = movieId;
        this.days = days;
    }

    String getMovieId() {
        return movieId;
    }

    int getDays() {
        return days;
    }
}
