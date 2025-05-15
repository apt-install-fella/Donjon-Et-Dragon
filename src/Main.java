import personnages.Entite;
import personnages.Guerrier;
import personnages.Monstre;
import personnages.Race;

public class Main {
    public static void main(String args[]){
        System.out.println("Bienvenue dans DOOnjon et Dragons");

        Donjon donjon = new Donjon(1, 20, 21);

        Entite p1 = new Guerrier("Fella", Race.ELFE);
        Entite p2 = new Guerrier("Elora", Race.HUMAIN);
        Entite p3 = new Guerrier("Jomni", Race.ELFE);
        Entite p4 =new Monstre(1,"Dragon",20,24,6,9,45,87,34,3,6);

        donjon.ajoutEntite(p1);
        donjon.ajoutEntite(p2);
        donjon.ajoutEntite(p3);
        donjon.ajoutEntite(p4);

        donjon.Affichage_tours();


    }
}