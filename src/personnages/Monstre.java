package personnages;

import equipement.Arme;
import equipement.Armure;
import equipement.Equipement;

import java.util.ArrayList;
import java.util.Random;

public class Monstre {
    String m_nom;
    Race m_race;
    public Monstre(String nom, Race race){
        this.m_nom = nom;
        this.m_race = race;
    }
}
