package equipement.sorts;

import equipement.Arme;

public class ArmeMagique {

    public ArmeMagique() {

    }

    public void ameliorer(Arme arme){
        arme.updateBonus();
    }
}
