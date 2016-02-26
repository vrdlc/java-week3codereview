import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void save_savesStylistNameIntoDatabase() {
    Stylist newStylist = new Stylist("Sally");
    newStylist.save();
    assertEquals(newStylist.getName(), "Sally");
  }

}
