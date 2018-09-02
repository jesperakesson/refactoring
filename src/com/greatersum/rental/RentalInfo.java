package com.greatersum.rental;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

class RentalInfo {

    public enum Classification {
        NEW,
        REGULAR,
        CHILDREN
    }

    private final static HashMap<String, Movie> movies = new HashMap<>();

    static {
        movies.put("F001", new Movie("Ran", Classification.REGULAR));
        movies.put("F002", new Movie("Trois Couleurs: Bleu", Classification.REGULAR));
        movies.put("F003", new Movie("Cars 2", Classification.CHILDREN));
        movies.put("F004", new Movie("Latest Hit Release", Classification.NEW));
    }

    static String createStatementOnRentals(String customersName, List<MovieRental> customersRentals) {
        BigDecimal totalAmount = BigDecimal.valueOf(0);
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + customersName + "\n");

        for (MovieRental rental : customersRentals) {
            Movie movie = movies.get(rental.getMovieId());

            //add frequent renter points
            frequentRenterPoints = frequentRenterPoints + getRentalPointsByClassificationAndDays(movie.getClassification(), rental.getDays());

            BigDecimal amountForMovie = getAmountByClassificationAndRentalDays(movie.getClassification(), rental.getDays());

            //print figures for this rental
            result.append("\t").append(movie.getTitle()).append("\t").append(amountForMovie).append("\n");
            totalAmount = totalAmount.add(amountForMovie);
        }
        // add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n");

        return result.toString();
    }


    static BigDecimal getAmountByClassificationAndRentalDays(Classification classification, int rentalDays) {
        BigDecimal amount = BigDecimal.valueOf(0);

        // determine amount for each movie
        switch (classification) {
            case REGULAR:
                amount = BigDecimal.valueOf(2);
                if (rentalDays > 2) {
                    amount = BigDecimal.valueOf((rentalDays - 2) * 1.5).add(amount);
                }
                break;
            case NEW:
                amount = BigDecimal.valueOf(rentalDays * 3);
                break;
            case CHILDREN:
                amount = BigDecimal.valueOf(1.5);
                if (rentalDays > 3) {
                    amount = BigDecimal.valueOf((rentalDays - 3) * 1.5).add(amount);
                }
                break;
        }
        return amount;
    }

    static int getRentalPointsByClassificationAndDays(Classification classification, int days) {
        int points = 1;
        // add bonus for a two day new release rental
        if (classification.equals(RentalInfo.Classification.NEW) && days > 2) points++;
        return points;
    }
}
