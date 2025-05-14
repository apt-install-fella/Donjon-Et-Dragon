package personnages;
import equipement.*;

import java.util.ArrayList;

public class Roublard extends Personnage {
    public Roublard(String nom, Race race){
<<<<<<< HEAD
        super(nom, race, 16, initInventaire(), "Roublard");
=======
        super(nom, race, 16, initInventaire());
>>>>>>> master
    }

    private static ArrayList<Equipement> initInventaire(){
        ArrayList<Equipement> inventaire = new ArrayList<>();
        inventaire.add(new Arme("rapière"));
        inventaire.add(new Arme("arc court"));
        return inventaire;
    }
}
