package jeu;

import personnages.Entite;

import java.util.Hashtable;
import java.util.Map;

public class MaitreDuJeu {
    private String m_pseudo;
    private Donjon m_donjon;

    public MaitreDuJeu(String pseudo, Donjon donjon) {
        this.m_pseudo = pseudo;
        this.m_donjon = donjon;
    }
    public MaitreDuJeu() {
        this.m_pseudo = "Maitre du jeu";
    }

    public String getPseudo() {
        return m_pseudo;
    }


    public String deplace(int Id, String position) {
        boolean reussi= m_donjon.deplacer(Id, position);
        if(reussi) {
            return "Déplacement effectué.";
        }
        return "Impossible de déplacer le personnage";
    }

    public String ajoutObstacle(String position) {
        m_donjon.ajoutObstacle(position);
        return "Un obstacle est ajouté à l'emplacement : " + position;
    }



}
