package equipement;

public abstract class Equipement {
    protected String m_nom;
    private String m_classe;
    protected String m_type;
    protected int m_diminutionVitesse;
    protected int m_augmentationForce;

    Equipement(String nom, String classe){
        this.m_nom = nom;
        this.m_classe = classe;
    }

    public int getDiminutionVitesse(){
        return m_diminutionVitesse;
    }

    public int getAugmentationForce(){
        return m_augmentationForce;
    }

    public String getType(){
        return m_type;
    }

    public String getClasse(){return m_classe;}

    @Override
    public abstract String toString();

}
