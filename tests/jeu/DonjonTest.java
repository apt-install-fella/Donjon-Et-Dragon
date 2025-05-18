package jeu;

import org.junit.jupiter.api.Test;
import personnages.*;

import static org.junit.jupiter.api.Assertions.*;

class DonjonTest {

    @Test
    void testAjouterPersonnage() {
        Donjon donjon = new Donjon(1); // constructeur avec numéro
        Personnage p = new Clerc("Aydeen", Race.HUMAIN);
        donjon.ajoutPersonnage(p);
        assertEquals(1, donjon.getListePersonnages().size());
    }

    @Test
    void testRetirerPersonnage() {
        Donjon donjon = new Donjon(1);
        Personnage p = new Magicien("Igor", Race.ELFE);
        donjon.ajoutPersonnage(p);

        // Simulation du retrait
        donjon.getListePersonnages().remove(p);

        assertEquals(0, donjon.getListePersonnages().size());
    }

    @Test
    void testContientPersonnage() {
        Donjon donjon = new Donjon(1);
        Personnage p = new Clerc("Aydeen", Race.HUMAIN);
        donjon.ajoutPersonnage(p);

        // Vérifie si la clé existe dans la Hashtable des personnages
        assertTrue(donjon.getListePersonnages().containsKey(p));
    }
    @Test
    void testAjoutMultiplesPersonnages() {
        Donjon donjon = new Donjon(2);
        Personnage p1 = new Guerrier("Thorgal", Race.NAIN);
        Personnage p2 = new Magicien("Zelda", Race.ELFE);
        donjon.ajoutPersonnage(p1);
        donjon.ajoutPersonnage(p2);
        assertEquals(2, donjon.getListePersonnages().size());
    }

    @Test
    void testPersonnageDansBonDonjon() {
        Donjon d1 = new Donjon(5);
        Donjon d2 = new Donjon(6);
        Personnage p = new Magicien("Gandalf", Race.ELFE);
        d1.ajoutPersonnage(p);
        assertTrue(d1.getListePersonnages().containsKey(p));
        assertFalse(d2.getListePersonnages().containsKey(p));
    }



}
