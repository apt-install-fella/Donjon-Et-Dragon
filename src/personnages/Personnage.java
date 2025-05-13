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
        }
        else{
            this.m_inventaire.add(m_arme);

            //Remet les caractéristiques vitesse et force par défaut
            this.m_vitesse += m_arme.getDiminutionVitesse();
            this.m_force -= m_arme.getAugmentationForce();

            m_arme = arme;
            this.m_inventaire.remove(arme);
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
        }
        else{
            this.m_inventaire.add(m_armure);

            //Remet la caractéristique vitesse par défaut
            this.m_vitesse += m_armure.getDiminutionVitesse();

            m_armure = armure;
            this.m_inventaire.remove(armure);
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

    public int getDistance(){
        return this.m_vitesse / 3;
    }

    /* PRECONDITION :
    L'entite doit être à portée d'attaque
    Le joueur doit avoir équipé une arme
    ------
    Affiche ce qui se passe directement
    */
    @Override
    public void attaquer(Entite cible){
        System.out.println("Lancé d'un dé à 20 faces...");
        int jetAttaque = jetDes(1, 20);
        System.out.println("Vous avez fait : " + jetAttaque);

        //Ajout des bonus en fonction du type de l'arme
        switch(m_arme.getType()){
            case "à distance":
                System.out.println("Votre attaque est de : " + jetAttaque + " + " + this.m_dexterite + " (dextérité) = " + jetAttaque + this.m_dexterite);
                jetAttaque += this.m_dexterite;
                break;
            case "courante au corps-à-corps": case "de guerre au corps-à-corps":
                System.out.println("Votre attaque est de : " + jetAttaque + " + " + this.m_force + " (force) = " + jetAttaque + this.m_force);
                jetAttaque += this.m_force;
                break;
        }

        //On vérifie si cela perce l'armure ou non
        if(jetAttaque <= cible.getClasseArmure()){
            System.out.println("Votre attaque n'est pas assez puissante pour percer l'armure du " + cible.getNom() + " (" + cible.getClasseArmure() + ")...");
        }
        else{
            System.out.println("Votre attaque perce l'armure du " + cible.getNom() + " (" + cible.getClasseArmure() + ") !");
            System.out.println("Lancé d'un dé à 4 faces...");
            int degats = m_arme.jetDes();
            System.out.println("Vous infligez " + degats + " dégâts au " + cible.getNom());
            cible.recevoirDegats(degats);
            System.out.println("Il reste " + cible.getPV() + " PV à " + cible.getNom() +  ".");
        }

    }

    @Override
    public int getClasseArmure(){
        return m_armure.getClasseArmure();
    }

    @Override
    public int getPV() {return this.m_PV;}

    //Utilisé pour l'affichage du récap rapide
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNom()).append(" (").append(this.m_race.toString()).append(" ").append(this.m_classe).append(", ").append(this.m_PV).append("/").append(this.m_MAX_PV).append(")");

        return sb.toString();
    }

    //Utilisé pour l'affichage lors du tour du personnage
    @Override
    public String toStringDetails(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNom()).append("\n");
        sb.append("\t").append("Vie : ").append(this.m_PV).append("/").append(this.m_MAX_PV).append("\n");

        //Ajout de l'équipement et inventaire
        sb.append(afficherEquipement(this.m_armure));
        sb.append(afficherEquipement(this.m_arme));

        for(Equipement equipement : this.m_inventaire){
            if(equipement.getClasse().equals("Armure")){
                sb.append(afficherEquipement((Armure) equipement));
            }
            else{
                sb.append(afficherEquipement((Arme) equipement));
            }
        }

        //Ajout des caractéristiques
        sb.append("\t").append("Force : ").append(this.m_force).append("\n");
        sb.append("\t").append("Dextérité : ").append(this.m_dexterite).append("\n");
        sb.append("\t").append("Vitesse : ").append(this.m_vitesse).append("\n");

        return sb.toString();
    }

    private String afficherEquipement(Arme arme){
        StringBuilder sb = new StringBuilder();

        if(arme != null){
            sb.append("\t").append("Arme : ").append(arme.toString()).append("\n");
        }
        else{
            sb.append("\t").append("Arme : aucune").append("\n");
        }
        return sb.toString();
    }

    private String afficherEquipement(Armure armure){
        StringBuilder sb = new StringBuilder();

        if(armure != null){
            sb.append("\t").append("Armure : ").append(armure.toString()).append("\n");
        }
        else{
            sb.append("\t").append("Armure : aucune").append("\n");
        }
        return sb.toString();
    }

    

    /* TODO-LIST :
        - methode toString détaillée (voir README)
    */
}
