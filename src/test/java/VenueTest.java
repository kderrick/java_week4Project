import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfTitlesAreTheSame() {
    Venue firstVenue = new Venue("Crystal");
    Venue secondVenue = new Venue("Crystal");
    assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_addsInstanceOfVenueToDatabase() {
    Venue newVenue = new Venue("Roseland");
    newVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertTrue(newVenue.equals(savedVenue));
  }

  @Test
  public void save_assignsIdToObject() {
    Venue newVenue = new Venue("Star");
    newVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertEquals(newVenue.getId(), savedVenue.getId());
  }

  @Test
  public void find_locatesAllInstancesOfClassInDatabaseUsingId() {
    Venue newVenue = new Venue("Wonder");
    newVenue.save();
    Venue savedVenue = Venue.find(newVenue.getId());
    assertTrue(newVenue.equals(savedVenue));
  }

  @Test
  public void update_updatesNameOfObject() {
    Venue newVenue = new Venue("Wonder");
    newVenue.save();
    newVenue.update("Berbatis");
    assertEquals(Venue.all().get(0).getName(), ("Berbatis"));
  }

  @Test
  public void delete_deleteVenueObject() {
    Venue newVenue = new Venue("Wonder");
    newVenue.save();
    newVenue.delete();
    assertEquals(Venue.all().size(), 0);
  }
  //
  // @Test
  // public void addRecipe_addsRecipeToTag() {
  //   Tag newTag = new Tag("Mexican");
  //   newTag.save();
  //
  //   Recipe newRecipe = new Recipe("Enchilada");
  //   newRecipe.save();
  //
  //   newTag.addRecipe(newRecipe);
  //   Recipe savedRecipe = newTag.getRecipes().get(0);
  //   assertTrue(newRecipe.equals(savedRecipe));
  // }
  //
  // @Test
  // public void getRecipe_getsTagRecipeByTagID() {
  //   Tag newTag = new Tag("Mexican");
  //   newTag.save();
  //
  //   Recipe newRecipe = new Recipe("Tacos");
  //   newRecipe.save();
  //
  //   newTag.addRecipe(newRecipe);
  //   List savedRecipe = newTag.getRecipes();
  //   assertEquals(savedRecipe.size(), 1);
  // }
  //
  // @Test
  // public void deleteAll_deletesAllTags() {
  //     Tag firstTag = new Tag("BLT");
  //     Tag secondTag = new Tag("Taco");
  //     firstTag.save();
  //     secondTag.save();
  //     Recipe firstTagRecipe = new Recipe("firstRecipe");
  //     firstTag.addRecipe(firstTagRecipe);
  //     Tag.deleteAll();
  //     assertEquals(Tag.all().size(), 0);
  // }
  //
  // @Test
  // public void deleteAll_deletesAllRecipeTagAssociations() {
  //     Tag firstTag = new Tag("BLT");
  //     Tag secondTag = new Tag("Taco");
  //     firstTag.save();
  //     secondTag.save();
  //     Recipe firstTagRecipe = new Recipe("firstRecipe");
  //     firstTag.addRecipe(firstTagRecipe);
  //     Tag.deleteAll();
  //     assertEquals(firstTagRecipe.getTags().size(), 0);
  // }


}
