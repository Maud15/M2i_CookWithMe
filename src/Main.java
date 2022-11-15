import connection.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static utils.Tools.askDataInt;

public class Main {

    public static void main(String[] args) {

        try(Connection connection = ConnectionManager.getConnectionInstance()) {
            //Action = 3 -> 6
            int action = askAction(true);
            RecipeManagement recipeManagement = new RecipeManagement(connection);
            switch (action) {
                case 3 -> recipeManagement.displayAllRecipes();
                case 4 -> recipeManagement.addNewRecipe();
                case 5 -> recipeManagement.displayRandomRecipe();
                case 6 -> recipeManagement.displayRecipesByCriterion();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


        // Choix d'action :
            //Si on n'est pas connecté :
                // Visualiser des recettes
                // S'inscrire
                // Se connecter

            //Si on est connecté :
                // Visualiser des recettes
                //Rechercher une recette (get by keyWord)
                //Ajouter une recette
                //Demander recette aléatoire
                //Editer son profil
                //Se deconnecter

    }

    private static int askAction(boolean isConnected) {
        Scanner sc = new Scanner(System.in);
        String question;
        Integer optionMax;
        Integer optionMin;
        if(!isConnected) {
            question = """
                    - 1 : S'inscrire
                    - 2 : Se connecter
                    - 3 : Visualiser des recettes
                    """;
            optionMin = 1;
            optionMax = 3;
        } else {
            question = """
                    - 3 : Visualiser des recettes
                    - 4 : Ajouter une recette
                    - 5 : Rechercher une recette par critère
                    - 6 : Récupérer une recette aléatoire
                    - 7 : Editer son profil
                    - 8 : Se déconnecter
                    """;
            optionMin = 3;
            optionMax = 6;
        }
        return askDataInt(sc, question, optionMin, optionMax);
    };

}