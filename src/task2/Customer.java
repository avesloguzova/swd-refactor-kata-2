package task2;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private List<Rental> rentals = new ArrayList<>();
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int days) {
        rentals.add(new Rental(movie, days));
    }


    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        StringBuilder resultBuilder = new StringBuilder();
        appendHeader(resultBuilder);

        for (Rental rental : rentals) {
            double amount = rental.getAmount();

            appendRentalInfo(resultBuilder, rental.getMovie().getTitle(), amount);

            frequentRenterPoints += rental.getFrequentRenterPoints();
            totalAmount += amount;
        }
        appendTotalInfo(totalAmount, frequentRenterPoints, resultBuilder);

        return resultBuilder.toString();
    }

    public void appendTotalInfo(double totalAmount, int frequentRenterPoints, StringBuilder resultBuilder) {
        resultBuilder.append("You owed ").append(String.valueOf(totalAmount)).append(System.lineSeparator());
        resultBuilder.append("You earned ").append(String.valueOf(frequentRenterPoints));
        resultBuilder.append(" frequent renter points").append(System.lineSeparator());
    }

    public void appendRentalInfo(StringBuilder resultBuilder, String movieTitle, double amount) {
        resultBuilder.append('\t').append(movieTitle).append('\t');
        resultBuilder.append(String.valueOf(amount)).append(System.lineSeparator());
    }

    public void appendHeader(StringBuilder resultBuilder) {
        resultBuilder.append("Rental Record for ").append(name).append('\n');
    }


}
