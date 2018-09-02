package com.greatersum.rental;

class Movie {
    private final String title;
    private final RentalInfo.Classification classification;

    Movie(String title, RentalInfo.Classification classification) {
        this.title = title;
        this.classification = classification;
    }

    String getTitle() {
        return title;
    }

    RentalInfo.Classification getClassification() {
        return classification;
    }
}
