import java.util.Map;
import java.util.List;
import java.util.HashMap;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


public class App {


  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

//USER STORY: I want to be able to see a list of all stylists in the database
//USER STORY: I want to be able to view all clients in the database
//USER STORY: I want to be able to see all clients assigned to a single stylist
  // get("/", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   model.put("template", "templates/index.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());
  //
  // post("/", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   String name = request.queryParams("stylistform");
  //   Stylist stylist = new Stylist(name);
  //   stylist.save();
  //   model.put("stylists", Stylist.all());
  //   model.put("template", "templates/index.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());
  //
  // get("/stylist/:id", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
  //   model.put("clients", Stylist.getClients(stylist.getId()));
  //   model.put("stylists", stylist);
  //   model.put("template", "templates/styilist.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());
  //
  //
  // }
  //
  // }


  get("/", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/clients", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    model.put("clients", Client.all());
    model.put("template", "templates/clients.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/clients", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    int id = Integer.parseInt(request.queryParams("clientId"));
    // Client.delete();
    model.put("clients", Client.all());
    model.put("template", "templates/clients.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());


  post("/delete/stylist/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    int id = Integer.parseInt(request.params(":id"));
    Stylist.delete(id);
    model.put("stylists", Stylist.all());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/delete/client/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    int id = Integer.parseInt(request.params(":id"));
    Client.delete(id);
    Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
    List<Client> clients = stylist.getClients();
    model.put("stylist", stylist);
    model.put("clients", clients);
    model.put("template", "templates/client.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    String stylistName = request.queryParams("stylistName");
    Stylist newStylist = new Stylist(stylistName);
    newStylist.save();
    List<Stylist> stylistList = newStylist.all();
    model.put("stylists", stylistList);
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());


  post("/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
    int id = Integer.parseInt(request.queryParams("stylistId"));
    String clientName = request.queryParams("clientName");
    Client newClient = new Client(clientName, id);
    newClient.save();
    List<Client> clients = stylist.getClients();
    model.put("stylist", stylist);
    model.put("clients", clients);
    model.put("template", "templates/client.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
    List<Client> clients = stylist.getClients();
    model.put("stylist", stylist);
    model.put("clients", clients);
    model.put("template", "templates/client.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());
}
}
