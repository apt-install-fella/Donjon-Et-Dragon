package personnages;
import equipement.*;

import java.util.ArrayList;

public class Magicien extends Personnage {
    public Magicien(String nom, Race race){
        super(nom, race, 12, initInventaire());
    }

    private static ArrayList<Equipement> initInventaire(){
        ArrayList<Equipement> inventaire = new ArrayList<>();
        inventaire.add(new Arme("bâton"));
        inventaire.add(new Arme("fronde"));
        return inventaire;
    }

}
