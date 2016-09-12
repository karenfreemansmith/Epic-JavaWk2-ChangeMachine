import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    ChangeMachine cm = new ChangeMachine();
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/change", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("change", cm.makeChange(Float.parseFloat(request.queryParams("money"))));
      model.put("template", "templates/results.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
