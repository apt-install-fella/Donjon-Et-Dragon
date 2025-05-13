package equipement;

public class Arme extends Equipement {
    private final int m_nbLancers;
    private final int m_nbFaces;
    private final int m_portee;
    public Arme(String nom){
        super(nom);

        //Initialiser le type, la portée et le type/nombre de dés
        switch (nom){
            case "bâton": case "masse d'armes":
                m_type = "courante au corps-à-corps";
                this.m_nbLancers = 1;
                this.m_nbFaces = 6;
                this.m_portee = 1;
                break;
            case "épée longue": case "rapière":
                m_type = "de guerre au corps-à-corps";
                this.m_nbLancers = 1;
                this.m_nbFaces = 8;
                this.m_portee = 1;
                break;
            case "arbalète légère":
                this.m_nbLancers = 1;
                this.m_nbFaces = 8;
                this.m_portee = 16;
                m_type = "à distance";
                break;
            case "fronde":
                this.m_nbLancers = 1;
                this.m_nbFaces = 4;
                this.m_portee = 6;
                m_type = "à distance";
                break;
            case "arc court":
                this.m_nbLancers = 1;
                this.m_nbFaces = 6;
                this.m_portee = 16;
                m_type = "à distance";
                break;
            default:
                this.m_nbLancers = 0;
                this.m_nbFaces = 0;
                this.m_portee = 0;
                m_type = "Unknow";
        }
    }


}
