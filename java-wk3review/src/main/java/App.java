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
  get("/", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  


  }
}
