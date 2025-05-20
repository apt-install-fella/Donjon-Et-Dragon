import equipement.*;
import jeu.*;
import personnages.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Bienvenue dans DOOnjon et Dragons");

        //Création des personnages pour la partie
        ArrayList<Personnage> personnages = new ArrayList<Personnage>();
        System.out.println("-------------------");
        System.out.println("Création des personnages, à vous de jouer, joueurs !");

        String choix;
        int nbPersonnages = 1;
        do {
            String rappel = ".";
            System.out.println("Personnage " + nbPersonnages + " :");
            System.out.println("Comment se nomme votre personnage ?");
            String nom = scan.nextLine();

            //Choix de la race
            int choixRace = 0;
            Race race;
            do {
                System.out.println("Choisissez la race de " + nom + " : \n\t[1] Humain \t[2] Nain\t[3] Elfe\t[4] Halfelin \n(Tapez sur entrée pour avoir le rappel des caractéristiques)");
                rappel = scan.nextLine();

                if (rappel.isEmpty()) {
                    System.out.println("--RAPPEL DES CARACTERISTIQUES DES RACES--");
                    System.out.println("Humains : toutes leurs caractéristiques augmentées de 2");
                    System.out.println("Nains : force augmentée de 6");
                    System.out.println("Elfes : dextérité augmentée de 6");
                    System.out.println("Halfelins : dextérité augmentée de 4, vitesse augmentée de 2");

                    System.out.println("[1] Humain \t[2] Nain\t[3] Elfe\t[4] Halfelin");
                    choixRace = scan.nextInt();
                    scan.nextLine();
                } else {
                    choixRace = Integer.parseInt(rappel);
                }

            } while (choixRace < 1 || choixRace > 4);

            switch (choixRace) {
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
                rappel = scan.nextLine();

                if (rappel.isEmpty()) {
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
                    choixClasse = scan.nextInt();
                    scan.nextLine();
                } else {
                    choixClasse = Integer.parseInt(rappel);
                }

            } while (choixClasse < 1 || choixClasse > 4);

            Personnage perso;

            switch (choixClasse) {
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

            System.out.println("Vous pouvez désormais équiper armure et/ou arme.");
            String veutEquiper;
            do {
                int choixEquip;
                do {
                    System.out.println(perso.afficherInventaire());
                    choixEquip = scan.nextInt();
                    scan.nextLine();
                } while (choixEquip < 1 || choixEquip > perso.tailleInventaire());

                Equipement equip = perso.getEquipement(choixEquip);

                if (equip.getClasse().equals("Arme")) {
                    Arme arme = (Arme) equip;
                    perso.equiper(arme);
                    System.out.println("Vous avez équipé " + arme.toString());
                } else {
                    Armure armure = (Armure) equip;
                    perso.equiper(armure);
                    System.out.println("Vous avez équipé " + armure.toString());
                }

                System.out.println("Voulez-vous vous équiper avec autre chose ? (o/n)");
                veutEquiper = scan.nextLine();

            }while(!veutEquiper.equalsIgnoreCase("n"));
            System.out.println();

            personnages.add(perso);

            //Vérification si les joueurs veulent créer un autre personnage
            System.out.println("Voulez-vous créer un autre personnage ? (o/n)");
            choix = scan.nextLine();
            nbPersonnages++;
        } while (choix.equalsIgnoreCase("o")); //On continue si on a tapé O ou o

        System.out.println(nbPersonnages - 1 + " personnages créé(s) avec succès !");


        Donjon donjon;
        boolean finPartie = false;

        //Numéro de donjon (on quitte si les joueurs ne réussissent pas un donjon)
        for (int i = 1; i < 4; i++) {
                //Création du donjon
                System.out.println("------------");
                System.out.println("Création du donjon...");
                System.out.println("Il est temps de choisir la structure du donjon.\nVoulez-vous un modèle prédéfini ? (o/n)");
                String decision = scan.nextLine();
                if (decision.equalsIgnoreCase("o")) {
                    donjon = new Donjon(i);
                } else {
                    System.out.println("Création d'un donjon personalisé : choisissons les paramètres !");

                    int lignes;
                    do {
                        System.out.println("Combien de lignes aura votre carte du donjon ? (nombre compris entre 15 et 25)");
                        lignes = scan.nextInt();
                        scan.nextLine();
                    } while (lignes < 15 || lignes > 25);

                    int colonnes;
                    do {
                        System.out.println("Combien de colonnes aura votre carte du donjon ? (nombre compris entre 15 et 25)");
                        colonnes = scan.nextInt();
                        scan.nextLine();
                    } while (colonnes < 15 || colonnes > 25);

                    donjon = new Donjon(i, lignes, colonnes);

                    //Ajout d'obstacles
                    System.out.println("Parfait notre carte est prête, ajoutons lui quelques obstacles pour augmenter la difficulté !");
                    System.out.println("Voulez-vous créer un obstacle ? (o/n)");
                    String decision2 = scan.nextLine();

                    if (decision2.equalsIgnoreCase("o")) {
                        boolean continuer = true;

                        while (continuer) {
                            donjon.affichagePlateau();
                            System.out.println("A quelle position voulez-vous placer cet obstacle (ex : A2 --> colonne/ligne) ?");
                            String position = scan.nextLine();
                            boolean ajout = donjon.ajoutObstacle(position);
                            if (ajout) {
                                System.out.println("Obstacle ajouté avec succès.");
                                System.out.println("Voulez-vous en créer un autre ? (o/n)");
                                String decision3 = scan.nextLine();
                                if (decision3.equalsIgnoreCase("n")) {
                                    continuer = false;
                                }
                            } else {
                                System.out.println("Oups, case non existante, recommencez.");
                            }
                        }
                    }

                    //Ajout d'équipements
                    System.out.println("Passons à l'ajout d'équipement sur la carte.\n Voulez-vous ajouter des équipements? (o/n)");
                    String decision4 = scan.nextLine();
                    if (decision4.equalsIgnoreCase("o")) {
                        boolean continuer = true;
                        while (continuer) {
                            System.out.println("Voulez-vous une arme ou une armure ? Taper 1 pour arme, 2 pour armure. Si vous avez changé d'avis taper n");
                            String arme = scan.nextLine();
                            if (arme.equals("1")) {
                                Arme arme1 = new Arme("bâton");
                                Arme arme2 = new Arme("masse d'armes");
                                Arme arme3 = new Arme("épée longue");
                                Arme arme4 = new Arme("rapière");
                                Arme arme5 = new Arme("arbalète légère");
                                Arme arme6 = new Arme("fronde");
                                Arme arme7 = new Arme("arc court");
                                Arme arme8 = new Arme("épée à deux mains");
                                int choixArme;
                                do {
                                    System.out.println("Choisissez une arme :");
                                    System.out.print("\t[1] " + arme1.toString());
                                    System.out.print("\t[2] " + arme2.toString());
                                    System.out.print("\t[3] " + arme3.toString());
                                    System.out.print("\t[4] " + arme4.toString());
                                    System.out.print("\t[5] " + arme5.toString());
                                    System.out.print("\t[6] " + arme6.toString());
                                    System.out.print("\t[7] " + arme7.toString());
                                    System.out.print("\t[8] " + arme8.toString());
                                    System.out.println("\t(choix entre 1 et 8)");

                                    choixArme = scan.nextInt();
                                    scan.nextLine();
                                } while (choixArme < 1 || choixArme > 8);

                                Arme armeChoisie;

                                switch (choixArme) {
                                    case 1:
                                        armeChoisie = arme1;
                                        break;
                                    case 2:
                                        armeChoisie = arme2;
                                        break;
                                    case 3:
                                        armeChoisie = arme3;
                                        break;
                                    case 4:
                                        armeChoisie = arme4;
                                        break;
                                    case 5:
                                        armeChoisie = arme5;
                                        break;
                                    case 6:
                                        armeChoisie = arme6;
                                        break;
                                    case 7:
                                        armeChoisie = arme7;
                                        break;
                                    default:
                                        armeChoisie = arme8;
                                }

                                donjon.affichagePlateau();
                                System.out.println("Où voulez-vous placer cette arme ? (ex : A2)");
                                String position = scan.nextLine();

                                donjon.ajoutEquipement(armeChoisie, position);
                                System.out.println("Arme placée avec succès !");

                            } else if (arme.equals("2")) {
                                Armure armure1 = new Armure("armure d'écailles");
                                Armure armure2 = new Armure("demi-plate");
                                Armure armure3 = new Armure("cotte de mailles");
                                Armure armure4 = new Armure("harnois");
                                int choixArmure;

                                do {
                                    System.out.println("Choisissez une armure :");
                                    System.out.print("\t[1] " + armure1.toString());
                                    System.out.print("\t[2] " + armure2.toString());
                                    System.out.print("\t[3] " + armure3.toString());
                                    System.out.print("\t[4] " + armure4.toString());
                                    System.out.println("\t(choix entre 1 et 4)");

                                    choixArmure = scan.nextInt();
                                    scan.nextLine();
                                } while (choixArmure < 1 || choixArmure > 4);

                                Armure armureChoisie;

                                switch (choixArmure) {
                                    case 1:
                                        armureChoisie = armure1;
                                        break;
                                    case 2:
                                        armureChoisie = armure2;
                                        break;
                                    case 3:
                                        armureChoisie = armure3;
                                        break;
                                    default:
                                        armureChoisie = armure4;
                                }

                                donjon.affichagePlateau();
                                System.out.println("Où voulez-vous placer cette armure ? (ex : A2)");
                                String position = scan.nextLine();

                                donjon.ajoutEquipement(armureChoisie, position);
                                System.out.println("Armure placée avec succès !");

                            } else if (arme.equals("n")) {
                                continuer = false;
                            } else {
                                System.out.println("Oups, vous avez taper sur une mauvaise touche, recommencez.");
                            }
                        }
                    }
                }

                //Ajout des personnages au donjon
                System.out.println("Maître du jeu, vous allez désormais placer les joueurs sur le plateau...");

                for (Personnage perso : personnages) {
                    donjon.ajoutPersonnage(perso);
                    String retour = "";
                    String pos;

                    do {
                        System.out.println("Positionnons " + perso.getNom() + ". Où voulez-vous le positionner (ex : A2) ? Attention aux obstacles !");
                        donjon.affichagePlateau();
                        pos = scan.nextLine();
                        retour = donjon.positionner(perso, pos);
                        System.out.println(retour);
                        System.out.println();
                    }while(!Objects.equals(retour, perso.getNom() + " a été positionné avec succès dans " + pos));
                }


                //Création des monstres
                System.out.println();
                System.out.println("Maître du jeu, créez les monstres qui terrifieront les joueurs !");
                int nbMonstres = 1;
                Hashtable<String, Integer> espece = new Hashtable<>();          //Sert pour l'id du monstre

                do {
                    System.out.println("Monstre " + nbMonstres + " :");
                    System.out.println("Quelle est l'espèce de ce monstre ?");
                    String nom = scan.nextLine();

                    if (!espece.containsKey(nom)) {              //S'il n'y a pas encore eu de monstre de cette espèce, on l'ajoute
                        espece.put(nom, 1);
                    } else {                                       //Sinon on incrémente le nombre de 1
                        espece.put(nom, espece.get(nom) + 1);
                    }

                    System.out.println("Entrez le nombre de PV qu'a le " + nom + " : ");
                    int PV = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Entrez la classe d'armure du " + nom + " : ");
                    int classeArmure = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Entrez l'initiative du " + nom + " : ");
                    int initiative = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Entrez la vitesse du " + nom + " : ");
                    int vitesse = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Entrez la force du " + nom + " : ");
                    int force = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Entrez la dextérité du " + nom + " : ");
                    int dexterite = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Entrez la portee de l'attaque du " + nom + " : ");
                    int porteeAttaque = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Choix du jet de dé des attaques du " + nom);
                    System.out.print("\tEntrez le nombre de dé : ");
                    int nbLancers = scan.nextInt();
                    scan.nextLine();
                    System.out.print("\tEntrez le nombre de faces du dé : ");
                    int nbFaces = scan.nextInt();
                    scan.nextLine();


                    Monstre monstre = new Monstre(espece.get(nom), nom, PV, porteeAttaque, classeArmure, initiative, vitesse, force, dexterite, nbLancers, nbFaces);


                    donjon.ajoutMonstre(monstre);
                    String retour;
                    String pos;

                    do {
                        System.out.println();
                        System.out.println("Positionnons ce monstre ! \nOù voulez-vous le positionner (ex : A2) ? Attention aux obstacles !");
                        donjon.affichagePlateau();
                        pos = scan.nextLine();
                        retour = donjon.positionner(monstre, pos);
                        System.out.println(retour);
                        System.out.println();
                    }while(!Objects.equals(retour, monstre.getNom() + " a été positionné avec succès dans " + pos));


                    System.out.println("Monstre créé ! Voici un récapitulatif :");
                    System.out.println(monstre.toString());

                    //Vérification si le maître du jeu veut créer un autre monstre
                    System.out.println("Voulez-vous créer un autre monstre ? (o/n)");
                    choix = scan.nextLine();
                    nbMonstres++;
                } while (choix.equalsIgnoreCase("o"));

                System.out.println("Maître du jeu, vous avez créé " + (nbMonstres - 1) + " monstres avec succès !");

                //Jeu
                System.out.println("Le donjon et les personnages/monstres sont créés, que le donjon commence !");

                donjon.affichagePlateau();


                ///////////GESTION DES TOURS/////////
            Narrateur narrateur;
            System.out.println("\n\nOups, et vous, Maitre du jeu, nous vous avons presque oublié. Voulez-vous un pseudo ? (o/n)");
            choix = scan.nextLine();
            if (choix.equalsIgnoreCase("o")) {
                System.out.println("Quel sera ce pseudo ?");
                String pseudo = scan.nextLine();
                narrateur = new Narrateur(pseudo);
            }
            else {
                System.out.println("Vous restez donc 'Maitre du jeu'");
                narrateur = new Narrateur();
            }

            System.out.println(narrateur.getPseudo() + " veuillez présenter le contexte aux joueurs...");
            scan.nextLine();

            System.out.println("Que le jeu commence ! Bonne chance\n");

            ArrayList<Entite> joueurs = new ArrayList<>(donjon.getOrdre());

            boolean suite = true;

            while (suite) {
                int tour=1;
                for (Entite entite : joueurs) {
                    Tours tours= new Tours(tour);
                    int id=donjon.getId(entite);
                    boolean estUnPerso=false;
                    Personnage perso=null;
                    Monstre monstre=null;
                    Hashtable<Personnage,Integer> persos = new Hashtable<>(donjon.getListePersonnages());//je recup la liste des persos du donjon
                    Hashtable<Monstre,Integer> mons = new Hashtable<>(donjon.getListeMonstres());
                    for (Map.Entry<Personnage, Integer> e : persos.entrySet()) {//pour chaque perso de cette liste
                        if (e.getValue() == id) { //si l'id renseigné est le meme que celui du perso
                            estUnPerso=true;
                            perso = e.getKey();
                        }

                    }
                    for (Map.Entry<Monstre, Integer> e : mons.entrySet()) {//pour chaque perso de cette liste
                        if (e.getValue() == id) { //si l'id renseigné est le meme que celui du perso
                            estUnPerso=false;
                            monstre = e.getKey();
                        }

                    }

                    if (estUnPerso) {
                        tours.ajouterTourPersonnage(perso, donjon, narrateur);
                    }
                    else {
                        tours.ajouterTourMonstre(monstre, donjon, narrateur);
                    }

                    for (Map.Entry<Personnage, Integer> e : persos.entrySet()) {//pour chaque perso de cette liste
                        int fin=donjon.finDonjon(e.getKey());

                        if (fin==0){
                            System.out.println("Oh non ! "+e.getKey().getNom()+" est mort ! Les monstres ont gagné...");
                            System.out.println("GAME OVER");
                            suite = false;
                            finPartie = true;
                            break;
                        } else if (fin==1) {
                            System.out.println("Le dernier monstre a été abattu, votre équipe a réussi ce donjon!");
                            System.out.println("VICTOIRE");
                            suite = false;
                            break;
                        }
                        else {
                            System.out.println("On continue !");
                        }

                    }

                    if(finPartie){
                        return;
                    }

                    if(!suite){
                        break;
                    }

                }
                tour=tour+1;
            }

        }

        System.out.println("Partie terminée ! Si vous souhaitez en recommencer une, merci de relancer le jeu.");

    }
}