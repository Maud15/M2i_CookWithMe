package utils;

import java.util.Random;
import java.util.Scanner;

public class Tools {

    //Constructeur déclaré en privé pour éviter de pouvoir l'instancier, c'est une classe utilitaire
    private Tools() {}

    public static int askDataInt(Scanner sc, String question) {
        return askDataInt(sc,question,null,null);
    }
    public static int askDataInt(Scanner sc, String question,int max) {
        return askDataInt(sc,question,null,max);
    }
    public static int askDataInt(Scanner sc, String question, Integer min) {
        return askDataInt(sc,question,min,null);
    }
    public static int askDataInt(Scanner sc, String question, Integer min, Integer max) {
        boolean dataOutOfRange;
        int data;
        do {
            dataOutOfRange = false;
            // Si min ou max renseigné => Ajouter l'intervalle possible a la fin de la question
            StringBuilder questionImproved = new StringBuilder(question);
            if(min != null) {
                if(max != null) {
                    questionImproved.append(" (entre ").append(min).append(" et ").append(max).append(")");
                } else {
                    questionImproved.append(" (minimum ").append(min).append(")");
                }
            } else if(max != null) {
                questionImproved.append(" (maximum ").append(max).append(")");
            }
            System.out.println(questionImproved);
            data = sc.nextInt();
            if(min != null && data < min) {
                dataOutOfRange = true;
                System.out.println("");
            } else if(max != null && data > max) {
                dataOutOfRange = true;
            }
        } while (dataOutOfRange);
        return data;
    }

    public static String askData(Scanner sc, String question) {
        String data;
        System.out.println(question);
        data = sc.nextLine();
        return data;
    }

    public static int getRandomNumber() {
        return getRandomNumber(null,null);
    }
    public static int getRandomNumber(Integer min, Integer max) {
        if(min == null) min = -999999999;
        if(max == null) max = 999999999;
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

}
