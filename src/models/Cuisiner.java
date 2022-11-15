package models;

import java.time.LocalDate;

public class Cuisiner {

    int idRecette;
    int idUtilisateur;
    LocalDate dateUtilisation;

    public Cuisiner(int idRecette, int idUtilisateur, LocalDate dateUtilisation) {
        this.idRecette = idRecette;
        this.idUtilisateur = idUtilisateur;
        this.dateUtilisation = dateUtilisation;
    }
}
