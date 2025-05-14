package personnages;

import equipement.*;

import java.util.ArrayList;

public class Guerrier extends Personnage {

    public Guerrier(String nom, Race race){
<<<<<<< HEAD
        super(nom, race, 20, initInventaire(), "Guerrier");
=======
        super(nom, race, 20, initInventaire());
>>>>>>> master

    }

    private static ArrayList<Equipement> initInventaire(){
        ArrayList<Equipement> inventaire = new ArrayList<>();
        inventaire.add(new Armure("cotte de mailles"));
        inventaire.add(new Arme("épée longue"));
        inventaire.add(new Arme("arbalète légère"));
        return inventaire;
    }
}
