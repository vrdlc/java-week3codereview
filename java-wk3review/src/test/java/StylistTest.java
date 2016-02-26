// import org.junit.*;
// import static org.junit.Assert.*;
// import org.sql2o.*;
// import java.util.Arrays;
// import java.util.List;
//
// public class StylistTest {
//
//   @Rule
//   public DatabaseRule database = new DatabaseRule();
//
//   @Test
//   public void all_emptyAtFirst() {
//       assertEquals(Stylist.all().size(), 0);
//   }
//
//   @Test
//   public void save_savesStylistNameIntoDatabase() {
//     Stylist newStylist = new Stylist("Sally");
//     newStylist.save();
//     assertEquals(newStylist.getName(), "Sally");
//   }
//
//   @Test
//   public void save_assignsIdToObject() {
//     Stylist newStylist = new Stylist("Sally");
//     newStylist.save();
//     Stylist savedStylist = Stylist.all().get(0);
//     assertEquals(newStylist.getId(), savedStylist.getId());
//   }
//
//   @Test
//   public void find_returnsStylistObject_True() {
//     Stylist newStylist = new Stylist("Sally");
//     newStylist.save();
//     assertTrue(newStylist.equals(Stylist.find(newStylist.getId())));
//   }
//
//   @Test
//   public void update_updateStylistInfoInDB() {
//     Stylist newStylist = new Stylist("Sally");
//     newStylist.save();
//     newStylist.update("Stan");
//     assertEquals(Stylist.all().get(0).getName(), "Stan");
//   }
//
//   @Test
//   public void getClients_getsClientsFromEachStylistId() {
//     Stylist newStylist = new Stylist("Sally");
//     newStylist.save();
//     Client newClient = new Client("Susan", newStylist.getId());
//     newClient.save();
//     assertTrue(newStylist.getClients().contains(newClient));
//   }
//
//   @Test
//   public void delete_deletesStylistFromDatabase() {
//     Stylist newStylist = new Stylist("Sally");
//     newStylist.save();
//     newStylist.delete();
//     assertEquals(Stylist.all().size(), 0);
//   }
//
// }
