import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String name;
  private int stylistId;
  private String phone;
  private String address;

  public Client(String name, int stylistId, String phone, String address) {
    this.name = name;
    this.stylistId = stylistId;
    this.phone = phone;
    this.address = address;
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

  public String getPhone() {
    return phone;
  }

  public String getAddress() {
    return address;
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
    String sql = "INSERT INTO clients (name, stylistId, phone, address) VALUES (:name, :stylistId, :phone, :address)";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
                .addParameter("name", name)
                .addParameter("stylistId", stylistId)
                .addParameter("phone", phone)
                .addParameter("address", address)
                .executeUpdate()
                .getKey();
    }
  }

  //READ
  public static List<Client> all() {
    String sql = "SELECT id, name, stylistId, phone, address FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static Client find (int id) {
    String sql = "SELECT * FROM clients WHERE id = :id ORDER BY name";
    try(Connection con = DB.sql2o.open()) {
      Client client = con.createQuery(sql)
              .addParameter("id", id)
              .executeAndFetchFirst(Client.class);
              return client;

    }
  }

  //UPDATE
  public void update(String newName, int newStylistId, String phone, String address) {
    this.name = newName;
    this.stylistId = newStylistId;
    this.phone = phone;
    this.address = address;
    String sql = "UPDATE clients SET name = :name, stylistId = :stylistId, phone = :phone, address = :address WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("stylistId", stylistId)
        .addParameter("phone", phone)
        .addParameter("address", address)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  //DELETE
  public static void delete(int id) {
    String sql = "DELETE FROM clients WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
