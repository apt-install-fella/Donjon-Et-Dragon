package equipement;

public class Armure extends Equipement {
    private final int m_classe;

    public Armure(String nom){
<<<<<<< HEAD
        super(nom, "Armure");
=======
        super(nom);
>>>>>>> master

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
<<<<<<< HEAD
                this.m_diminutionVitesse = 0;
                this.m_augmentationForce = 0;
                break;
            case "cotte de mailles": case "harnois":
                this.m_type = "lourde";
                this.m_diminutionVitesse = 4;
                this.m_augmentationForce = 0;
                break;
            default:
                this.m_type = "Unknow";
                this.m_diminutionVitesse = 0;
                this.m_augmentationForce = 0;
        }
    }

    public int getClasseArmure(){
        return this.m_classe;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.m_nom).append(" (classe d'armure : ").append(this.m_classe).append(")");

        return sb.toString();
    }

=======
                break;
            case "cotte de mailles": case "harnois":
                this.m_type = "lourde";
                break;
            default:
                this.m_type = "Unknow";
        }
    }

>>>>>>> master
}
