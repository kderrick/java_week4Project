import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("template", "templates/bands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/venues", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("venues", Venue.all());
      model.put("template", "templates/venues.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
    //
    // post("/recipes/deleteAll", (request, response) -> {
    //     HashMap<String, Object> model = new HashMap<String, Object>();
    //     Recipe.deleteAll();
    //     response.redirect("/recipes");
    //     return null;
    // });
    //
    // post("/recipes/:id/update", (request, response) -> {
    //     HashMap<String, Object> model = new HashMap<String, Object>();
    //     int id = Integer.parseInt(request.params("id"));
    //     Recipe recipe = Recipe.find(id);
    //     String recipeTitle = request.queryParams("updateRecipeTitle");
    //     String recipeIngredients = request.queryParams("updateRecipeIngredients");
    //     recipe.updateAll(recipeTitle, recipeIngredients);
    //     response.redirect("/recipes/" + id);
    //     return null;
    // });
    //
    // get("/recipes/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int id = Integer.parseInt(request.params("id"));
    //   Recipe recipe = Recipe.find(id);
    //   model.put("recipe", recipe);
    //   model.put("allTags", Tag.all());
    //   model.put("template", "templates/recipe.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // post("/tags/deleteAll", (request, response) -> {
    //     HashMap<String, Object> model = new HashMap<String, Object>();
    //     Tag.deleteAll();
    //     response.redirect("/tags");
    //     return null;
    // });
    //
    // get("/tags/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int id = Integer.parseInt(request.params("id"));
    //   Tag tag = Tag.find(id);
    //   model.put("tag", tag);
    //   model.put("allRecipes", Recipe.all());
    //   model.put("template", "templates/tag.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    post("/bands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String band_name = request.queryParams("band_name");
      Band newBand = new Band(band_name);
      newBand.save();
      response.redirect("/bands");
      return null;
    });
    //
    // post("/recipes/:id/delete", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int recipeId = Integer.parseInt(request.queryParams("recipeId"));
    //   Recipe recipe = Recipe.find(recipeId);
    //   recipe.delete();
    //   response.redirect("/recipes");
    //   return null;
    // });
    //
    // post("/recipes/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int recipeId = Integer.parseInt(request.queryParams("recipe_id"));
    //   int tagId = Integer.parseInt(request.queryParams("tagTitle"));
    //   Tag tag = Tag.find(tagId);
    //   Recipe recipe = Recipe.find(recipeId);
    //   recipe.addTag(tag);
    //   response.redirect("/recipes/" + recipeId);
    //   return null;
    // });
    //
    // post("/tags/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int tagId = Integer.parseInt(request.queryParams("tag_id"));
    //   int recipeId = Integer.parseInt(request.queryParams("recipe_id"));
    //   Recipe recipe = Recipe.find(recipeId);
    //   Tag tag = Tag.find(tagId);
    //   tag.addRecipe(recipe);
    //   response.redirect("/tags/" + tagId);
    //   return null;
    // });
    //
    post("/venues", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String venueName = request.queryParams("venueName");
      Venue newVenue = new Venue(venueName);
      newVenue.save();
      response.redirect("/venues");
      return null;
    });
    //
    // post("/tags/:id/delete", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int tagId = Integer.parseInt(request.queryParams("tagId"));
    //   Tag tag = Tag.find(tagId);
    //   tag.delete();
    //   response.redirect("/tags");
    //   return null;
    // });



  }

  //public static 'Returntype' 'FuncName' (Paramtype param) {}  //first business logic function

}
