// import org.junit.*;
// import static org.junit.Assert.*;
// import org.sql2o.*;
//
// public class ClientTest {
//
//   @Rule
//   public DatabaseRule database = new DatabaseRule();
//
//   @Test
//   public void all_emptyAtFirst() {
//       assertEquals(Client.all().size(), 0);
//   }
//
//   @Test
//   public void save_savesClientIntoDatabase() {
//     Client newClient = new Client("Susan", 1);
//     newClient.save();
//     assertEquals(newClient.getName(), "Susan");
//   }
//
//   @Test
//   public void save_savesClientIdIntoDatabase() {
//     Client newClient = new Client("Susan", 1);
//     newClient.save();
//     Client savedClient = Client.all().get(0);
//     assertEquals(newClient.getId(), savedClient.getId());
//   }
//
//   @Test
//   public void update_updateClientInfoInDB() {
//     Client newClient = new Client("Susan", 1);
//     newClient.save();
//     newClient.update("Sally", 1);
//     assertEquals(Client.all().get(0).getName(), "Sally");
//   }
//
//   @Test
//   public void delete_deletesClientFromDatabase() {
//     Client newClient = new Client("Susan", 1);
//     newClient.save();
//     newClient.delete();
//     assertEquals(Client.all().size(), 0);
//   }
// }
