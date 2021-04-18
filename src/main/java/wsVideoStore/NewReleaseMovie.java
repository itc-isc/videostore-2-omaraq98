package wsVideoStore;

public class NewReleaseMovie extends Movie {
  public NewReleaseMovie(String title) {
    super(title);
    setPriceCode(Movie.NEW_RELEASE);
  }

  public double determineAmount(int daysRented) {
    return daysRented * 3.0;
  }

  public int determineFrequentRenterPoints(int daysRented) {
    return (daysRented > 1) ? 2 : 1;
  }
}
