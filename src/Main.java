import personnages.Entite;
import personnages.Guerrier;
import personnages.Monstre;
import personnages.Race;

public class Main {
    public static void main(String args[]){
        System.out.println("Bienvenue dans DOOnjon et Dragons");

        Donjon donjon = new Donjon(1, 20, 21);

        Guerrier p1 = new Guerrier("Fella", Race.ELFE);
        Guerrier p2 = new Guerrier("Elora", Race.HUMAIN);
        Guerrier p3 = new Guerrier("Jomni", Race.ELFE);
        Monstre p4 =new Monstre(1,"Dragon",20,24,6,9,45,87,34,3,6);

        donjon.ajoutPersonnage(p1);
        donjon.ajoutPersonnage(p2);
        donjon.ajoutPersonnage(p3);
        donjon.ajoutMonstre(p4);

        donjon.Affichage_tours();

    }
}