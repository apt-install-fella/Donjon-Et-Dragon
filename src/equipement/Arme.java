package equipement;
import java.util.Random;


public class Arme extends Equipement {
    private final int m_nbLancers;
    private final int m_nbFaces;
    private final int m_portee;
    private int m_bonusAttaque;
    private int m_bonusDegats;

    public Arme(String nom){
        super(nom, "Arme");
        this.m_bonusAttaque = 0;
        this.m_bonusDegats = 0;

        //Initialiser le type, la portée et le type/nombre de dés et les désaventages
        switch (nom){
            case "bâton": case "masse d'armes":
                this.m_type = "courante au corps-à-corps";
                this.m_nbLancers = 1;
                this.m_nbFaces = 6;
                this.m_portee = 1;
                this.m_diminutionVitesse = 0;
                this.m_augmentationForce = 0;
                break;
            case "épée longue": case "rapière":
                this.m_type = "de guerre au corps-à-corps";
                this.m_nbLancers = 1;
                this.m_nbFaces = 8;
                this.m_portee = 1;
                this.m_diminutionVitesse = 2;
                this.m_augmentationForce = 4;
                break;
            case "arbalète légère":
                this.m_nbLancers = 1;
                this.m_nbFaces = 8;
                this.m_portee = 16;
                this.m_type = "à distance";
                this.m_diminutionVitesse = 0;
                this.m_augmentationForce = 0;
                break;
            case "fronde":
                this.m_nbLancers = 1;
                this.m_nbFaces = 4;
                this.m_portee = 6;
                this.m_type = "à distance";
                this.m_diminutionVitesse = 0;
                this.m_augmentationForce = 0;
                break;
            case "arc court":
                this.m_nbLancers = 1;
                this.m_nbFaces = 6;
                this.m_portee = 16;
                this.m_type = "à distance";
                this.m_diminutionVitesse = 0;
                this.m_augmentationForce = 0;
                break;
            case "épée à deux mains":
                this.m_nbLancers = 2;
                this.m_nbFaces = 6;
                this.m_portee = 1;
                this.m_type = "de guerre au corps-à-corps";
                this.m_diminutionVitesse = 2;
                this.m_augmentationForce = 4;
                break;
            default:
                this.m_nbLancers = 0;
                this.m_nbFaces = 0;
                this.m_portee = 0;
                this.m_type = "Unknow";
                this.m_diminutionVitesse = 0;
                this.m_augmentationForce = 0;
        }
    }

     private int jetDes(){
        Random random = new Random();
        int somme = 0;

        for(int i=0; i<m_nbLancers; i++){
            somme += 1 + random.nextInt(m_nbFaces);
        }

        return somme;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.m_nom).append(" (dégâts : ").append(this.m_nbLancers).append("d").append(this.m_nbFaces).append(", portee : ").append(this.m_portee).append(")");

        return sb.toString();
    }

    public int getBonusAttaque() {
        return this.m_bonusAttaque;
    }

    public int jetDegats(){
        return jetDes() + this.m_bonusDegats;
    }

    public void updateBonus(){
        this.m_bonusAttaque++;
        this.m_bonusDegats++;
    }

}
