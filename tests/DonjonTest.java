import personnages.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DonjonTest {

    @Test
    void ajoutEntite() {
        Donjon donjon = new Donjon(1, 5, 7);


        Entite perso = new Clerc("Jojo", Race.HUMAIN);

        // Ajout de l'entité
        donjon.ajoutEntite(perso);

        // Vérification que le donjon contient maintenant 1 entité
        // Attention : ici on ne peut pas accéder directement à `m_entites` car il est `private`
        // donc on va tricher un peu en réutilisant la méthode ordreDeJeu() qui affiche tous les noms

        // Tu peux aussi ajouter une méthode dans Donjon pour connaître le nombre d'entités
        // Par exemple :
        // public int getNombreEntites() { return m_entites.size(); }

        assertEquals(1, donjon.getNombreEntites()); // Test si une entité a bien été ajoutée
    }


@org.junit.jupiter.api.Test
    void ordreDeJeu() {
    }

    @org.junit.jupiter.api.Test
    void seDeplacer() {
    }

    @org.junit.jupiter.api.Test
    void affichage_plateau() {
    }
}