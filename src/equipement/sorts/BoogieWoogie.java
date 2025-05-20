package equipement.sorts;

import jeu.Donjon;

public class BoogieWoogie {

    public BoogieWoogie() {

    }

    public String echangerPersos(int idEntite1, int idEntite2, Donjon donjon) {
        return donjon.echangerPlaces(idEntite1, idEntite2);
    }

}
