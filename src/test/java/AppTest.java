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

  @Test
  public void deleteBand() {
    Venue newVenue = new Venue("Wonder");
    newVenue.save();
    Band newBand = new Band("El VY");
    newBand.save();
    newBand.addVenue(newVenue);
    String bandId = "#" + newBand.getId();
    goTo("http://localhost:4567/bands");
    submit(bandId);
    assertThat(pageSource()).doesNotContain(newBand.getName());
    assertThat(pageSource()).doesNotContain("404");
  }

  @Test
  public void deleteVenue() {
    Venue newVenue = new Venue("Wonder");
    newVenue.save();
    String venueId = "#" + newVenue.getId();
    goTo("http://localhost:4567/venues");
    submit(venueId);
    assertThat(pageSource()).doesNotContain(newVenue.getName());
    assertThat(pageSource()).doesNotContain("404");
  }

  @Test
  public void assignVenueToBand() {
    Venue newVenue = new Venue("Roseland");
    newVenue.save();
    Band newBand = new Band("The National");
    newBand.save();
    // newBand.addVenue(newVenue);
    goTo("http://localhost:4567/bands/" + newBand.getId());
    fillSelect("#venueName").withIndex(0);
    submit("#assignVenueBtn");
    assertThat(pageSource()).contains("<a href=\"/venues/" + newVenue.getId() + "\">");

  }

  @Test
  public void assignBandToVenue() {
    Venue newVenue = new Venue("Wonder");
    newVenue.save();
    Band newBand = new Band("The National");
    newBand.save();
    // newBand.addVenue(newVenue);
    goTo("http://localhost:4567/venues/" + newVenue.getId());
    fillSelect("#band_id").withIndex(0);
    submit("#assignBandBtn");
    assertThat(pageSource()).contains("<a href=\"/bands/" + newBand.getId() + "\">");
  }

  @Test
  public void deleteAllBands_displaysNoBandsOnBandPage() {
    Band newBand = new Band("The National");
    newBand.save();
    goTo("http://localhost:4567/bands");
    submit("#deleteAllBandsBtn");
    assertThat(pageSource()).doesNotContain("The National");
    assertThat(pageSource()).doesNotContain("Error");
  }

  @Test
  public void updateAllBands_updatesName() {
    Band newBand = new Band("The National");
    newBand.save();
    goTo("http://localhost:4567/bands/" + newBand.getId());
    fill("#updateBandName").with("UpdatedName");
    submit("#updateBand");
    assertThat(pageSource()).contains("UpdatedName");
  }

  @Test
  public void updateAllVenues_updatesName() {
    Venue newVenue = new Venue("Wonder");
    newVenue.save();
    goTo("http://localhost:4567/venues/" + newVenue.getId());
    fill("#updateVenueName").with("UpdatedName");
    submit("#updateVenue");
    assertThat(pageSource()).contains("UpdatedName");
  }

}
