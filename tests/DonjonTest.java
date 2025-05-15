import personnages.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DonjonTest {

    @Test
    void ajoutEntite() {
        Donjon donjon = new Donjon(1, 5, 7);

        Entite p1 = new Guerrier("Fella", Race.ELFE);
        Entite p2 = new Guerrier("Elora", Race.HUMAIN);
        Entite p3 = new Guerrier("Jomni", Race.ELFE);
        Entite p4 =new Monstre(1,"Dragon",20,24,6,9,45,87,34,3,6);

        donjon.ajoutEntite(p1);
        donjon.ajoutEntite(p2);
        donjon.ajoutEntite(p3);
        donjon.ajoutEntite(p4);

          donjon.afficherEntites();
    }

    @Test
    void ordreDeJeu() {
        Donjon donjon = new Donjon(1, 5, 5);

        Entite p1 = new Guerrier("Fella", Race.ELFE);
        Entite p2 = new Guerrier("Elora", Race.HUMAIN);
        Entite p3 = new Guerrier("Jomni", Race.ELFE);
        Entite p4 =new Monstre(1,"Dragon",20,24,6,9,45,87,34,3,6);

        donjon.ajoutEntite(p1);
        donjon.ajoutEntite(p2);
        donjon.ajoutEntite(p3);
        donjon.ajoutEntite(p4);

        String ordre = donjon.ordreDeJeu().toString();
       System.out.println(ordre);
    }

    @Test
    void seDeplacer() {
        Donjon donjon = new Donjon(1, 5, 5);

        donjon.nommerCases();

        Entite p1 = new Guerrier("Fella", Race.NAIN);
        Entite p2 = new Clerc("Elora", Race.NAIN);
        Entite p3 =new Monstre(1,"Dragon",20,24,6,9,45,87,34,3,6);
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

    }



    @Test
    void affichage_plateau() {
        Donjon donjon = new Donjon(1, 7, 10);

        Entite p1 = new Magicien("Fella", Race.HUMAIN);
        Entite p2 = new Guerrier("Elora", Race.ELFE);
        Entite p3 = new Monstre(1,"Dragon",20,24,6,9,45,87,34,3,6);
        donjon.ajoutEntite(p1);
        donjon.ajoutEntite(p2);
        donjon.ajoutEntite(p3);

        donjon.getCases().put("A5", new int[]{1, 0});
        donjon.getCases().put("B4", new int[]{2, 1}); //2eme case tab pleine, donc equipement
        donjon.getCases().put("D4", new int[]{3, 0});
        donjon.getCases().put("J2", new int[]{3, 6});
        donjon.getCases().put("E5", new int[]{4, 0}); //plus que le nombre d'id, donc ca sera un obstacle

       donjon.Affichage_plateau();
    }
}
