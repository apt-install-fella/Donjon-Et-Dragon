package personnages;

import equipement.*;

import java.util.ArrayList;

public class Clerc extends Personnage {
    public Clerc(String nom, Race race){
<<<<<<< HEAD
        super(nom, race, 16, initInventaire(), "Clerc");
=======
        super(nom, race, 16, initInventaire());
>>>>>>> master
    }

    private static ArrayList<Equipement> initInventaire(){
        ArrayList<Equipement> inventaire = new ArrayList<>();
        inventaire.add(new Arme("masse d'armes"));
        inventaire.add(new Armure("armure d'écailles"));
        inventaire.add(new Arme("arbalète légère"));
        return inventaire;
    }

}
