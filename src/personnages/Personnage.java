package personnages;

import equipement.Arme;
import equipement.Armure;
import equipement.Equipement;

import java.util.ArrayList;
import java.util.Random;

public abstract class Personnage {
    private String m_nom;
    private Race m_race;
    private int m_PV;
    private int m_force;
    private int m_dexterite;
    private int m_vitesse;
    private int m_initiative;
    private Armure m_armure;
    private Arme m_arme;
    private ArrayList<Equipement> m_inventaire;

    Personnage(String nom, Race race, int pv, ArrayList<Equipement> inventaire){
        this.m_nom = nom;
        this.m_race = race;
        this.m_PV = pv;
        this.m_inventaire = inventaire;

        //Décision des caractéristiques
        this.m_force = jetDes(4, 4) + 3;
        this.m_dexterite = jetDes(4, 4) + 3;
        this.m_vitesse = jetDes(4, 4) + 3;
        this.m_initiative = jetDes(4, 4) + 3;

        switch(race){
            case HUMAIN:
                m_PV += 2;
                m_force += 2;
                m_dexterite += 2;
                m_vitesse += 2;
                m_initiative += 2;
                break;
            case NAIN:
                m_force += 6;
                break;
            case ELFE:
                m_dexterite += 6;
                break;
            case HALFELIN:
                m_dexterite += 4;
                m_vitesse += 2;
        }
    }

    private static int jetDes(int nbLancers, int nbFaces){
        Random random = new Random();
        int somme = 0;

        for(int i=0; i<nbLancers; i++){
            somme += 1 + random.nextInt(nbFaces);
        }

        return somme;
    }



}
