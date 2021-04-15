package wsVideoStore;

// import junit.framework.TestCase;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class VideoStoreTest  {
  private RentalStatement statement;
  private Movie newRelease1;
  private Movie newRelease2;
  private Movie childrens;
  private Movie regular1;
  private Movie regular2;
  private Movie regular3;


  @Before public void setUp() {
    statement = new RentalStatement("Customer Name");
    newRelease1 = new NewReleaseMovie("New Release 1");
    newRelease2 = new NewReleaseMovie("New Release 2");
    childrens = new ChildrensMovie("Childrens");
    regular1 = new RegularMovie("Regular 1");
    regular2 = new RegularMovie("Regular 2");
    regular3 = new RegularMovie("Regular 3");
  }

  private void assertAmountAndPointsForReport(double expectedAmount, int expectedPoints) {

    assertTrue(expectedAmount==statement.getAmountOwed());
    assertTrue(expectedPoints==statement.getFrequentRenterPoints());
    
//  assertEquals(expectedAmount, statement.getAmountOwed());
//  assertEquals(expectedPoints, statement.getFrequentRenterPoints());
  }

  @Test public void testSingleNewReleaseStatement() {
    statement.addRental(new Rental(newRelease1, 3));
    statement.makeRentalStatement();
    assertAmountAndPointsForReport(9.0, 2);
  }

  @Test public void testDualNewReleaseStatement() {
    statement.addRental(new Rental(newRelease1, 3));
    statement.addRental(new Rental(newRelease2, 3));
    statement.makeRentalStatement();
    assertAmountAndPointsForReport(18.0, 4);
  }

  @Test public void testSingleChildrensStatement() {
    statement.addRental(new Rental(childrens, 3));
    statement.makeRentalStatement();
    assertAmountAndPointsForReport(1.5, 1);
  }


  @Test public void testMultipleRegularStatement() {
    statement.addRental(new Rental(regular1, 1));
    statement.addRental(new Rental(regular2, 2));
    statement.addRental(new Rental(regular3, 3));
    statement.makeRentalStatement();
    assertAmountAndPointsForReport(7.5, 3);
  }

  @Test public void testRentalStatementFormat() {
    statement.addRental(new Rental(regular1, 1));
    statement.addRental(new Rental(regular2, 2));
    statement.addRental(new Rental(regular3, 3));

    assertEquals(
      "Rental Record for Customer Name\n" +
        "\tRegular 1\t2.0\n" +
        "\tRegular 2\t2.0\n" +
        "\tRegular 3\t3.5\n" +
        "You owed 7.5\n" +
        "You earned 3 frequent renter points\n",
      statement.makeRentalStatement());
  }
}