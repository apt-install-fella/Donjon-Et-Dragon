package personnages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonstreTest {

    @Test
    void getNom() {
        Monstre monstre = new Monstre(1, "Dragon bleu", 10, 10, 10, 2, 10, 10, 2, 1, 6);
        assertEquals("Dragon bleu", monstre.getNom());
    }

    @Test
    void getClasseArmure() {
        Monstre monstre = new Monstre(1, "Dragon bleu", 10, 10, 10, 2, 10, 10, 2, 1, 6);
        assertEquals(10, monstre.getClasseArmure());
    }

    @Test
    void getPV() {
        Monstre monstre = new Monstre(1, "Dragon bleu", 10, 10, 10, 2, 10, 10, 2, 1, 6);
        assertEquals(10, monstre.getPV());
    }

    @Test
    void recevoirDegats() {
        Monstre monstre = new Monstre(1, "Dragon bleu", 10, 10, 10, 2, 10, 10, 2, 1, 6);
        monstre.recevoirDegats(4);
        assertEquals(6, monstre.getPV());
    }

    @Test
    void estVivant() {
        Monstre monstre = new Monstre(1, "Dragon bleu", 10, 10, 10, 2, 10, 10, 2, 1, 6);
        assertTrue(monstre.estVivant());
    }

    @Test
    void testToString() {
        Monstre monstre = new Monstre(1, "Dragon bleu", 10, 10, 10, 2, 10, 10, 2, 1, 6);
        assertEquals("Dragon bleu (10/10)\n", monstre.toString());
    }
}