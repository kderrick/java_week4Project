import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Band {
  private int id;
  private String name;


  public String getName() {
    return name;
  }
  public int getId() {
    return id;
  }

  public Band (String name) {
    this.name = name;
  }

  public static  List<Band> all() {
    String sql = "SELECT * FROM bands ORDER BY name ASC";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Band.class);
    }
  }

  @Override
  public boolean equals(Object otherBand) {
    if (!(otherBand instanceof Band)) {
      return false;
    } else {
      Band newBand = (Band) otherBand;
      return this.getName().equals(newBand.getName());
    }
  }

  public void save() {
    String sql = "INSERT INTO bands (name) VALUES (:name)";
     try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Band find(int id) {
    String sql = "SELECT * FROM bands WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Band band = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Band.class);
      return band;
    }
  }

  public void updateName(String name) {
    String sql ="UPDATE bands SET name = :name WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
  public void delete() {
    String sqlJoin ="DELETE FROM bands_venues WHERE band_id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sqlJoin)
        .addParameter("id", id)
        .executeUpdate();
    }
    String sql ="DELETE FROM bands WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  //
  // public static void deleteAll() {
  //   String sqlJoin ="DELETE FROM recipes_tags";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sqlJoin)
  //       .executeUpdate();
  //   }
  //   String sql ="DELETE FROM recipes ";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sql)
  //       .executeUpdate();
  //   }
  // }
  //
  // public void addTag (Tag tag) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO recipes_tags (tag_id, recipe_id) VALUES (:tag_id, :recipe_id)";
  //     con.createQuery(sql)
  //     .addParameter("recipe_id", this.getId())
  //     .addParameter("tag_id", tag.getId())
  //     .executeUpdate();
  //   }
  // }
  //
  // public List<Tag> getTags() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT tags.* FROM tags " +
  //     "JOIN recipes_tags ON (tags.id = tag_id) " +
  //     "JOIN recipes ON (recipes.id = recipe_id) " +
  //     "WHERE recipes.id = :id";
  //     List<Tag> tags = con.createQuery(sql)
  //     .addParameter("id", id)
  //     .executeAndFetch(Tag.class);
  //     return tags;
  //   }
  // }
}
