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

    @Test
    void afficherInventaire(){
        Clerc clerc = new Clerc("Aydeen", Race.HUMAIN);
        assertEquals("Inventaire : \n\t[1] masse d'armes (dégâts : 1d6, portee : 1)\t[2] armure d'écailles (classe d'armure : 9)\t[3] arbalète légère (dégâts : 1d8, portee : 16)\n", clerc.afficherInventaire());

    }

    @Test
    void resetPV(){
        Magicien mage = new Magicien("Halcyon", Race.ELFE);
        mage.recevoirDegats(4);
        mage.resetPV();
        assertEquals(12, mage.getPV());
    }

}