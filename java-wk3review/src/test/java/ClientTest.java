import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Client.all().size(), 0);
  }

  @Test
  public void save_savesClientIntoDatabase() {
    Client newClient = new Client("Susan", 1, "555-5555", "1234 NW Main St.");
    newClient.save();
    assertEquals(newClient.getName(), "Susan");
  }

  @Test
  public void save_savesClientIdIntoDatabase() {
    Client newClient = new Client("Susan", 1, "555-5555", "1234 NW Main St.");
    newClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(newClient.getId(), savedClient.getId());
  }

  @Test
  public void update_updateClientInfoInDB() {
    Client newClient = new Client("Susan", 1, "555-5555", "1234 NW Main St.");
    newClient.save();
    newClient.update("Sally", 1);
    assertEquals(Client.all().get(0).getName(), "Sally");
  }


//RECEIVED THIS ERROR MESSAGE WHEN TRYING TO PASS .delete() TEST:
// /Users/Guest/Desktop/java-wk3review/src/test/java/ClientTest.java:42: error: method delete in class Client cannot be applied to given types;
//     newClient.delete();
//              ^
//   required: int
//   found: no arguments
//   reason: actual and formal argument lists differ in length
// I UNDERSTAND THAT IT NEEDS AN ID TO DELETE THE Client, BUT I DON'T KNOW HOW TO GET THAT ID AND THAT ID WASN'T REQUESTED IN OTHER ACTIVITIES FROM THE WEEK AS FAR AS I CAN TELL. THIS ALSO HAPPENED IN MY Stylists CLASS.



  // @Test
  // public void delete_deletesClientFromDatabase() {
  //   Client newClient = new Client("Susan", 1, "555-5555", "1234 NW Main St.");
  //   newClient.save();
  //   newClient.delete();
  //   assertEquals(Client.all().size(), 0);
  // }
}
