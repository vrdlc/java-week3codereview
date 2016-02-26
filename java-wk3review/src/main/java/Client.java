import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String name;
  private int stylistId;

  public Client(String name, int stylistId) {
    this.name = name;
    this.stylistId = stylistId;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getStylistId() {
    return stylistId;
  }

  @Override
  public boolean equals (Object otherClient) {
    if(!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
      this.getId() == newClient.getId();
    }
  }

  //CREATE
  public void save() {
    String sql = "INSERT INTO clients (name, stylistId) VALUES (:name, :stylistId)";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
                .addParameter("name", name)
                .addParameter("stylistId", stylistId)
                .executeUpdate()
                .getKey();
    }
  }

  //READ
  public static List<Client> all() {
    String sql = "SELECT id, name, stylistId FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }
}
