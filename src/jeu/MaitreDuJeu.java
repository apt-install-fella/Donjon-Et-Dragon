package jeu;

import personnages.Entite;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class MaitreDuJeu {
    private String m_pseudo;
    private Donjon m_donjon;

    public MaitreDuJeu(String pseudo, Donjon donjon) {
        this.m_pseudo = pseudo;
        this.m_donjon = donjon;
    }
    public MaitreDuJeu(Donjon donjon) {
        this.m_pseudo = "Maitre du jeu";
        this.m_donjon = donjon;
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


    public String infligerDegats(Entite entite, int nbLancers, int nbFaces) {

            Random random = new Random();
            int somme = 0;

            for(int i=0; i<nbLancers; i++){
                somme += 1 + random.nextInt(nbFaces);
            }

        entite.recevoirDegats(somme);
        return (entite.getNom())+" est une entité un peu trop forte, mais un malheureux incident fait qu'elle perde "+somme+" dégats.\nQuel dommage...";
    }

}
