import connection.ConnectionManager;
import models.Difficulte;
import models.Recette;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecipeManagement implements RecipeCrudDao<Recette> {

    Connection connection;

    public RecipeManagement(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Recette> findAll() {
        List<Recette> recipeList = new ArrayList<>();
        String query = "SELECT `nom`, `difficulte` FROM Recette";
        try(PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                //todo: vérifier si le uppercase est utile
                Recette recette = new Recette(rs.getString("nom"), Difficulte.valueOf(rs.getString("difficulte").toUpperCase()));
                recipeList.add(recette);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return recipeList;
    }

    @Override
    public Optional<Recette> findByKeyword(String criterion, String keyword) {
        return Optional.empty();
    }

    @Override
    public Recette create(Recette element) {
        return null;
    }

    /*@Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Recette update(Recette element) {
        return null;
    }*/


    public void displayAllRecipes() {
        System.out.println("Liste des recettes disponibles : ");
        System.out.println(findAll());
    }
    public void addNewRecipe() {
        Recette recette = new Recette();
        create(recette);
    }
    public void displayRecipesByCriterion() {
        //Demander quel type de critère
        // demander le mot clef
        // findByKeyword(type, keyword)
    }
    public void displayRandomRecipe() {
        // Requète -> récupérer une ligne random correspondant à une recette
        // l'afficher
    }
}
