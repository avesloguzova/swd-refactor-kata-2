package task2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoStoreTest
{

    @Before
    public void setUp ()  {
        customer = new Customer("Fred");
    }

    @Test
    public void testSingleNewReleaseStatement () {
        customer.addRental(new Movie("The Cell", PriceCode.NEW_RELEASE), 3);
        assertEquals ("Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n", customer.statement ());
    }

    @Test
    public void testDualNewReleaseStatement () {
        customer.addRental(new Movie("The Cell", PriceCode.NEW_RELEASE), 3);
        customer.addRental(new Movie("The Tigger Movie", PriceCode.NEW_RELEASE), 3);
        assertEquals ("Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n", customer.statement ());
    }

    @Test
    public void testSingleChildrensStatement () {
        customer.addRental(new Movie("The Tigger Movie", PriceCode.CHILDRENS), 3);
        assertEquals ("Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n", customer.statement ());
    }

    @Test
    public void testMultipleRegularStatement () {
        customer.addRental(new Movie("Plan 9 from Outer Space", PriceCode.REGULAR), 1);
        customer.addRental(new Movie("8 1/2", PriceCode.REGULAR), 2);
        customer.addRental(new Movie("Eraserhead", PriceCode.REGULAR), 3);

        assertEquals ("Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n", customer.statement ());
    }

    private Customer customer;
}
