package com.greatersum.rental;

import java.util.List;

class Customer {
    private final String name;
    private final List<MovieRental> rentals;

    Customer(String name, List<MovieRental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    String getName() {
        return name;
    }

    List<MovieRental> getRentals() {
        return rentals;
    }
}
