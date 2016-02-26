import org.sql2o.*;
import java.util.List;

public class Stylist {
  private int id;
  private String name;


  public Stylist (String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override //STILL DON'T QUITE UNDERSTAND THIS
  public boolean equals(Object otherStylist) {
    if(!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
        this.getId() == newStylist.getId();
    }
  }

  //CREATE
  public void save() {
    String sql = "INSERT INTO stylists (name) VALUES (:name)";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
                .addParameter("name", name)
                .executeUpdate()
                .getKey();
    }
  }

  //READ
  public static List<Stylist> all() {
    String sql = "SELECT id, name FROM stylists";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  //UPDATE
  public void update(String newName) {
    this.name = newName;
    String sql = "UPDATE stylists SET name = :name WHERE id = :id";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public List<Client> getClients(){
    String sql = "SELECT * FROM clients WHERE stylistid = :id";
    try(Connection con = DB.sql2o.open()) {
      List<Client> clients = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Client.class);
      return clients;
    }
  }

  //DELETE
  public void delete() {
    String sql = "DELETE FROM stylists WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
