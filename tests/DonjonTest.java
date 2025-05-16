import jeu.Donjon;
import personnages.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DonjonTest {

    @Test
    void ajoutEntite() {
        Donjon donjon = new Donjon(1, 5, 7);

        Guerrier p1 = new Guerrier("Fella", Race.ELFE);
        Guerrier p2 = new Guerrier("Elora", Race.HUMAIN);
        Guerrier p3 = new Guerrier("Jomni", Race.ELFE);
        Monstre p4 =new Monstre(1,"Dragon",20,24,6,9,45,87,34,3,6);

        donjon.ajoutPersonnage(p1);
        donjon.ajoutPersonnage(p2);
        donjon.ajoutPersonnage(p3);
        donjon.ajoutMonstre(p4);

    }

    @Test
    void ordreDeJeu() {
        Donjon donjon = new Donjon(1, 5, 5);

        Guerrier p1 = new Guerrier("Fella", Race.ELFE);
        Guerrier p2 = new Guerrier("Elora", Race.HUMAIN);
        Guerrier p3 = new Guerrier("Jomni", Race.ELFE);
        Monstre p4 =new Monstre(1,"Dragon",20,24,6,9,45,87,34,3,6);

        donjon.ajoutPersonnage(p1);
        donjon.ajoutPersonnage(p2);
        donjon.ajoutPersonnage(p3);
        donjon.ajoutMonstre(p4);
/*
        ArrayList<Entite> ordre= new ArrayList<>(donjon.ordreDeJeu());
        for (Entite entite : ordre) {
            System.out.println(entite.getNom());
        }

 */

    }

    @Test
    void seDeplacer() {
        /*
        jeu.Donjon donjon = new jeu.Donjon(1, 5, 5);

        donjon.nommerCases();

        Entite p1 = new Guerrier("Fella", Race.NAIN);
        Entite p2 = new Clerc("Elora", Race.NAIN);
        Entite p3 =new Monstre(3,"Dragon",20,24,6,9,45,87,34,3,6);
        donjon.ajoutEntite(p1);
        donjon.ajoutEntite(p2);
        donjon.ajoutEntite(p3);

        donjon.afficherEntites();

        // On place manuellement l'entité dans une case
        donjon.getCases().put("A1", new int[]{1, 0}); // ID 1, pas d'equipement sur cette case
        donjon.getCases().put("B4", new int[]{1, 0});
        donjon.getCases().put("E5", new int[]{1, 0});

        String message1 = donjon.seDeplacer(1, "bas");
        String message2 = donjon.seDeplacer(2, "uiugyug");
        String message3 = donjon.seDeplacer(3, "bas");

        System.out.println(message1);
        System.out.println(message2);
        System.out.println(message3);
*/
    }



    @Test
    void affichagePlateau() {
        Donjon donjon = new Donjon(1, 7, 10);

        Magicien p1 = new Magicien("Fella", Race.HUMAIN);
        Guerrier p2 = new Guerrier("Elora", Race.ELFE);
        Monstre p3 = new Monstre(1,"Dragon",20,24,6,9,45,87,34,3,6);
        donjon.ajoutPersonnage(p1);
        donjon.ajoutPersonnage(p2);
        donjon.ajoutMonstre(p3);

        donjon.positionner(p1, "B3");
        donjon.positionner(p2, "D5");
        donjon.positionner(p3, "F2");


        donjon.affichagePlateau();


    }

    @Test
    void afficherEntites() {
        Donjon donjon = new Donjon(1, 7, 10);

        Magicien p1 = new Magicien("Fella", Race.HUMAIN);
        Guerrier p2 = new Guerrier("Elora", Race.ELFE);
        Monstre p3 = new Monstre(1,"Dragon",20,24,6,9,45,87,34,3,6);
        donjon.ajoutPersonnage(p1);
        donjon.ajoutPersonnage(p2);
        donjon.ajoutMonstre(p3);

    }
}
