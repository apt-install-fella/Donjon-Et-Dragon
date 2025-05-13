package personnages;
import equipement.*;

import java.util.ArrayList;

public class Roublard extends Personnage {
    public Roublard(String nom, Race race){
        super(nom, race, 16, initInventaire());
    }

    private static ArrayList<Equipement> initInventaire(){
        ArrayList<Equipement> inventaire = new ArrayList<>();
        inventaire.add(new Arme("rapière"));
        inventaire.add(new Arme("arc court"));
        return inventaire;
    }
}
