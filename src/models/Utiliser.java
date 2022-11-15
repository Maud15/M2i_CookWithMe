package models;

public class Utiliser {

    int idIngredient;
    int idRecette;
    int quantite;
    String unite;


    public Utiliser(int idIngredient, int idRecette, int quantite, String unite) {
        this.idIngredient = idIngredient;
        this.idRecette = idRecette;
        this.quantite = quantite;
        this.unite = unite;
    }
}
