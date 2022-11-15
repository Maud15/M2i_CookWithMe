import connection.ConnectionManager;
import models.Difficulte;
import models.Recette;
import models.TypeDePlat;
import utils.Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RecipeManagement implements RecipeCrudDao<Recette> {

    Connection connection;
    Integer idUtilisateur;

    public RecipeManagement(Connection connection, int idUtilisateur) {
        this.connection = connection;
        this.idUtilisateur = idUtilisateur;
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
    public Recette findRandom() {
        String queryRandom = "SELECT `nom`, `description`, `nombre_de_portion`, `difficulte`, `type_de_plat` " +
                "FROM recette " +
                "LEFT JOIN cuisiner c ON recette.id_recette = c.id_recette " +
                "WHERE (c.id_utilisateur = "+ this.idUtilisateur +" AND DATEDIFF(NOW(), c.date_utilisation) > 6) " +
                "OR c.id_utilisateur != "+ this.idUtilisateur +" OR  c.id_utilisateur IS NULL " +
                "ORDER BY RAND() LIMIT 1;";

        try(PreparedStatement st = connection.prepareStatement(queryRandom)) {
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return new Recette(
                        rs.getString("nom"),
                        rs.getString("description"),
                        TypeDePlat.valueOf(rs.getString("type_de_plat")),
                        Difficulte.valueOf(rs.getString("difficulte")),
                        rs.getInt("nombre_de_portion"));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Recette> findByKeyword(String criterion, String keyword) {
        return Optional.empty();
    }

    @Override
    public Recette create(Recette recette) {
        String queryCreate = "" +
                "INSERT INTO recette (`description`,`nom`,`difficulte`,`type_de_plat`, `nombre_de_portion`,`id_utilisateur`)" +
                "SELECT ? , ? , ? , ? , ?, u.id_utilisateur " +
                "FROM utilisateur u " +
                "WHERE u.pseudo = ?;";

        try(PreparedStatement st = connection.prepareStatement(queryCreate)) {
            st.setString(1,recette.getDescription());
            st.setString(2,recette.getNom());
            st.setString(3,recette.getDifficulte().toString());
            st.setString(4,recette.getTypeDePlat().toString());
            st.setInt(5,recette.getNombreDePortions());
            st.setString(6,recette.getCreateur());

            boolean isSuccess = st.execute();
            //todo: demander comment ça marche
            //connection.commit();
            //todo: demander comment ça marche ça aussi... peut-on l'utiliser ici
            //ResultSet key = st.getGeneratedKeys();
            if(isSuccess) {
                System.out.println("Recette créée");
                //recette.setId(key.getInt("id_recette"));
            } else {
                System.out.println("Erreur création");
            }

        } catch (SQLException e) {
            //todo: demander comment ça marche
            //connection.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return recette;
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
        List<Recette> recipeList = findAll();
        for (Recette recipe:recipeList) {
            System.out.print("Nom : " + recipe.getNom()+"   ---   ");
            System.out.println(recipe.getDifficulte());
        }
    }
    public void addNewRecipe() {
        Scanner sc = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);
        Recette recette = new Recette();
        //todo
        //recette.setCreateur();
        recette.setNom(Tools.askData(sc, "Veuillez saisir le nom de la recette"));
        recette.setDescription(Tools.askData(sc, "Veuillez saisir une description"));
        recette.setNombreDePortions(Tools.askDataInt(scInt, "Veuillez saisir le nombre de portions"));
        String questionType = """
                Choisir le type de plat :
                1 -> ENTREE
                2 -> PLAT
                3 -> DESSERT
                4 -> PETIT DEJEUNER
                """;
        String questionDifficulte = """
                Choisir le niveau de difficulte :
                1 -> FACILE
                2 -> INTERMEDIAIRE
                3 -> DIFFICILE
                """;
        int type = Tools.askDataInt(scInt,questionType,1,4);
        int difficulte = Tools.askDataInt(scInt,questionDifficulte,1,3);

        recette.setTypeDePlat(TypeDePlat.values()[type-1]);
        recette.setDifficulte(Difficulte.values()[difficulte-1]);
        if(recette.getCreateur() != null && recette.getDifficulte() != null) {
            Recette recetteCreee = create(recette);
        } else {
            System.out.println("La recette n'est pas complète");
        }
    }
    public void displayRecipesByCriterion() {
        //Demander quel type de critère
        // demander le mot clef
        //findByKeyword(type, keyword)
    }
    public void displayRandomRecipe() {
        Recette recette = findRandom();
        if(recette != null) {
            System.out.println(recette);
        } else {
            System.out.println("Erreur : Impossible de récupérer une recette");
        }
    }
}
