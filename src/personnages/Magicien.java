package personnages;
import equipement.*;
import equipement.sorts.*;
import jeu.Donjon;

import java.util.ArrayList;

public class Magicien extends Personnage {
    private final Guerison m_guerison;
    private final BoogieWoogie m_boogieWoogie;
    private final ArmeMagique m_armeMagique;

    public Magicien(String nom, Race race){
        super(nom, race, 12, initInventaire(), "Magicien");
        m_guerison = new Guerison();
        m_boogieWoogie = new BoogieWoogie();
        m_armeMagique = new ArmeMagique();
    }

    private static ArrayList<Equipement> initInventaire(){
        ArrayList<Equipement> inventaire = new ArrayList<>();
        inventaire.add(new Arme("bâton"));
        inventaire.add(new Arme("fronde"));
        return inventaire;
    }

    public void guerir(Personnage personnage){
        int soin =m_guerison.guerir();
        personnage.seFaireGuerir(soin);
    }

    public String echanger(int idEntite1, int idEntite2, Donjon donjon){
        return m_boogieWoogie.echangerPersos(idEntite1, idEntite2, donjon);
    }

    public void ameliorer(Arme arme){
        this.m_armeMagique.ameliorer(arme);
    }

}
