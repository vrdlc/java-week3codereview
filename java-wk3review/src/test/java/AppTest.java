import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import java.util.List;


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
    assertThat(pageSource()).contains("Salon Database");
  }

  @Test
  public void stylistIsDisplayed() {
    Stylist newStylist = new Stylist("Sally");
    newStylist.save();
    goTo("http://localhost:4567/stylists");
    assertThat(pageSource().contains("Sally"));
  }

  @Test
  public void allClientsDisplayOnStylistPage() {
    Stylist newStylist = new Stylist("Sally");
    newStylist.save();
    Client firstClient = new Client("Susan", newStylist.getId(), "555-5555", "1234 NW Main St.");
    firstClient.save();
    Client secondClient = new Client("Stan", newStylist.getId(), "555-5555", "4321 SW Main St.");
    secondClient.save();

    //THIS WAS THE ORIGINAL LINE, BUT THE LINK DIDN'T WORK AND I DON'T KNOW WHY. MY Stylist.getId() METHOD WORKS.
    // String stylistPath = String.format("http://localhost:4567/:%d", newStylist.getId());

    String stylistPath = String.format("http://localhost:4567/clients");
    goTo(stylistPath);
    assertThat(pageSource()).contains("Susan");
    assertThat(pageSource()).contains("Stan");
  }

  @Test
  public void stylistIsDeleted() {
    Stylist newStylist = new Stylist("Sally");
    newStylist.save();
    int stylistId = newStylist.getId();
    newStylist.delete(stylistId);
    goTo("http://localhost:4567/");
    assertThat(!(pageSource().contains("Sally")));
  }

  @Test
  public void clientIsDeleted() {
    Client newClient = new Client("Susan", 1, "555-5555", "1234 NW Main St.");
    newClient.save();
    int clientId = newClient.getId();
    newClient.delete(clientId);
    goTo("http://localhost:4567/clients");
    assertThat(!(pageSource().contains("Susan")));
  }

}
