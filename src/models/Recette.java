package models;

public class Recette {

    int id;
    String createur;
    String nom;
    String description;
    TypeDePlat typeDePlat;
    Difficulte difficulte;
    int nombreDePortions;

    public Recette() {
    }

    public Recette(String nom, Difficulte difficulte) {
        this.nom = nom;
        this.difficulte = difficulte;
    }

}
