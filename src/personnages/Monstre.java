package personnages;

import java.util.Random;

public abstract class Monstre implements Entite {
    private int m_id;
    private String m_espece;
    private final int m_MAX_PV;
    private int m_PV;
    private int m_force;
    private int m_dexterite;
    private int m_classeArmure;
    private int m_initiative;
    private int m_vitesse;
    private int m_porteeAttaque;
    private int m_nbLancers;
    private int m_nbFaces;

    public Monstre(int ID, String espece, int PV, int porteeAttaque, int classeArmure, int initiative, int vitesse, int force, int dexterite, int nbLancers, int nbFaces){
        this.m_id = ID;
        this.m_espece = espece;
        this.m_MAX_PV = PV;
        this.m_PV = PV;
        this.m_porteeAttaque = porteeAttaque;
        this.m_classeArmure = classeArmure;
        this.m_initiative = initiative;
        this.m_vitesse = vitesse;
        this.m_nbLancers = nbLancers;
        this.m_nbFaces = nbFaces;

        if(this.m_porteeAttaque == 1){
            this.m_dexterite = 0;
            this.m_force = force;
        }
        else{
            this.m_force = 0;
            this.m_dexterite = dexterite;
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

    private int jetDesAttaque(){
        Random random = new Random();
        int somme = 0;

        for(int i=0; i<this.m_nbLancers; i++){
            somme += 1 + random.nextInt(this.m_nbFaces);
        }

        return somme;
    }

    @Override
    public String getNom(){
        return this.m_espece;
    }

    @Override
    public int getClasseArmure(){
        return m_classeArmure;
    }

    @Override
    public void recevoirDegats(int degats) {
        this.m_PV -= degats;
    }

    @Override
    public boolean estVivant() {
        return m_PV >0;
    }

    @Override
    public int getPV() {return this.m_PV;}

    /* PRECONDITION :
    L'entite doit être à portée d'attaque
    ------
    Affiche ce qui se passe directement
    */
    @Override
    public void attaquer(Entite cible){
        System.out.println("Lancé d'un dé à 20 faces...");
        int jetAttaque = jetDes(1, 20);

        //Ajout des bonus en fonction du type d'attaque
        if(this.m_porteeAttaque == 1){
            System.out.println("L'attaque de " + this.getNom()  + " est de : " + jetAttaque + " + " + this.m_dexterite + " (dextérité) = " + jetAttaque + this.m_dexterite);
            jetAttaque += this.m_dexterite;
        }
        else{
            System.out.println("L'attaque de " + this.getNom()  + " est de : " + jetAttaque + " + " + this.m_force + " (force) = " + jetAttaque + this.m_force);
            jetAttaque += this.m_force;
        }

        //On vérifie si cela perce l'armure ou non
        if(jetAttaque <= cible.getClasseArmure()){
            System.out.println("l'attaque n'est pas assez puissante pour percer l'armure de " + cible.getNom() + " (" + cible.getClasseArmure() + ")...");
        }
        else{
            System.out.println("L'attaque perce l'armure de " + cible.getNom() + " (" + cible.getClasseArmure() + ") !");
            System.out.println("Lancé d'un dé à 4 faces...");
            int degats = this.jetDesAttaque();
            System.out.println("Le " + this.getNom() + " inflige " + degats + " dégâts à " + cible.getNom());
            cible.recevoirDegats(degats);
            System.out.println("Il reste " + cible.getPV() + " PV à " + cible.getNom() +  ".");
        }
    }

    //Utilisé pour l'affichage du récap rapide
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNom()).append(" (").append(this.m_PV).append("/").append(this.m_MAX_PV).append(")");

        return sb.toString();
    }

    //Utilisé pour l'affichage lors du tour du personnage
    @Override
    public String toStringDetails(){
        StringBuilder sb = new StringBuilder();
        

        return sb.toString();
    }


}
