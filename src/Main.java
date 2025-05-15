import personnages.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println("Bienvenue dans DOOnjon et Dragons");

        //Création du donjon
        //FAIRE LE CHOIX PAR DEFAUT OU COMPLET
        Donjon donjon = new Donjon(1, 15, 17);

        //Création des personnages
        System.out.println("Création des personnages, à vous de jouer, joueurs !");

        Scanner sc = new Scanner(System.in);
        String choix;
        int id = 1;
        do{
            String rappel = ".";
            System.out.println("Personnage " + id + " :");
            System.out.println("Comment se nomme votre personnage ?");
            String nom = sc.nextLine();

            //Choix de la race
            int choixRace = 0;
            Race race;
            do {
                System.out.println("Choisissez la race de " + nom + " : \n\t[1] Humain \t[2] Nain\t[3] Elfe\t[4] Halfelin \n(Tapez sur entrée pour avoir le rappel des caractéristiques)");
                rappel = sc.nextLine();

                if(rappel.isEmpty()){
                    System.out.println("--RAPPEL DES CARACTERISTIQUES DES RACES--");
                    System.out.println("Humains : toutes leurs caractéristiques augmentée de 2");
                    System.out.println("Nains : force augmentée de 6");
                    System.out.println("Elfes : dextérité augmentée de 6");
                    System.out.println("Halfelins : dextérité augmentée de 4, vitesse augmentée de 2");

                    System.out.println("[1] Humain \t[2] Nain\t[3] Elfe\t[4] Halfelin");
                    choixRace = sc.nextInt();
                    sc.nextLine();
                }
                else{
                    choixRace = Integer.parseInt(rappel);
                }

            }while(choixRace <1 || choixRace > 4);

            switch (choixRace){
                case 1:
                    race = Race.HUMAIN;
                    break;
                case 2:
                    race = Race.NAIN;
                    break;
                case 3:
                    race = Race.ELFE;
                    break;
                default:
                    race = Race.HALFELIN;
            }
            //Choix de la classe
            int choixClasse;

            do {
                System.out.println("Choisissez la classe de " + nom + " : \n\t[1] Clerc\t[2] Guerrier\t[3] Magicien\t[4] Roublard \n(Tapez sur entrée pour avoir le rappel des caractéristiques)");
                rappel = sc.nextLine();

                if(rappel.isEmpty()){
                    System.out.println("--RAPPEL DES CARACTERISTIQUES DES CLASSES--");
                    System.out.println("""
                            Clercs possèdent à leur création :
                            \t- 16 points de vie
                            \t- un équipement de base contenant une masse d'armes, une armure d'écailles et une arbalète légère""");
                    System.out.println("""
                            Guerriers possèdent à leur création :
                            \t- 20 points de vie,
                            \t- un équipement contenant une cotte de mailles, une épée longue, une arbalète légère""");
                    System.out.println("""
                            Magiciens possèdent à leur création :
                            \t- 12 points de vie
                            \t- un équipement contenant un bâton et une fronde""");
                    System.out.println("""
                            Roublards possèdent à leur création :
                            \t- 16 points de vie
                            \t- un équipement contenant une rapière et un arc court
                            """);

                    System.out.println("[1] Clerc \t[2] Guerrier\t[3] Magicien\t[4] Roublard");
                    choixClasse = sc.nextInt();
                    sc.nextLine();
                }
                else{
                    choixClasse = Integer.parseInt(rappel);
                }

            }while(choixClasse <1 || choixClasse > 4);

            Personnage perso;

            switch (choixClasse){
                case 1:
                    perso = new Clerc(nom, race);
                    break;
                case 2:
                    perso = new Guerrier(nom, race);
                    break;
                case 3:
                    perso = new Magicien(nom, race);
                    break;
                default:
                    perso = new Roublard(nom, race);
            }

            System.out.println("Personnage créé ! Voici un récapitulatif :");
            System.out.println(perso.toString());

            donjon.ajoutEntite(perso);

            //Vérification si les joueurs veulent créer un autre personnage
            System.out.println("Voulez-vous créer un autre personnage ? (o/n)");
            choix = sc.nextLine();
            id++;
        }while(choix.equalsIgnoreCase("o")); //On continue si on a tapé O ou o

        System.out.println(id-1 + " personnages créé(s) avec succès !");

        //Création des monstres
        // FAIRE ID MONSTRE EN FONCTION DE L'ESPECE
        System.out.println();
        System.out.println("Maître du jeu, à votre tour. Créez les monstres qui terrifieront les joueurs !");
        id = 1;

        do{
            System.out.println("Monstre " + id + " :");
            System.out.println("Quelle est l'espèce de ce monstre ?");
            String nom = sc.nextLine();

            System.out.println("Entrez le nombre de PV qu'a le " + nom + " : ");
            int PV = sc.nextInt();
            sc.nextLine();

            System.out.println("Entrez la classe d'armure de " + nom + " : ");
            int classeArmure = sc.nextInt();
            sc.nextLine();

            System.out.println("Entrez l'initiative de " + nom + " : ");
            int initiative = sc.nextInt();
            sc.nextLine();

            System.out.println("Entrez la vitesse de " + nom + " : ");
            int vitesse = sc.nextInt();
            sc.nextLine();

            System.out.println("Entrez la force de " + nom + " : ");
            int force = sc.nextInt();
            sc.nextLine();

            System.out.println("Entrez la dextérité de " + nom + " : ");
            int dexterite = sc.nextInt();
            sc.nextLine();

            System.out.println("Entrez la portee de l'attaque du " + nom + " : ");
            int porteeAttaque = sc.nextInt();
            sc.nextLine();

            System.out.println("Choix du jet de dé des attaques du " + nom);
            System.out.println("\tEntrez le nombre de dé : ");
            int nbLancers = sc.nextInt();
            sc.nextLine();
            System.out.println("\tEntrez le nombre de faces du dé : ");
            int nbFaces = sc.nextInt();
            sc.nextLine();


            Monstre monstre = new Monstre(1, nom, PV, porteeAttaque, classeArmure, initiative, vitesse, force, dexterite, nbLancers, nbFaces);

            donjon.ajoutEntite(monstre);
            System.out.println(monstre.toString());
            //Vérification si le maître du jeu veut créer un autre monstre
            System.out.println("Voulez-vous créer un autre monstre ? (o/n)");
            choix = sc.nextLine();
            id++;
        }while(choix.equalsIgnoreCase("o"));

        System.out.println("Maître du jeu, vous avez créé " + (id -1) + " monstres avec succès !");

    }
}