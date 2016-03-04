import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();


  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Database");
  }


  @Test
  public void addBand() {
    goTo("http://localhost:4567/bands");
    fill("#band_name").with("The National");
    submit("#addBand");
    assertThat(pageSource()).contains("The National");
  }

  @Test
  public void addVenue() {
    goTo("http://localhost:4567/venues");
    fill("#venueName").with("Wonder Ballroom");
    submit("#addVenueBtn");
    assertThat(pageSource()).contains("Wonder Ballroom");
  }

  @Test
  public void addVenueToBand() {
    Venue newVenue = new Venue("Wonder");
    newVenue.save();
    Band newBand = new Band("The National");
    newBand.save();
    String bandPath = String.format("http://localhost:4567/bands/%d", newBand.getId());
    goTo(bandPath);
    assertThat(pageSource()).contains("The National");
    assertThat(pageSource()).contains("Wonder");
  }

  @Test
  public void addBandToVenue() {
    Venue newVenue = new Venue("Roseland");
    newVenue.save();
    Band newBand = new Band("Do Make Say Think");
    newBand.save();
    String venuePath = String.format("http://localhost:4567/venues/%d", newVenue.getId());
    goTo(venuePath);
    assertThat(pageSource()).contains("Roseland");
    assertThat(pageSource()).contains("Do Make Say Think");
  }
  //
  // @Test
  // public void deleteRecipe() {
  //   Venue newTag = new Tag("Mexican");
  //   newTag.save();
  //   Recipe newRecipe = new Recipe("Tacos");
  //   newRecipe.save();
  //   newRecipe.addTag(newTag);
  //   String recipeId = "#" + newRecipe.getId();
  //   goTo("http://localhost:4567/recipes");
  //   submit(recipeId);
  //   assertThat(pageSource()).doesNotContain(newRecipe.getTitle());
  // }
  //
  // @Test
  // public void deleteTag() {
  //   Tag newTag = new Tag("Mexican");
  //   newTag.save();
  //   String tagId = "#" + newTag.getId();
  //   goTo("http://localhost:4567/tags");
  //   submit(tagId);
  //   assertThat(pageSource()).doesNotContain(newTag.getTitle());
  //   assertThat(pageSource()).doesNotContain("404");
  // }
  //
  // @Test
  // public void assignTagToRecipe() {
  //   Tag newTag = new Tag("Mexican");
  //   newTag.save();
  //   Recipe newRecipe = new Recipe("Tacos");
  //   newRecipe.save();
  //   // newRecipe.addTag(newTag);
  //   goTo("http://localhost:4567/recipes/" + newRecipe.getId());
  //   fillSelect("#tagTitle").withIndex(0);
  //   submit("#assignTagBtn");
  //   assertThat(pageSource()).contains("<a href=\"/tags/" + newTag.getId() + "\">");
  //
  // }
  //
  // @Test
  // public void assignRecipeToTag() {
  //   Tag newTag = new Tag("Mexican");
  //   newTag.save();
  //   Recipe newRecipe = new Recipe("Tacos");
  //   newRecipe.save();
  //   // newRecipe.addTag(newTag);
  //   goTo("http://localhost:4567/tags/" + newTag.getId());
  //   fillSelect("#recipe_id").withIndex(0);
  //   submit("#assignRecipeBtn");
  //   assertThat(pageSource()).contains("<a href=\"/recipes/" + newRecipe.getId() + "\">");
  //
  // }
  //
  // @Test
  // public void deleteAllRecipes_displaysNoRecipesOnRecipePage() {
  //   Recipe newRecipe = new Recipe("Tacos");
  //   newRecipe.save();
  //   goTo("http://localhost:4567/recipes");
  //   submit("#deleteAllRecipesBtn");
  //   assertThat(pageSource()).doesNotContain("Tacos");
  //   assertThat(pageSource()).doesNotContain("Error");
  // }
  //
  // @Test
  // public void updateAllRecipes_updatesNameAndIngredients() {
  //   Recipe newRecipe = new Recipe("Tacos", "Beef");
  //   newRecipe.save();
  //
  //   goTo("http://localhost:4567/recipes/" + newRecipe.getId());
  //   fill("#updateRecipeTitle").with("UpdatedTitle");
  //   fill("#updateRecipeIngredients").with("Yak Butt");
  //   submit("#updateRecipe");
  //   assertThat(pageSource()).contains("UpdatedTitle");
  //   assertThat(pageSource()).contains("Yak Butt");
  // }


  // @Test
  // public void checkThatSubmitButtonWorksOnRecipes() {
  //   goTo("http://localhost:4567/recipes");
  //   fill()
  // }


  /*
  @Test
  public void negativeNumber() {
    goTo("http://localhost:4567");
    fill("#userChange").with("-87");
    submit(".btn");
    assertThat(pageSource()).contains("Please enter a positive value");
  }
*/

}
