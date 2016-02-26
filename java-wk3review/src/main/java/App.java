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
  }


}
