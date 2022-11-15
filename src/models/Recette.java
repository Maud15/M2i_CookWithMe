package models;

public class Recette {

    int id;
    String createur;
    String nom;
    String description;
    TypeDePlat typeDePlat;
    Difficulte difficulte;
    Integer nombreDePortions;

    public Recette() {
        this.createur = "Admin";
    }

    public Recette(String nom, Difficulte difficulte) {
        this.nom = nom;
        this.difficulte = difficulte;
    }

    public Recette(String nom, String description, TypeDePlat typeDePlat, Difficulte difficulte, Integer nombreDePortions) {
        this.nom = nom;
        this.description = description;
        this.typeDePlat = typeDePlat;
        this.difficulte = difficulte;
        this.nombreDePortions = nombreDePortions;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        //todo: createur ?
        sb.append("Recette : ");
        sb.append(this.nom);
        sb.append("   ---   ");
        sb.append(this.difficulte);
        sb.append("   ---   ");
        sb.append(this.typeDePlat);
        if(this.nombreDePortions != null) {
            sb.append("\nPortions : pour ");
            sb.append(this.nombreDePortions);
            sb.append(" personnes");
        }

        if(this.description != null) {
            sb.append("\nDescription : \n");
            sb.append(this.description);
        }

        return sb.toString();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeDePlat getTypeDePlat() {
        return typeDePlat;
    }

    public void setTypeDePlat(TypeDePlat typeDePlat) {
        this.typeDePlat = typeDePlat;
    }

    public Difficulte getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(Difficulte difficulte) {
        this.difficulte = difficulte;
    }

    public int getNombreDePortions() {
        return nombreDePortions;
    }

    public void setNombreDePortions(int nombreDePortions) {
        this.nombreDePortions = nombreDePortions;
    }
}
