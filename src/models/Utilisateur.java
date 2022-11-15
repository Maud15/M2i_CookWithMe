package models;

public class Utilisateur {

    int id;
    String pseudo;
    String email;
    String motDePasse;

    public Utilisateur(int id, String pseudo, String email, String motDePasse) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.motDePasse = motDePasse;
    }

}
