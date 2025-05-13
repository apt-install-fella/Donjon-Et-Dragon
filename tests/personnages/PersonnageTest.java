package personnages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonnageTest {

    @Test
    void getNom() {
        Clerc clerc = new Clerc("Aydeen", Race.HUMAIN);
        assertEquals("Aydeen", clerc.getNom());
    }

    @Test
    void testToString() {
        Magicien mage = new Magicien("Igor", Race.ELFE);
        assertEquals("Igor (Elfe Magicien, 12/12)\n", mage.toString());
    }

    @Test
    void estVivant(){
        Roublard rd = new Roublard("Elyon", Race.HALFELIN);
        assertTrue(rd.estVivant());
    }

    @Test
    void recevoirDegats(){
        Magicien mage = new Magicien("Halcyon", Race.ELFE);
        mage.recevoirDegats(4);
        assertEquals(8, mage.getPV());
    }
}