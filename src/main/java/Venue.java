import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Venue {
  private String name;
  private int id;

  public Venue(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public static  List<Venue> all() {
    String sql = "SELECT * FROM venues";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Venue.class);
    }
  }

  @Override
  public boolean equals(Object otherVenue) {
    if (!(otherVenue instanceof Venue)) {
      return false;
    } else {
      Venue newVenue = (Venue) otherVenue;
      return this.getName().equals(newVenue.getName()) &&
      this.getId() == newVenue.getId();
    }
  }

  public void save() {
    String sql = "INSERT INTO venues (name) VALUES (:name)";
     try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Venue find(int id) {
    String sql = "SELECT id, name FROM venues WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Venue venue = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Venue.class);
      return venue;
    }
  }
  //
  public void update(String name) {
    String sql ="UPDATE venues SET name = :name WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
  //
  // public void delete() {
  //   String sqlJoin ="DELETE FROM recipes_tags WHERE tag_id = :id";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sqlJoin)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  //   String sql ="DELETE FROM tags WHERE id = :id";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }
  //
  // public static void deleteAll() {
  //   String sqlJoin ="DELETE FROM recipes_tags";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sqlJoin)
  //       .executeUpdate();
  //   }
  //   String sql ="DELETE FROM tags ";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sql)
  //       .executeUpdate();
  //   }
  // }
  //
  // public void addRecipe (Recipe recipe) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO recipes_tags (recipe_id, tag_id) VALUES (:recipe_id, :tag_id)";
  //     con.createQuery(sql)
  //     .addParameter("tag_id", this.getId())
  //     .addParameter("recipe_id", recipe.getId())
  //     .executeUpdate();
  //   }
  // }
  //
  // public List<Recipe> getRecipes() {
  //   try(Connection con = DB.sql2o.open()) {
  //
  //     String sql = "SELECT recipes.* FROM tags " +
  //     "JOIN recipes_tags ON (tags.id = tag_id) " +
  //     "JOIN recipes ON (recipe_id = recipes.id) " +
  //     "WHERE tags.id = :id";
  //     List<Recipe> recipes = con.createQuery(sql)
  //     .addParameter("id", id)
  //     .executeAndFetch(Recipe.class);
  //     return recipes;
  //   }
  // }
}
