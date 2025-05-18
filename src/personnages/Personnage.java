package personnages;

import equipement.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Personnage implements Entite{
    private final String m_nom;
    private final Race m_race;
    private final String m_classe;
    private final int m_MAX_PV;
    private int m_PV;
    private int m_force;
    private int m_dexterite;
    private int m_vitesse;
    private int m_initiative;
    private Armure m_armure;
    private Arme m_arme;
    private ArrayList<Equipement> m_inventaire;

    Personnage(String nom, Race race, int pv, ArrayList<Equipement> inventaire, String classe){
        this.m_nom = nom;
        this.m_race = race;
        this.m_classe = classe;
        this.m_MAX_PV = pv;
        this.m_PV = pv;
        this.m_inventaire = inventaire;
        this.m_arme = null;
        this.m_armure = null;

        //Décision des caractéristiques
        this.m_force = jetDes(4, 4) + 3;
        this.m_dexterite = jetDes(4, 4) + 3;
        this.m_vitesse = jetDes(4, 4) + 3;
        this.m_initiative = jetDes(4, 4) + 3;

        switch(m_race){
            case HUMAIN:
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

    @Override
    public String getNom(){
        return m_nom;
    }

    /* PRECONDITION
    L'argument arme ne doit pas être null
                    doit être contenu dans l'inventaire
    */
    public void equiper(Arme arme){
        if(this.m_arme == null){
            m_arme = arme;
            this.m_inventaire.remove(arme);
        }
        else{
            this.m_inventaire.add(m_arme);

            //Remet les caractéristiques vitesse et force par défaut
            this.m_vitesse += m_arme.getDiminutionVitesse();
            this.m_force -= m_arme.getAugmentationForce();

            m_arme = arme;
        }
        //Modifie la vitesse et la force si besoin
        this.m_vitesse -= arme.getDiminutionVitesse();
        this.m_force += arme.getAugmentationForce();
    }

    /* PRECONDITION
    L'argument armure ne doit pas être null
                    doit être contenu dans l'inventaire
    */
    public void equiper(Armure armure){
        if(this.m_armure == null){
            m_armure = armure;
            this.m_inventaire.remove(armure);
        }
        else{
            this.m_inventaire.add(m_armure);

            //Remet la caractéristique vitesse par défaut
            this.m_vitesse += m_armure.getDiminutionVitesse();

            m_armure = armure;
        }
        //Modifie la vitesse si besoin
        this.m_vitesse -= armure.getDiminutionVitesse();
    }

    @Override
    public void recevoirDegats(int degats){
        this.m_PV -= degats;
    }

    @Override
    public boolean estVivant(){
        return this.m_PV > 0;
    }

    @Override
    public int getDistance(){
        if(this.m_vitesse <3){
            return 1;
        }

        return this.m_vitesse / 3;
    }

    @Override
    public int getInitiative(){
        return this.m_initiative;
    }

    /* PRECONDITION :
    L'entite doit être à portée d'attaque
    Le joueur doit avoir équipé une arme
    ------
    @return texte
    */
    @Override
    public String attaquer(Entite cible){
        if (m_arme == null) {
            return "Erreur : vous n'avez pas d'arme pour attaquer.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Lancé d'un dé à 20 faces...\n");
        int jetAttaque = jetDes(1, 20);
        sb.append("Vous avez fait : ").append(jetAttaque).append("\n");

        //Ajout des bonus en fonction du type de l'arme
        switch(m_arme.getType()){
            case "à distance":
                sb.append("Votre attaque est de : ").append(jetAttaque).append(" + ").append(this.m_dexterite).append(" (dextérité) = ");
                jetAttaque += this.m_dexterite;
                sb.append(jetAttaque).append("\n");
                break;
            case "courante au corps-à-corps": case "de guerre au corps-à-corps":
                sb.append("Votre attaque est de : ").append(jetAttaque).append(" + ").append(this.m_force).append(" (force) = ");
                jetAttaque += this.m_force;
                sb.append(jetAttaque).append("\n");
                break;
        }

        //On vérifie si cela perce l'armure ou non
        if(jetAttaque <= cible.getClasseArmure()){
            sb.append("Votre attaque n'est pas assez puissante pour percer l'armure du ").append(cible.getNom()).append(" (").append(cible.getClasseArmure()).append(")...\n");
        }
        else{
            sb.append("Votre attaque perce l'armure du ").append(cible.getNom()).append(" (").append(cible.getClasseArmure()).append(") !\n");
            sb.append("Lancé d'un dé à 4 faces...\n");
            int degats = m_arme.jetDes();
            sb.append("Vous infligez ").append(degats).append(" dégâts au ").append(cible.getNom()).append("\n");
            cible.recevoirDegats(degats);
            sb.append("Il reste ").append(cible.getPV()).append(" PV au ").append(cible.getNom()).append(".\n");
        }

        return sb.toString();

    }

    @Override
    public int getClasseArmure(){
        if(m_armure != null){
            return m_armure.getClasseArmure();
        }
        return 0;
    }

    @Override
    public int getPV() {return this.m_PV;}

    //Utilisé pour l'affichage du récap rapide
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNom()).append(" (").append(this.m_race.toString()).append(" ").append(this.m_classe).append(", ").append(this.m_PV).append("/").append(this.m_MAX_PV).append(")\n");

        return sb.toString();
    }

    //Utilisé pour l'affichage lors du tour du personnage
    @Override
    public String toStringDetails(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNom()).append("\n");
        sb.append("\t").append("Vie : ").append(this.m_PV).append("/").append(this.m_MAX_PV).append("\n");

        //Ajout de l'équipement et inventaire
        sb.append(afficherEquipement(this.m_armure)).append("\n");
        sb.append(afficherEquipement(this.m_arme)).append("\n");

        sb.append("\t");
        sb.append(afficherInventaire());

        //Ajout des caractéristiques
        sb.append("\t").append("Force : ").append(this.m_force).append("\n");
        sb.append("\t").append("Dextérité : ").append(this.m_dexterite).append("\n");
        sb.append("\t").append("Vitesse : ").append(this.m_vitesse).append("\n");

        return sb.toString();
    }

    /*
        Servent lorsque de toStringDetails() --> affichage de l'arme ou armure équipée
    */
    private static String afficherEquipement(Arme arme){
        StringBuilder sb = new StringBuilder();

        if(arme != null){
            sb.append("\t").append("Arme : ").append(arme.toString());
        }
        else{
            sb.append("\t").append("Arme : aucune");
        }
        return sb.toString();
    }

    private static String afficherEquipement(Armure armure){
        StringBuilder sb = new StringBuilder();

        if(armure != null){
            sb.append("\t").append("Armure : ").append(armure.toString());
        }
        else{
            sb.append("\t").append("Armure : aucune");
        }
        return sb.toString();
    }

    //Méthode à appeler lors du passage au donjon suivant : réinitialise les PV du personnage
    public void resetPV(){
        this.m_PV = this.m_MAX_PV;
    }

    /*
    Ajout d'équipements dans l'inventaire (utilisées lorsque le personnage rammasse un équipement)
    */
    public void ajoutEquipement(Arme arme){
        this.m_inventaire.add(arme);
    }

    public void ajoutEquipement(Armure armure){
        this.m_inventaire.add(armure);
    }

    /*
        Servent lors de l'affichage de l'inventaire
    */
    private static String afficherEquipement(Armure armure, int position){
        StringBuilder sb = new StringBuilder();
        if(armure != null){
            sb.append("\t").append("[").append(position).append("] ").append(armure.toString());
        }

        return sb.toString();
    }

    private static String afficherEquipement(Arme arme, int position){
        StringBuilder sb = new StringBuilder();
        if(arme != null){
            sb.append("\t").append("[").append(position).append("] ").append(arme.toString());
        }

        return sb.toString();
    }


    public String afficherInventaire(){
        StringBuilder sb = new StringBuilder();
        sb.append("Inventaire : \n");

        for(int i=0; i<this.m_inventaire.size(); i++){
            Equipement equipement = this.m_inventaire.get(i);

            if(equipement.getClasse().equals("Armure")){
                sb.append(afficherEquipement((Armure) equipement, i+1));
            }
            else{
                sb.append(afficherEquipement((Arme) equipement, i+1));
            }

        }
        sb.append("\n");

        return sb.toString();
    }

    public int tailleInventaire(){
        return this.m_inventaire.size();
    }

    public Equipement getEquipement(int position){
        return this.m_inventaire.get(position-1);
    }
}
