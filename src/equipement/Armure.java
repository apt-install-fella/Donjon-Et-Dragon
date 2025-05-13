package equipement;

public class Armure extends Equipement {
    private final int m_classe;

    public Armure(String nom){
        super(nom);

        //Determiner la classe de l'armure
        switch (nom){
            case "armure d'écailles":
                this.m_classe = 9;
                break;
            case "demi-plate":
                this.m_classe = 10;
                break;
            case "cotte de mailles":
                this.m_classe = 11;
                break;
            case "harnois":
                this.m_classe = 12;
                break;
            default:
                this.m_classe = 0;
        }

        //Déterminer le type de l'armure
        switch(nom){
            case "armure d'écailles": case "demi-plate":
                this.m_type = "légère";
                break;
            case "cotte de mailles": case "harnois":
                this.m_type = "lourde";
                break;
            default:
                this.m_type = "Unknow";
        }
    }

}
