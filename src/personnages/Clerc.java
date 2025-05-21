package personnages;

import equipement.*;
import equipement.sorts.Guerison;

import java.util.ArrayList;

public class Clerc extends Personnage {
    private final Guerison m_sort;

    public Clerc(String nom, Race race){
        super(nom, race, 16, initInventaire(), "Clerc");
        m_sort = new Guerison();
    }

    private static ArrayList<Equipement> initInventaire(){
        ArrayList<Equipement> inventaire = new ArrayList<>();
        inventaire.add(new Arme("masse d'armes"));
        inventaire.add(new Armure("armure d'écailles"));
        inventaire.add(new Arme("arbalète légère"));
        return inventaire;
    }

    public void guerir(Personnage personnage){
        int soin = m_sort.guerir();
        personnage.seFaireGuerir(soin);
    }

}
