import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Band firstBand = new Band("Sigur Ros");
    Band secondBand = new Band("Sigur Ros");
    assertTrue(firstBand.equals(secondBand));
  }

  @Test
  public void save_addsInstanceOfBandToDatabase() {
    Band newBand = new Band("Ecco");
    newBand.save();
    Band savedBand = Band.all().get(0);
    assertTrue(newBand.equals(savedBand));
  }

  @Test
  public void save_assignsIdToObject() {
    Band newBand = new Band("Elliott Smith");
    newBand.save();
    Band savedBand = Band.all().get(0);
    assertEquals(newBand.getId(), savedBand.getId());
  }

  @Test
  public void find_locatesAllInstancesOfClassInDatabaseUsingId() {
    Band newBand = new Band("The Misfits");
    newBand.save();
    Band savedBand = Band.find(newBand.getId());
    assertTrue(newBand.equals(savedBand));
  }

  @Test
  public void updateName_updatesNameOfObject() {
    Band newBand = new Band("Mogwai");
    newBand.save();
    newBand.updateName("Mono");
    assertEquals(Band.all().get(0).getName(), ("Mono"));
  }
  @Test
  public void deleteBand() {
    Band newBand = new Band("Bon Iver");
    newBand.save();
    newBand.delete();
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void addVenue_addsVenueToRecipe() {
    Band newBand = new Band("The Smiths");
    newBand.save();

    Venue newVenue = new Venue("Moda Center");
    newVenue.save();

    newBand.addVenue(newVenue);
    Venue savedVenue = newBand.getVenues().get(0);
    assertTrue(newVenue.equals(savedVenue));
  }

  @Test
  public void getVenues_getsBandsVenuesByBandID() {
    Band newBand = new Band("Young Thug");
    newBand.save();

    Venue newVenue = new Venue("Roseland");
    newVenue.save();

    newBand.addVenue(newVenue);
    List savedVenues = newBand.getVenues();
    assertEquals(savedVenues.size(), 1);
  }

  @Test
  public void deleteAll_deletesAllBandsAndBandVenues() {
      Band firstBand = new Band("Black Sabbath");
      Band secondBand = new Band("Air");
      firstBand.save();
      secondBand.save();
      Venue firstBandVenue = new Venue("Crystal");
      firstBand.addVenue(firstBandVenue);
      Band.deleteAll();
      assertEquals(Band.all().size(), 0);
  }
  //
  // @Test
  // public void deleteAll_deletesRecipeTagAssociations () {
  //     Recipe firstRecipe = new Recipe("BLT");
  //     firstRecipe.save();
  //     int recipeId = firstRecipe.getId();
  //     Tag firstRecipeTag = new Tag("firstTag");
  //     firstRecipe.addTag(firstRecipeTag);
  //     Recipe.deleteAll();
  //     assertEquals(firstRecipeTag.getRecipes().size(), 0);
  // }
}
