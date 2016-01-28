package task2;

/**
 * Created by av on 27/01/16.
 */
public enum PriceCode {
    CHILDRENS {
        @Override
        double price(int daysRented) {
            double price = 1.5;
            if (daysRented > 3) price += (daysRented - 3) * 1.5;
            return price;
        }
    }, REGULAR {
        @Override
        double price(int daysRented) {
            double price = 2;
            if (daysRented > 2) price += (daysRented - 2) * 1.5;
            return price;
        }
    }, NEW_RELEASE {
        @Override
        double price(int daysRented) {
            return daysRented * 3;
        }

        @Override
        int frequentRenterPoints(int daysRented) {
            int bonus = super.frequentRenterPoints(daysRented);
            if (daysRented > 1) bonus++;
            return bonus;
        }
    };

    abstract double price(int daysRented);

    int frequentRenterPoints(int daysRented) {
        return 1;
    }
}