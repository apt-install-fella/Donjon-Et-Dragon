package equipement.sorts;

import java.util.Random;

public class Guerison extends Sort{
    private final int m_nbLancers;
    private final int m_nbFaces;

    public Guerison() {
        this.m_nbLancers = 1;
        this.m_nbFaces = 10;
    }

    public int guerir() {
        Random random = new Random();
        int somme = 0;

        for(int i=0; i<this.m_nbLancers; i++){
            somme += 1 + random.nextInt(this.m_nbFaces);
        }

        return somme;
    }

    /*
    TODO :
        - Implementer dans personnages les sorts
        - Sort Guerison :
            créer l'action dans Tours
        - Sort BoogieWoogie :
            créer méthode dans Donjon echnagerPlace(int entite1, int entite2)    --> on passe leur id en argument pour les trouver et les échanger de case



     */




}
