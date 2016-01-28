package task2;

public class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getAmount() {
        return movie.getPriceCode().price(daysRented);
    }


    public int getFrequentRenterPoints() {
        return movie.getPriceCode().frequentRenterPoints(daysRented);
    }
}
