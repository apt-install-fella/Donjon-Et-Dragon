package personnages;

public interface Entite {
    void recevoirDegats(int degats);
    boolean estVivant();
    void attaquer(Entite cible);
    int getClasseArmure();
    String getNom();
    int getPV();
    String toString();
    String toStringDetails();
}
