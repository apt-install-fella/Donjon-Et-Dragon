package personnages;
import equipement.*;

import java.util.ArrayList;

public class Magicien extends Personnage {
    public Magicien(String nom, Race race){
<<<<<<< HEAD
        super(nom, race, 12, initInventaire(), "Magicien");
=======
        super(nom, race, 12, initInventaire());
>>>>>>> master
    }

    private static ArrayList<Equipement> initInventaire(){
        ArrayList<Equipement> inventaire = new ArrayList<>();
        inventaire.add(new Arme("bâton"));
        inventaire.add(new Arme("fronde"));
        return inventaire;
    }

}
