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
        double thisAmount = 0;
        // determines the amount for rental line
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR:
                return getRegularAmount();
            case Movie.NEW_RELEASE:
                return getNewReleaseAmount();
            case Movie.CHILDRENS:
                return getChildrensAmount();
        }
        return thisAmount;
    }

    public double getChildrensAmount() {
        double thisAmount = 1.5;
        if (daysRented > 3)
            thisAmount += (daysRented - 3) * 1.5;
        return thisAmount;
    }

    public double getNewReleaseAmount() {
        return daysRented * 3.0;
    }

    public double getRegularAmount() {
        double thisAmount = 2;
        if (daysRented > 2)
            thisAmount += (daysRented - 2) * 1.5;
        return thisAmount;
    }

    public int getFrequentRenterPoints() {
        return (movie.getPriceCode() == Movie.NEW_RELEASE && daysRented > 1) ? 2 : 1;
    }
}
